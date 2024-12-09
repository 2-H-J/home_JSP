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

/**
 * LoginController 클래스는 사용자의 로그인 요청을 처리합니다.
 * 사용자 인증 및 세션/쿠키 관리와 관련된 작업을 수행합니다.
 */
public class LoginController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 요청으로부터 로그인 아이디와 비밀번호, 로그인 상태 유지 여부를 가져옴
        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        System.out.println("[LoginController] execute() 호출 -> 로그인 시도 중, loginId: " + loginId);

        // 사용자 인증 처리
        UsersDTO user = UsersService.getInstance().login(loginId, password);
        ModelAndView view = new ModelAndView();

        if (user != null) {
            // 로그인 성공 시 세션 설정
            System.out.println("[LoginController] 로그인 성공 - 사용자: " + user.getNickName() + " -> 세션 설정 시작");
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // 세션 만료 시간 설정 (기본 10초)
            session.setAttribute("sessionExpireTime", Instant.now().plus(10, ChronoUnit.SECONDS));
            System.out.println("[LoginController] 세션 만료 시간 설정: 10초 후");
            
            /*
            10초	session.setAttribute("sessionExpireTime", Instant.now().plus(10, ChronoUnit.SECONDS));
			10분	session.setAttribute("sessionExpireTime", Instant.now().plus(10, ChronoUnit.MINUTES));
			1시간	session.setAttribute("sessionExpireTime", Instant.now().plus(1, ChronoUnit.HOURS));
			1일		session.setAttribute("sessionExpireTime", Instant.now().plus(1, ChronoUnit.DAYS));
            */

            // 로그인 상태 유지 설정
            if ("on".equals(rememberMe)) {
                Cookie cookie = new Cookie("loginId", loginId);
                cookie.setMaxAge(20); // 20초 동안 쿠키 유지
                cookie.setPath("/");
                response.addCookie(cookie);
                
                /*
            	20초	cookie.setMaxAge(20);
				1분		cookie.setMaxAge(60);
				10분	cookie.setMaxAge(600);
				30분	cookie.setMaxAge(1800);
				1시간	cookie.setMaxAge(3600);
				12시간	cookie.setMaxAge(60 * 60 * 12);
				1일		cookie.setMaxAge(60 * 60 * 24);
				3일		cookie.setMaxAge(60 * 60 * 24 * 3);
				7일		cookie.setMaxAge(60 * 60 * 24 * 7);
                */

                // 세션에 "로그인 상태 유지" 정보 저장
                session.setAttribute("rememberMe", true);
                System.out.println("[LoginController] 로그인 상태 유지 설정 완료 - 쿠키와 세션 저장");
            } else {
                session.setAttribute("rememberMe", false);
                System.out.println("[LoginController] 로그인 상태 유지 비활성화");
            }

            // 로그인 성공 후 메인 페이지로 리다이렉트
            System.out.println("[LoginController] 로그인 성공 후 index.jsp로 리다이렉트");
            view.setPath("index.jsp");
            view.setRedirect(true);
        } else {
            // 로그인 실패 처리 (아이디 또는 비밀번호 불일치)
            System.out.println("[LoginController] 로그인 실패 - 아이디 또는 비밀번호 불일치, signin.jsp로 리다이렉트");
            view.setPath("signin.jsp?error=invalid");
            view.setRedirect(true);
        }

        return view;
    }
}
