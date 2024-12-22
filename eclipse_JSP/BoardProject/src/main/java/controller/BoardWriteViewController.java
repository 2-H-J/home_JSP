package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

public class BoardWriteViewController implements Controller {

    // 이 클래스는 게시글 작성 화면 요청을 처리하는 컨트롤러입니다.
    // 클라이언트가 게시글 작성 화면으로 이동하려는 요청을 처리하여
    // 해당 뷰(View) 페이지("board_write_view.jsp")로 이동합니다.

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // [INFO] 게시글 작성 화면 요청 처리 시작
        System.out.println("[INFO] 게시글 작성 화면 요청 처리 시작");

        // 1. ModelAndView 객체 생성
        // - ModelAndView는 요청 처리 결과를 포함하여 클라이언트를 어떤 뷰(View)로 이동시킬지 설정하는 객체입니다.
        ModelAndView view = new ModelAndView();

        // 2. 뷰(View) 경로 설정
        // - setPath() 메서드는 클라이언트가 이동할 JSP 경로를 설정합니다.
        // - "board_write_view.jsp"는 게시글 작성 화면을 렌더링하는 JSP 파일 경로입니다.
        view.setPath("board_write_view.jsp");
        System.out.println("[DEBUG] 설정된 뷰 경로(path): board_write_view.jsp");

        // 3. 처리 결과 반환
        // - ModelAndView 객체를 반환하여 클라이언트를 설정된 JSP 페이지로 이동시킵니다.
        System.out.println("[INFO] 게시글 작성 화면 요청 처리 완료");
        return view;
    }
}
