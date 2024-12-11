package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

public class LoginViewController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("[LoginViewController] execute() 호출 -> 시작");

        // 요청 URI 출력
        System.out.println("[LoginViewController] 요청 URI: " + request.getRequestURI());

        // ModelAndView 생성 및 초기화
        ModelAndView view = new ModelAndView();
        view.setRedirect(false); // 포워드 방식
        view.setPath("loginView.jsp"); // 이동할 경로 설정
        System.out.println("[LoginViewController] ModelAndView 설정 완료 -> Path: " + view.getPath() + ", Redirect: " + view.isRedirect());

        System.out.println("[LoginViewController] execute() 완료 -> ModelAndView 반환");
        return view;
    }
}

