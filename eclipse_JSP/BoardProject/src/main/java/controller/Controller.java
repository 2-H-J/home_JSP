package controller;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

public interface Controller {

    // execute 메서드는 모든 컨트롤러에서 구현해야 하는 메서드입니다.
    // - 이 메서드는 클라이언트 요청(HttpServletRequest)을 처리하고,
    //   그 결과를 담은 ModelAndView 객체를 반환합니다.
    // - HttpServletResponse는 서버가 클라이언트로 응답 데이터를 보낼 때 사용됩니다.
    // - ServletException 및 IOException은 서블릿 처리 중 발생할 수 있는 예외를 선언하여 호출자에게 알립니다.

    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}
