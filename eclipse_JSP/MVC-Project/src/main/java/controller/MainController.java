package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

// 메인 페이지로 이동하는 기능을 담당하는 컨트롤러 클래스
public class MainController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ModelAndView 객체 생성 - 페이지 이동과 데이터를 설정하기 위한 객체
        ModelAndView view = new ModelAndView();

        // 이동할 페이지 경로 설정
        // "./index.jsp"는 웹 애플리케이션의 루트 경로 아래에 위치한 "index.jsp" 페이지를 가리킴
        view.setPath("./index.jsp");

        // 페이지 이동 방식을 리다이렉트 방식으로 설정
        // 리다이렉트는 클라이언트 측에서 새로운 요청을 보내도록 지시하는 방식으로 URL이 변경됨
        // 예를 들어 사용자가 특정 작업 후 메인 페이지로 이동할 때 사용
        view.setRedirect(true);

        // 설정한 ModelAndView 객체를 반환
        // DispatcherServlet이 이 반환된 ModelAndView를 사용하여 페이지 이동 및 데이터를 처리하게 됨
        return view;
    }
}
