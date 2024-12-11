package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

public class MainController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("[MainController] execute() 호출 -> 시작");

        // 요청 URI 출력
        System.out.println("[MainController] 요청 URI: " + request.getRequestURI());

        // ModelAndView 생성 및 초기화
        ModelAndView view = new ModelAndView();
        view.setPath("./index.jsp"); // 이동할 페이지 설정
        view.setRedirect(true); // 리다이렉트 방식 설정
        System.out.println("[MainController] ModelAndView 설정 완료 -> Path: " + view.getPath() + ", Redirect: " + view.isRedirect());

        System.out.println("[MainController] execute() 완료 -> ModelAndView 반환");
        return view;
    }
}
