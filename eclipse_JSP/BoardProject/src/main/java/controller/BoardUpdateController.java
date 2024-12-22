package controller;

import java.io.IOException;

import dto.BoardDTO; // 게시글 정보를 저장하기 위한 데이터 전송 객체 (DTO)
import jakarta.servlet.ServletException; // 서블릿 관련 예외를 처리하는 클래스
import jakarta.servlet.http.HttpServletRequest; // 클라이언트 요청 정보를 담는 객체
import jakarta.servlet.http.HttpServletResponse; // 클라이언트로 응답 데이터를 전송하는 객체
import service.BoardService; // 비즈니스 로직 처리를 위한 서비스 계층
import view.ModelAndView; // 뷰(View)와 모델(Model)을 포함하는 객체

// BoardUpdateController 클래스는 게시글 수정 요청을 처리하는 컨트롤러입니다.
// 클라이언트가 수정한 게시글 데이터를 받아 데이터베이스를 업데이트한 후,
// 수정된 게시글 상세 페이지로 이동합니다.
public class BoardUpdateController implements Controller {

    // 이 메서드는 클라이언트 요청을 처리하고 결과를 반환합니다.
    // - HttpServletRequest: 클라이언트 요청 정보를 포함하는 객체
    //   (예: 요청 파라미터, 세션 정보 등)
    // - HttpServletResponse: 서버가 클라이언트로 보낼 응답 정보를 포함하는 객체
    //   (예: 응답 데이터, 상태 코드 등)
    // - ModelAndView: 요청 처리 결과와 이동할 뷰(View)를 설정하는 객체
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // [INFO] 게시글 수정 요청 처리 시작
        System.out.println("[INFO] 게시글 수정 요청 처리 시작");

        // 1. 요청 파라미터에서 게시글 정보 추출
        // - 클라이언트가 전송한 수정된 게시글 제목(title), 내용(content), 게시글 번호(bno)를 가져옵니다.
        String title = request.getParameter("title"); // 수정된 제목
        String content = request.getParameter("content"); // 수정된 내용
        int bno = Integer.parseInt(request.getParameter("bno")); // 수정할 게시글 번호
        System.out.println("[DEBUG] 수정된 제목(title): " + title);
        System.out.println("[DEBUG] 수정된 내용(content): " + content);
        System.out.println("[DEBUG] 게시글 번호(bno): " + bno);

        // 2. BoardDTO 객체 생성 및 데이터 설정
        // - BoardDTO 객체는 게시글 정보를 저장하는 데이터 전송 객체입니다.
        // - 수정된 제목, 내용, 게시글 번호를 DTO 객체에 설정합니다.
        BoardDTO dto = new BoardDTO();
        dto.setTitle(title); // 제목 설정
        dto.setContent(content); // 내용 설정
        dto.setBno(bno); // 게시글 번호 설정
        System.out.println("[DEBUG] 생성된 DTO: " + dto);

        // 3. 게시글 수정 처리
        // - BoardService의 updateBoard 메서드를 호출하여 데이터베이스에서 해당 게시글을 수정합니다.
        System.out.println("[INFO] 게시글 수정 처리 시작");
        BoardService.getInstance().updateBoard(dto); // 게시글 수정 로직 실행
        System.out.println("[INFO] 게시글 수정 처리 완료");

        // 4. 수정된 게시글 상세 페이지로 이동
        // - ModelAndView 객체를 생성하여 수정된 게시글 번호를 포함한 뷰 경로를 설정합니다.
        // - setRedirect(true): 리다이렉트 방식으로 페이지를 이동합니다.
        System.out.println("[INFO] 수정된 게시글 상세 페이지로 이동 설정 중");
        ModelAndView view = new ModelAndView();
        view.setPath("./boardView.do?bno=" + bno); // 수정된 게시글 상세 보기 URL 설정
        view.setRedirect(true); // 리다이렉트 플래그 설정
        System.out.println("[DEBUG] 설정된 뷰 경로(path): ./boardView.do?bno=" + bno);

        // 5. 처리 결과 반환
        // - ModelAndView 객체를 반환하여 요청 처리를 종료합니다.
        System.out.println("[INFO] 게시글 수정 요청 처리 완료");
        return view;
    }
}
