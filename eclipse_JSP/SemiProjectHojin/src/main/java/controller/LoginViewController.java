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
        System.out.println("LoginViewController: execute 메서드 시작");

        // ModelAndView 생성 및 초기화
        ModelAndView view = new ModelAndView();
        view.setRedirect(false);
        view.setPath("loginView.jsp");
        System.out.println("LoginViewController: 경로를 login.jsp로 설정 (redirect = false)");

        System.out.println("LoginViewController: execute 메서드 완료");
        return view;
    }
}
