package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardService;
import view.ModelAndView;

public class BoardCommentDeleteController implements Controller {

    // 컨트롤러 클래스는 특정 요청을 처리하고 적절한 응답을 생성하는 역할을 합니다.
    // 여기서는 댓글 삭제 요청을 처리합니다.

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 메서드 시그니처 설명:
        // - HttpServletRequest: 클라이언트 요청 정보를 담고 있는 객체
        // - HttpServletResponse: 서버에서 클라이언트로의 응답을 작성하는 객체
        // - ModelAndView: 뷰(View)와 모델(Model) 데이터를 함께 담아 반환하는 객체
        // - throws ServletException, IOException: 서블릿 및 입출력 처리 중 발생할 수 있는 예외를 선언

        // [INFO] 댓글 삭제 요청 처리 시작
        System.out.println("[INFO] 댓글 삭제 요청 처리 시작");

        // 1. 요청 파라미터에서 댓글 번호(cno)와 게시글 번호(bno) 추출
        // - 클라이언트로부터 전달받은 댓글의 고유 번호(cno)와 게시글의 고유 번호(bno)를 추출합니다.
        // - 추출된 값은 댓글 삭제 및 리다이렉트 경로 설정에 사용됩니다.
        System.out.println("[INFO] 요청 파라미터 추출 중");
        int cno = Integer.parseInt(request.getParameter("cno")); // 댓글 번호를 요청에서 추출
        System.out.println("[DEBUG] 추출된 댓글 번호(cno): " + cno);
        int bno = Integer.parseInt(request.getParameter("bno")); // 게시글 번호를 요청에서 추출
        System.out.println("[DEBUG] 추출된 게시글 번호(bno): " + bno);

        // 2. 댓글 삭제 처리 로직 실행
        // - BoardService의 deleteBoardComment 메서드를 호출하여 특정 댓글을 삭제합니다.
        // - 비즈니스 로직은 서비스 계층(BoardService)에 위임하여 실행됩니다.
        System.out.println("[INFO] 댓글 삭제 처리 시작");
        BoardService.getInstance().deleteBoardComment(cno); // 댓글 삭제 로직 실행
        System.out.println("[INFO] 댓글 삭제 처리 완료");

        // 3. 리다이렉트 경로 설정
        // - 삭제된 댓글이 속한 게시글의 상세 보기 페이지로 이동하도록 리다이렉트 경로를 설정합니다.
        // - ModelAndView 객체를 사용하여 뷰 경로와 리다이렉트 여부를 설정합니다.
        System.out.println("[INFO] 리다이렉트 경로 설정 중");
        ModelAndView view = new ModelAndView(); // ModelAndView 객체 생성
        view.setPath("./boardView.do?bno=" + bno); // 리다이렉트 경로 설정 (게시글 상세 보기 URL)
        System.out.println("[DEBUG] 설정된 리다이렉트 경로: ./boardView.do?bno=" + bno);
        view.setRedirect(true); // 리다이렉트 플래그 설정 (true로 설정하여 리다이렉트 동작 수행)
        System.out.println("[INFO] 리다이렉트 경로 설정 완료");

        // 4. 처리 결과 반환
        // - ModelAndView 객체를 반환하여 컨트롤러 요청 처리를 종료합니다.
        // - 클라이언트는 지정된 URL로 리다이렉트됩니다.
        System.out.println("[INFO] 댓글 삭제 요청 처리 완료");
        return view;
    }

}
