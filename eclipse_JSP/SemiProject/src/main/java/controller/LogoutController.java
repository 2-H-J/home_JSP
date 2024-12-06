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
	    HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.invalidate();
	        System.out.println("[LogoutController] 세션 무효화 완료");
	    }

	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if ("loginId".equals(cookie.getName())) {
	                cookie.setMaxAge(0); // 쿠키 삭제
	                cookie.setPath("/");
	                response.addCookie(cookie);
	                System.out.println("[LogoutController] 로그인 쿠키 삭제 완료");
	            }
	        }
	    }

	    ModelAndView view = new ModelAndView("./signin.jsp", true);
	    return view;
	}

}
