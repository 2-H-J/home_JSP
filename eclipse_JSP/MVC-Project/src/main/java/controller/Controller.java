package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

// 모든 컨트롤러가 구현해야 하는 공통 인터페이스
// MVC 패턴의 컨트롤러 역할을 수행하는 클래스들이 반드시 이 인터페이스를 구현하도록 함
public interface Controller {

    // 모든 컨트롤러가 구현해야 하는 메소드 정의
    // 이 메소드는 HTTP 요청(request)과 응답(response)을 처리하고, ModelAndView 객체를 반환함
    // ServletException, IOException을 던지도록 정의되어 있어 HTTP 요청/응답 과정에서 발생할 수 있는 예외를 처리할 수 있음
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}
