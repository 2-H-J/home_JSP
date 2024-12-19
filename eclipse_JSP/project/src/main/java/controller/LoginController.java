package controller;

import java.io.IOException;

import dto.UsersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UsersService;
import view.ModelAndView;

public class LoginController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("[LoginController] execute() 호출 -> 로그인 처리 시작");

        // 1. 클라이언트로부터 전달된 id와 password 값을 가져오기
        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");

        // 디버깅: 로그인 ID와 비밀번호 출력
        System.out.println("[LoginController] 입력된 값 -> loginId: " + loginId + ", password: " + password);

        // 2. UsersService를 통해 로그인 검증
        System.out.println("[LoginController] UsersService.login() 호출 시작");
        UsersDTO user = UsersService.getInstance().login(loginId, password);
        System.out.println("[LoginController] UsersService.login() 호출 완료");

        // ModelAndView 객체 생성
        ModelAndView view = new ModelAndView();

        // 3. 로그인 성공 처리
        if (user != null) {
            // 사용자 정보 출력 (디버깅용)
            System.out.println("[LoginController] 로그인 성공 -> 사용자 정보 확인");
            System.out.println("userNumber: " + user.getUserNumber());
            System.out.println("loginId: " + user.getLoginId());
            System.out.println("nickName: " + user.getNickName());
            System.out.println("userEmail: " + user.getUserEmail());

            // 세션에 사용자 정보를 저장
            System.out.println("[LoginController] 세션 저장 시작");
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("userNumber", user.getUserNumber()); // userNumber 추가 저장
            System.out.println("[LoginController] 세션에 사용자 정보 저장 완료 -> 닉네임: " + user.getNickName());

            // 성공 시 메인 페이지로 리다이렉트 설정
            view.setPath("index.do");
            view.setRedirect(true);
            System.out.println("[LoginController] 로그인 성공 -> 마이페이지로 리다이렉트 설정 완료");
        } else {
            // 로그인 실패 처리
            System.out.println("[LoginController] 로그인 실패 -> 사용자 정보 없음");
            request.setAttribute("errorMessage", "아이디 또는 비밀번호가 일치하지 않습니다.");
            view.setPath("./loginView.jsp");
            view.setRedirect(false);
        }

        System.out.println("[LoginController] execute() 종료 -> 반환할 ModelAndView: Path=" + view.getPath() + ", Redirect=" + view.isRedirect());
        return view;
    }
}
