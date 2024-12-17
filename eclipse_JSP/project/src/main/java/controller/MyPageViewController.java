package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dto.UsersDTO;
import view.ModelAndView;

/**
 * MyPageViewController 클래스는 마이페이지로 이동하는 요청을 처리하는 컨트롤러 역할을 수행한다.
 * Controller 인터페이스를 구현하여 특정 요청을 처리하고, 그 결과를 반환한다.
 */
public class MyPageViewController implements Controller {

    /**
     * execute 메서드는 마이페이지로 이동하는 로직을 처리하고, 처리 결과를 기반으로 사용자에게 적합한 화면(view)을 반환한다.
     *
     * @param request  클라이언트로부터 들어온 HTTP 요청 객체
     * @param response 클라이언트로 전송할 HTTP 응답 객체
     * @return ModelAndView 객체 (화면 정보 및 전환 방식을 포함)
     * @throws ServletException 서블릿 처리 중 발생할 수 있는 예외
     * @throws IOException      입출력 처리 중 발생할 수 있는 예외
     */
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("[MyPageViewController] execute() 호출 -> 시작");

        // 1. 세션에서 사용자 정보 가져오기
        UsersDTO user = (UsersDTO) request.getSession().getAttribute("user");

        // 2. 세션 정보 검증
        if (user == null) {
            System.out.println("[MyPageViewController] 세션에 사용자 정보가 없습니다. -> 로그인 페이지로 리다이렉트");
            ModelAndView view = new ModelAndView();
            view.setPath("./loginView.jsp");
            view.setRedirect(true); // 로그인 페이지로 리다이렉트
            return view;
        } else {
            // 사용자 정보 확인 로그
            System.out.println("[MyPageViewController] 세션 사용자 정보 확인 -> "
                + "이름: " + user.getUserName()
                + ", 아이디: " + user.getLoginId()
                + ", 닉네임: " + user.getNickName()
                + ", 이메일: " + user.getUserEmail());
        }

        // 3. 사용자 정보를 request에 설정
        request.setAttribute("user", user);
        System.out.println("[MyPageViewController] 사용자 정보를 request에 설정 완료");

        // 4. 마이페이지 JSP로 포워드
        ModelAndView view = new ModelAndView();
        view.setPath("./mypageView.jsp"); // 마이페이지 JSP 경로 설정
        view.setRedirect(false); // 포워드 방식 설정

        System.out.println("[MyPageViewController] 마이페이지로 포워드 설정 완료 -> Path: " + view.getPath());
        System.out.println("[MyPageViewController] execute() 완료 -> ModelAndView 반환");

        return view;
    }
}
