package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardsService;
import view.ModelAndView;

/**
 * DeleteBoardByPostNumber 클래스는 게시글 번호(postNumber)를 기반으로 게시글을 삭제하는 요청을 처리하는 컨트롤러입니다.
 */
public class DeleteBoardByPostNumber implements Controller {

    /**
     * 클라이언트의 요청을 처리하여 게시글을 삭제하고, 처리 결과에 따라 적절한 경로로 리다이렉트하거나 포워딩합니다.
     * 
     * @param request  클라이언트로부터 받은 HTTP 요청 객체
     * @param response 클라이언트로 반환할 HTTP 응답 객체
     * @return ModelAndView 객체 (뷰 정보와 리다이렉트 여부를 포함)
     * @throws ServletException 요청 처리 중 서블릿 관련 예외가 발생할 경우
     * @throws IOException      요청 처리 중 입출력 관련 예외가 발생할 경우
     */
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 게시글 번호를 파라미터에서 가져오기
        String postNumberStr = request.getParameter("postNumber");
        System.out.println("[DeleteBoardByPostNumber] 요청받은 postNumber: " + postNumberStr);

        // postNumber를 정수로 변환
        int postNumber;
        try {
            postNumber = Integer.parseInt(postNumberStr);
        } catch (NumberFormatException e) {
            System.out.println("[DeleteBoardByPostNumber] 잘못된 postNumber 형식: " + postNumberStr);
            response.getWriter().println("잘못된 게시글 번호 형식입니다.");
            ModelAndView errorView = new ModelAndView();
            errorView.setPath("/errorPage.jsp");
            errorView.setRedirect(false);
            return errorView;
        }

        // 게시글 삭제 서비스 호출
        System.out.println("[DeleteBoardByPostNumber] 게시글 삭제 시도 - postNumber: " + postNumber);
        int result = BoardsService.getInstance().deleteBoardByPostNumber(postNumber);

        // 반환할 ModelAndView 객체 생성
        ModelAndView view = new ModelAndView();

        if (result > 0) { // 삭제 성공
            System.out.println("[DeleteBoardByPostNumber] 게시글 삭제 성공 - postNumber: " + postNumber);
            // 게시글 목록으로 리다이렉트 설정
            view.setPath("./allBoard.do");
            view.setRedirect(true);
        } else { // 삭제 실패
            System.out.println("[DeleteBoardByPostNumber] 게시글 삭제 실패 - 존재하지 않는 게시글 번호: " + postNumber);
            // 실패 메시지 출력
            response.getWriter().println("게시글 삭제 실패. 존재하지 않는 게시글 번호입니다.");
            // 에러 페이지로 포워딩 설정
            view.setPath("/errorPage.jsp");
            view.setRedirect(false);
        }

        return view;
    }
}
