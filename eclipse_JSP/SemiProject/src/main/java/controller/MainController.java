package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

/**
 * MainController 클래스는 메인 페이지로 이동하는 요청을 처리하는 컨트롤러입니다.
 * 주로 초기 요청이나 기본 경로 요청에 대해 메인 페이지(index.jsp)로 리다이렉트합니다.
 */
public class MainController implements Controller {

    /**
     * 클라이언트 요청을 처리하여 메인 페이지로 리다이렉트합니다.
     *
     * @param request  클라이언트로부터 받은 HTTP 요청 객체
     * @param response 클라이언트로 반환할 HTTP 응답 객체
     * @return ModelAndView 객체 (뷰 정보와 리다이렉트 여부를 포함)
     * @throws ServletException 요청 처리 중 서블릿 관련 예외가 발생할 경우
     * @throws IOException      요청 처리 중 입출력 관련 예외가 발생할 경우
     */
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("[MainController] execute() 호출 -> 메인 페이지로 이동");

        // ModelAndView 객체 생성 및 설정
        ModelAndView view = new ModelAndView();
        view.setPath("./index.jsp"); // 메인 페이지 경로 설정
        view.setRedirect(true); // 리다이렉트 설정
        System.out.println("[MainController] 리다이렉트 설정 -> 경로: " + view.getPath());

        return view;
    }
}
