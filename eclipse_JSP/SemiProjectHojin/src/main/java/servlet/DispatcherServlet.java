package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UsersService;
import view.ModelAndView;
import controller.Controller;
import controller.HandlerMapping;
import dto.UsersDTO;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DispatcherServlet() {
        super();
        System.out.println("[DispatcherServlet] 생성자 호출 -> DispatcherServlet 인스턴스 생성");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 세션 확인 및 요청 처리
            handleSession(request);
            handleRequest(request, response);
        } catch (Exception e) {
            // 서버 내부 오류 처리
            System.err.println("[DispatcherServlet] 예외 발생: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // POST 요청도 GET 요청처럼 처리
    }

    /**
     * 사용자 세션을 확인하고 관리합니다.
     */
    private void handleSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            // 세션이 없거나 사용자 정보가 없는 경우 -> 세션 복구 시도
            if (!tryRestoreSessionFromCookie(request)) {
                System.out.println("[DispatcherServlet] 세션 복구 실패: 사용자 세션 없음");
            }
        } else {
            // 기존 세션 유지 관리
            manageSessionExpiry(session);
        }
    }

    /**
     * 쿠키를 통해 세션 복구를 시도합니다.
     */
    private boolean tryRestoreSessionFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return false;

        for (Cookie cookie : cookies) {
            if ("loginId".equals(cookie.getName())) {
                String loginId = cookie.getValue();
                UsersDTO user = UsersService.getInstance().getUserByLoginId(loginId);

                if (user != null) {
                    HttpSession session = request.getSession(true); // 새 세션 생성
                    session.setAttribute("user", user);
                    session.setAttribute("rememberMe", true); // 로그인 상태 유지 플래그 설정
                    System.out.println("[DispatcherServlet] 세션 복구 완료: " + user.getNickName());
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 세션 만료를 관리합니다.
     */
    private void manageSessionExpiry(HttpSession session) {
        Instant expireTime = (Instant) session.getAttribute("sessionExpireTime");
        Boolean rememberMe = (Boolean) session.getAttribute("rememberMe");

        if (Boolean.TRUE.equals(rememberMe)) {
            // "로그인 상태 유지" 사용자는 세션 만료 시간 무시
            System.out.println("[DispatcherServlet] '로그인 상태 유지' 사용자 -> 세션 유지");
        } else if (expireTime != null && Instant.now().isAfter(expireTime)) {
            // 세션 만료 처리
            session.invalidate();
            System.out.println("[DispatcherServlet] 세션 만료 -> 로그아웃 처리");
        } else {
            // 세션 만료 시간 연장
            session.setAttribute("sessionExpireTime", Instant.now().plus(10, ChronoUnit.MINUTES));
            System.out.println("[DispatcherServlet] 세션 연장 완료");
        }
    }

    /**
     * 클라이언트 요청을 처리합니다.
     */
    private void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // URI 및 컨텍스트 경로 추출
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();

        // 명령어 추출
        String command = uri.substring(contextPath.length());
        if (command.startsWith("/")) {
            command = command.substring(1); // 앞의 슬래시 제거
        }
        command = command.replace(".do", ""); // ".do" 제거
        System.out.println("[DispatcherServlet] 요청된 명령: " + command);

        // 명령어 유효성 검사
        if (command == null || command.isEmpty()) {
            System.err.println("[DispatcherServlet] 유효하지 않은 명령: " + command);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 요청입니다.");
            return;
        }

        // 핸들러 매핑에서 Controller 가져오기
        Controller controller = HandlerMapping.getInstance().createController(command);
        if (controller == null) {
            System.err.println("[DispatcherServlet] 매핑되지 않은 명령: " + command);
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "요청을 처리할 수 없습니다.");
            return;
        }

        try {
            // Controller 실행 및 결과 처리
            ModelAndView view = controller.execute(request, response);

            if (view != null) {
                // Model 데이터를 요청 속성에 추가
                setRequestAttributes(request, view);

                if (view.isRedirect()) {
                    // Redirect 처리
                    response.sendRedirect(view.getPath());
                } else {
                    // Forward 처리
                    forwardToView(request, response, view.getPath());
                }
            }
        } catch (Exception e) {
            // 예외 처리 및 로깅
            System.err.println("[DispatcherServlet] 요청 처리 중 예외 발생: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.");
        }
    }

    /**
     * ModelAndView 데이터를 요청 속성에 설정합니다.
     */
    private void setRequestAttributes(HttpServletRequest request, ModelAndView view) {
        for (String key : view.getModel().keySet()) {
            request.setAttribute(key, view.getModel().get(key));
        }
    }

    /**
     * 요청을 지정된 뷰로 포워딩합니다.
     */
    private void forwardToView(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        if (path == null || path.isEmpty()) {
            throw new ServletException("[DispatcherServlet] 유효하지 않은 경로: " + path);
        }

        System.out.println("[DispatcherServlet] View로 포워딩: " + path);
        request.getRequestDispatcher(path).forward(request, response);
    }
}
