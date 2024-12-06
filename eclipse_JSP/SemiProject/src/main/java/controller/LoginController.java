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
        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        System.out.println("[LoginController] execute() 호출 -> 로그인 시도 중, loginId: " + loginId);

        UsersDTO user = UsersService.getInstance().login(loginId, password);
        ModelAndView view = new ModelAndView();

        if (user != null) {
            System.out.println("[LoginController] 로그인 성공 - 사용자: " + user.getNickName() + " -> 세션 설정 시작");
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            session.setAttribute("sessionExpireTime", Instant.now().plus(10, ChronoUnit.SECONDS));
            System.out.println("[LoginController] 세션 만료 시간 설정: 1분 후");

            
            if ("on".equals(rememberMe)) {
                Cookie cookie = new Cookie("loginId", loginId);
                cookie.setMaxAge(60 * 60 * 24 * 7); // 7일 동안 유지
                cookie.setPath("/");
                response.addCookie(cookie);

                // 세션에 "로그인 상태 유지" 정보 저장
                session.setAttribute("rememberMe", true);
                System.out.println("[LoginController] 로그인 상태 유지 설정 완료 - 쿠키와 세션 저장");
            } else {
                session.setAttribute("rememberMe", false);
                System.out.println("[LoginController] 로그인 상태 유지 비활성화");
            }



            System.out.println("[LoginController] 로그인 성공 후 index.jsp로 리다이렉트");
            view.setPath("index.jsp");
            view.setRedirect(true);
        } else {
            System.out.println("[LoginController] 로그인 실패 - 아이디 또는 비밀번호 불일치, signin.jsp로 리다이렉트");
            view.setPath("signin.jsp?error=invalid");
            view.setRedirect(true);
        }

        return view;
    }
}
