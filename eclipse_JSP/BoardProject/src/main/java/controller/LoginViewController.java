package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

public class LoginViewController implements Controller {

    // LoginViewController 클래스는 로그인 화면 요청을 처리하는 컨트롤러입니다.
    // 클라이언트가 로그인 화면으로 이동을 요청하면 해당 요청을 처리하고
    // "login.jsp" 뷰(View)로 이동하도록 설정합니다.

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // execute 메서드는 클라이언트 요청을 처리하고 처리 결과를 반환합니다.
        // - HttpServletRequest: 클라이언트 요청 정보를 담고 있는 객체
        // - HttpServletResponse: 서버가 클라이언트로 보낼 응답 정보를 담고 있는 객체
        // - throws ServletException, IOException: 메서드 실행 중 발생할 수 있는 예외를 호출자에게 전달

        // ModelAndView 객체 생성
        // 클라이언트 요청의 처리 결과와 이동할 뷰(View)를 포함하는 객체
        ModelAndView view = new ModelAndView();

        // 리다이렉트 설정
        // false로 설정하여 포워딩 방식으로 이동
        // - 리다이렉트는 클라이언트에게 새로운 요청을 보내도록 지시하지만,
        //   포워딩은 서버 내부에서 요청을 처리하며 클라이언트는 URL이 변경되지 않습니다.
        view.setRedirect(false);

        // 이동할 뷰 경로 설정
        // "login.jsp"는 로그인 화면을 렌더링하는 JSP 파일의 경로
        view.setPath("login.jsp");

        // 처리 결과 반환
        // ModelAndView 객체를 반환하여 요청 처리를 완료합니다.
        return view;
    }
}
