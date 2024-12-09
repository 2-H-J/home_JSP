package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import view.ModelAndView;

/**
 * LogoutController 클래스는 사용자의 로그아웃 요청을 처리합니다.
 * 세션 무효화 및 로그인 상태 유지 쿠키 삭제를 수행합니다.
 */
public class LogoutController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("[LogoutController] execute() 호출 -> 로그아웃 처리 시작");

        // 현재 세션 가져오기 (세션이 없으면 null 반환)
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 세션 무효화
            session.invalidate();
            System.out.println("[LogoutController] 세션 무효화 완료");
        } else {
            System.out.println("[LogoutController] 활성 세션 없음");
        }

        // 쿠키 확인 및 삭제
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("loginId".equals(cookie.getName())) {
                    // "loginId" 쿠키를 삭제
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    System.out.println("[LogoutController] 로그인 상태 유지 쿠키 삭제 완료");
                }
            }
        } else {
            System.out.println("[LogoutController] 쿠키 없음");
        }

        // 로그아웃 후 로그인 페이지로 리다이렉트
        ModelAndView view = new ModelAndView();
        view.setPath("signin.jsp"); // 로그인 페이지 경로 설정
        view.setRedirect(true); // 리다이렉트 설정
        System.out.println("[LogoutController] 로그아웃 완료 -> signin.jsp로 리다이렉트");
        return view;
    }
}
