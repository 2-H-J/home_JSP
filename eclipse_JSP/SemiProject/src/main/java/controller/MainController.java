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

public class MainController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    HttpSession session = request.getSession(false);
	    UsersDTO user = null;

	    System.out.println("[MainController] 세션 확인 시작");

	    // 세션에서 사용자 정보 확인
	    if (session == null || session.getAttribute("user") == null) {
	        System.out.println("[MainController] 세션 없음 또는 사용자 정보 없음 -> 쿠키 확인 시작");

	        // 쿠키 확인
	        Cookie[] cookies = request.getCookies();
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                if ("loginId".equals(cookie.getName())) {
	                    String loginId = cookie.getValue();
	                    System.out.println("[MainController] 쿠키에서 loginId 확인: " + loginId);

	                    // 사용자 복구
	                    user = UsersService.getInstance().getUserByLoginId(loginId);
	                    if (user != null) {
	                        session = request.getSession(true); // 새로운 세션 생성
	                        session.setAttribute("user", user);
	                        session.setAttribute("sessionExpireTime", Instant.now().plus(10, ChronoUnit.MINUTES));
	                        session.setAttribute("rememberMe", true);
	                        System.out.println("[MainController] 사용자 복구 성공: " + user.getNickName());
	                    } else {
	                        System.out.println("[MainController] 쿠키 loginId에 해당하는 사용자 없음");
	                    }
	                    break;
	                }
	            }
	        } else {
	            System.out.println("[MainController] 쿠키 없음");
	        }
	    } else {
	        // 세션이 유효하면 사용자 정보 가져오기
	        user = (UsersDTO) session.getAttribute("user");
	        System.out.println("[MainController] 세션에서 사용자 정보 확인: " + (user != null ? user.getNickName() : "null"));
	    }

	    // 사용자 정보를 request에 설정
	    request.setAttribute("user", user);

	    if (user == null) {
	        System.out.println("[MainController] 사용자 정보 없음 -> 로그인 버튼 노출 예정");
	    } else {
	        System.out.println("[MainController] 사용자 정보 있음 -> 닉네임: " + user.getNickName());
	    }

	    // JSP로 이동
	    ModelAndView view = new ModelAndView();
	    view.setPath("./index.jsp");
	    view.setRedirect(false);
	    return view;
	}

}
