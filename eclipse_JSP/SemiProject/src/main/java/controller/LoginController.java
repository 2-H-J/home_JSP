package controller;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UsersService;
import dto.UsersDTO;
import view.ModelAndView;

public class LoginController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 로그인 아이디와 비밀번호, 로그인 상태 유지 여부를 요청에서 가져옴
        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        System.out.println("[LoginController] execute() 호출 -> 로그인 시도 중, loginId: " + loginId);

        // 로그인 서비스 호출하여 사용자 인증 수행
        UsersDTO user = UsersService.getInstance().login(loginId, password);
        ModelAndView view = new ModelAndView();

        if (user != null) {
            // 로그인 성공 시 세션 설정
            System.out.println("[LoginController] 로그인 성공 - 사용자: " + user.getNickName() + " -> 세션 설정 시작");
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // 세션 만료 시간 설정 (1분 후 만료)
            session.setAttribute("sessionExpireTime", Instant.now().plus(1, ChronoUnit.MINUTES));
            System.out.println("[LoginController] 세션 만료 시간 설정: 1분 후");

            // 로그인 상태 유지 체크박스 처리
            if ("on".equals(rememberMe)) {
                Cookie cookie = new Cookie("loginId", loginId);
                cookie.setMaxAge(60); // 60초 (1분 동안 유지)
                cookie.setPath("/");
                response.addCookie(cookie);
                System.out.println("[LoginController] 로그인 상태 유지 쿠키 설정 - 유지 시간: 1분");
            }

            // 로그인 성공 시 메인 페이지로 리다이렉트
            System.out.println("[LoginController] 로그인 성공 후 index.jsp로 리다이렉트");
            view.setPath("index.jsp");
            view.setRedirect(true);
        } else {
            // 로그인 실패 시 로그인 페이지로 리다이렉트 (error 파라미터 추가)
            System.out.println("[LoginController] 로그인 실패 - 아이디 또는 비밀번호 불일치, signin.jsp로 리다이렉트");
            view.setPath("signin.jsp?error=invalid");
            view.setRedirect(true);
        }

        return view;
    }
}

// 이 코드는 사용자가 로그인 요청을 했을 때 처리하는 컨트롤러입니다.
// 사용자가 입력한 아이디와 비밀번호로 로그인 시도를 하며, 성공 시 세션과 쿠키를 설정하고 메인 페이지로 이동합니다.
// 로그인 실패 시에는 로그인 페이지로 다시 이동하며, 실패 원인을 나타내는 파라미터를 추가합니다.
// "rememberMe" 옵션에 따라 쿠키를 생성하여 로그인 상태를 유지할 수 있습니다.
// System.out.println()을 통해 각 단계에서 로그를 남겨서 호출 흐름을 명확히 추적할 수 있습니다.