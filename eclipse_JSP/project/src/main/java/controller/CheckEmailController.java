package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import service.UsersService;
import view.ModelAndView;

public class CheckEmailController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 요청 시작 로그
        System.out.println("[CheckEmailController] execute() 시작");

        JSONObject jsonResponse = new JSONObject(); // JSON 응답 객체 생성
        HttpSession session = request.getSession(); // 세션 객체 가져오기

        try {
            // 이메일 파라미터 확인
            String email = request.getParameter("email");
            System.out.println("[CheckEmailController] 요청받은 이메일: " + email);

            // 입력값 검증
            if (email == null || email.isEmpty() || !email.matches("^[\\w-.]+@[\\w-]+(\\.[\\w-]+)+$")) {
                System.out.println("[CheckEmailController] 유효하지 않은 이메일 입력");
                jsonResponse.put("error", "유효한 이메일을 입력해 주세요.");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json");
                response.getWriter().write(jsonResponse.toString());
                return null;
            }

            // 이메일 존재 여부 확인
            UsersService usersService = UsersService.getInstance();
            boolean isEmailValid = !usersService.isEmailExists(email); // 중복 확인

            // 세션에 이메일 확인 상태 저장
            if (isEmailValid) {
                session.setAttribute("emailChecked", true); // 이메일 확인 성공 상태
            } else {
                session.setAttribute("emailChecked", false); // 이메일 중복 상태
            }

            // JSON 응답 생성
            jsonResponse.put("exists", !isEmailValid);
            System.out.println("[CheckEmailController] 이메일 상태 (중복여부): " + !isEmailValid);

        } catch (Exception e) {
            // 예외 처리 시 오류 메시지 반환
            System.err.println("[CheckEmailController] 오류 발생: " + e.getMessage());
            jsonResponse.put("error", "서버 오류가 발생했습니다. 다시 시도해주세요.");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        // JSON 응답 전송
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
        System.out.println("[CheckEmailController] 응답 전송 완료");

        // 뷰로 이동하지 않음
        return null;
    }
}
