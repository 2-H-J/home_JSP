package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

/**
 * LoginViewController 클래스는 로그인 화면으로 이동하는 요청을 처리하는 컨트롤러 역할을 수행한다.
 * Controller 인터페이스를 구현하여 특정 요청을 처리하고, 그 결과를 반환한다.
 */
public class LoginViewController implements Controller {

    /**
     * execute 메서드는 로그인 화면으로 이동하는 로직을 처리하고, 처리 결과를 기반으로 사용자에게 적합한 화면(view)을 반환한다.
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
        System.out.println("[LoginViewController] execute() 호출 -> 시작");

        // 요청 URI 출력
        System.out.println("[LoginViewController] 요청 URI: " + request.getRequestURI());

        // ModelAndView 생성 및 초기화
        ModelAndView view = new ModelAndView();
        view.setRedirect(false); // 포워드 방식으로 설정
        view.setPath("./loginView.jsp"); // 이동할 경로 설정
        System.out.println("[LoginViewController] ModelAndView 설정 완료 -> Path: " + view.getPath() + ", Redirect: " + view.isRedirect());

        System.out.println("[LoginViewController] execute() 완료 -> ModelAndView 반환");
        return view;
    }
}
