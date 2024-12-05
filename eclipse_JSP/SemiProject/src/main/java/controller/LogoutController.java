package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import view.ModelAndView;

public class LogoutController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("[LogoutController] execute() 호출 -> 로그아웃 처리 시작");
        
        // 세션 무효화
        HttpSession session = request.getSession(false);
        if (session != null) {
            System.out.println("[LogoutController] 세션 무효화 처리 -> 세션이 존재하므로 무효화 진행");
            session.invalidate(); // 세션 무효화
        } else {
            System.out.println("[LogoutController] 세션이 존재하지 않음 -> 무효화 생략");
        }

        // 로그인 상태 유지 쿠키 삭제
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("loginId".equals(cookie.getName())) {
                    System.out.println("[LogoutController] 로그인 상태 유지 쿠키 삭제 처리 -> 쿠키 이름: " + cookie.getName());
                    cookie.setMaxAge(0); // 쿠키 삭제
                    cookie.setPath("/"); // 루트 경로에서 삭제
                    response.addCookie(cookie);
                }
            }
        } else {
            System.out.println("[LogoutController] 쿠키가 존재하지 않음 -> 쿠키 삭제 생략");
        }

        // 로그아웃 후 로그인 페이지로 리다이렉트
        System.out.println("[LogoutController] 로그아웃 후 로그인 페이지로 리다이렉트 -> signin.jsp");
        ModelAndView view = new ModelAndView();
        view.setPath("signin.jsp"); // 로그인 페이지
        view.setRedirect(true); // 리다이렉트 설정
        return view;
    }
}

// 이 코드는 사용자가 로그아웃을 요청했을 때 처리하는 컨트롤러입니다.
// 세션을 무효화하고, 로그인 상태 유지 쿠키가 있다면 삭제합니다.
// 로그아웃 후에는 로그인 페이지로 리다이렉트하여 사용자에게 로그아웃이 완료되었음을 알립니다.
// System.out.println()을 통해 각 단계에서 로그를 남겨서 호출 흐름을 명확히 추적할 수 있습니다.
