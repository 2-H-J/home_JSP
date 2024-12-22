package controller;

import java.io.IOException;

import dto.BoardDTO; // 게시글 정보를 담는 데이터 전송 객체 (DTO)
import jakarta.servlet.ServletException; // 서블릿 관련 예외를 처리하는 클래스
import jakarta.servlet.http.HttpServletRequest; // 클라이언트 요청 정보를 담는 객체
import jakarta.servlet.http.HttpServletResponse; // 클라이언트로 응답 데이터를 전송하는 객체
import service.BoardService; // 비즈니스 로직 처리를 위한 서비스 계층
import view.ModelAndView; // 뷰(View)와 모델(Model)을 포함하는 객체

// BoardUpdateViewController 클래스는 게시글 수정 화면 요청을 처리하는 컨트롤러입니다.
// 클라이언트가 수정하고자 하는 게시글 번호를 받아 해당 게시글 데이터를 조회하여
// 수정 화면으로 전달합니다.
public class BoardUpdateViewController implements Controller {

    // execute 메서드는 클라이언트 요청을 처리하고 결과를 반환합니다.
    // - HttpServletRequest: 클라이언트 요청 정보를 포함하는 객체
    //   (예: 요청 파라미터, 세션 정보 등)
    // - HttpServletResponse: 서버가 클라이언트로 보낼 응답 정보를 포함하는 객체
    //   (예: 응답 데이터, 상태 코드 등)
    // - ModelAndView: 요청 처리 결과와 이동할 뷰(View)를 설정하는 객체
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // [INFO] 게시글 수정 화면 요청 처리 시작
        System.out.println("[INFO] 게시글 수정 화면 요청 처리 시작");

        // 1. 요청 파라미터에서 게시글 번호(bno) 추출
        // - 클라이언트가 수정하고자 하는 게시글의 고유 번호를 가져옵니다.
        int bno = Integer.parseInt(request.getParameter("bno")); // 요청된 게시글 번호
        System.out.println("[DEBUG] 게시글 번호(bno): " + bno);

        // 2. 게시글 데이터 조회
        // - BoardService의 selectBoard 메서드를 호출하여 게시글 데이터를 조회합니다.
        // - 조회된 데이터는 BoardDTO 객체로 반환됩니다.
        System.out.println("[INFO] 게시글 데이터 조회 중");
        BoardDTO dto = BoardService.getInstance().selectBoard(bno); // 게시글 데이터 조회
        System.out.println("[DEBUG] 조회된 게시글 데이터(dto): " + dto);

        // 3. ModelAndView 객체 생성 및 데이터 추가
        // - ModelAndView 객체를 생성하여 조회된 게시글 데이터를 추가합니다.
        // - 뷰(View) 경로를 "board_update_view.jsp"로 설정하여 수정 화면을 렌더링합니다.
        System.out.println("[INFO] ModelAndView 객체 생성 및 데이터 추가 중");
        ModelAndView view = new ModelAndView();
        view.setPath("board_update_view.jsp"); // 이동할 뷰(View) 경로 설정
        view.addObject("board", dto); // 게시글 데이터 추가
        System.out.println("[DEBUG] 설정된 뷰 경로(path): board_update_view.jsp");

        // 4. 처리 결과 반환
        // - ModelAndView 객체를 반환하여 클라이언트 요청 처리를 종료합니다.
        System.out.println("[INFO] 게시글 수정 화면 요청 처리 완료");
        return view;
    }
}
