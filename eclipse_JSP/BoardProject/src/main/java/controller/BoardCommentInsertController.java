package controller;

import java.io.IOException;

import dto.BoardCommentDTO;
import dto.BoardMemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardService;
import view.ModelAndView;

public class BoardCommentInsertController implements Controller {

    // 이 메서드는 사용자의 요청(HttpServletRequest)과 응답(HttpServletResponse)을 처리합니다.
    // - HttpServletRequest: 클라이언트(웹 브라우저)로부터 전달된 데이터를 포함하는 객체입니다.
    //   예: 폼 데이터, URL 파라미터, 쿠키 등
    // - HttpServletResponse: 서버에서 클라이언트로 응답을 보낼 때 사용되는 객체입니다.
    //   예: HTML, JSON 데이터, 리다이렉트 응답 등
    // - ModelAndView: 컨트롤러가 처리한 데이터를 뷰(View)에 전달하고, 이동할 페이지(View의 경로)를 설정하는 객체입니다.
    //   1. `setPath()`: 이동할 페이지의 경로를 설정합니다.
    //   2. `setRedirect()`: 리다이렉트를 수행할지 여부를 설정합니다.
    // - ServletException, IOException: 서블릿 및 입출력 작업에서 발생할 수 있는 예외를 선언하여 호출자에게 알립니다.

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // [INFO] 댓글 추가 요청 처리 시작
        System.out.println("[INFO] 댓글 추가 요청 처리 시작");

        // 1. 요청 파라미터에서 댓글 내용과 게시글 번호 추출
        // - 클라이언트로부터 전달된 댓글 내용(comment)과 게시글 번호(bno)를 가져옵니다.
        System.out.println("[INFO] 요청 파라미터 추출 중");
        String content = request.getParameter("comment"); // 댓글 내용 추출
        System.out.println("[DEBUG] 추출된 댓글 내용: " + content);
        int bno = Integer.parseInt(request.getParameter("bno")); // 게시글 번호 추출
        System.out.println("[DEBUG] 추출된 게시글 번호(bno): " + bno);

        // 2. 세션에서 사용자 ID 추출
        // - 현재 로그인된 사용자의 정보를 세션에서 가져옵니다.
        // - BoardMemberDTO 객체로 변환하여 사용자 ID를 추출합니다.
        System.out.println("[INFO] 사용자 ID 추출 중");
        String id = ((BoardMemberDTO) request.getSession().getAttribute("user")).getId();
        System.out.println("[DEBUG] 추출된 사용자 ID: " + id);

        // 3. BoardCommentDTO 객체 생성
        // - 게시글 번호, 사용자 ID, 댓글 내용을 포함하는 DTO 객체를 생성합니다.
        //   DTO(Data Transfer Object): 데이터 이동을 위해 사용되는 객체로, 계층 간 데이터를 전달할 때 주로 사용됩니다.
        System.out.println("[INFO] 댓글 DTO 생성 중");
        BoardCommentDTO dto = new BoardCommentDTO(bno, id, content);
        System.out.println("[DEBUG] 생성된 DTO: " + dto);

        // 4. 댓글 추가 처리
        // - BoardService의 insertBoardComment 메서드를 호출하여 댓글을 추가합니다.
        //   Service 계층: 비즈니스 로직을 처리하는 계층으로, 컨트롤러와 데이터베이스 사이의 작업을 담당합니다.
        System.out.println("[INFO] 댓글 추가 처리 시작");
        BoardService.getInstance().insertBoardComment(dto); // 댓글 추가 로직 실행
        System.out.println("[INFO] 댓글 추가 처리 완료");

        // 5. 리다이렉트 경로 설정
        // - 댓글 추가 완료 후, 해당 게시글 상세 페이지로 이동하도록 설정합니다.
        //   리다이렉트(Redirect): 클라이언트에게 새로운 URL로 이동하라는 응답을 보냅니다.
        //   - 브라우저가 서버로부터 새로운 URL 정보를 받고 직접 이동을 수행합니다.
        //   - 현재 요청과 다음 요청이 별도의 요청으로 처리되며, 브라우저의 주소창이 변경됩니다.
        System.out.println("[INFO] 리다이렉트 경로 설정 중");
        ModelAndView view = new ModelAndView(); // ModelAndView 객체 생성
        view.setPath("./boardView.do?bno=" + bno); // 리다이렉트 경로 설정
        System.out.println("[DEBUG] 설정된 리다이렉트 경로: ./boardView.do?bno=" + bno);

        // 5.1 리다이렉트 플래그 설정
        // - `setRedirect(true)`: 리다이렉트를 수행하도록 설정합니다.
        //   - true: 클라이언트가 새로운 요청을 서버에 보냄
        //   - false: 서버에서 바로 뷰를 반환(포워딩)하여 클라이언트로 응답
        view.setRedirect(true); // 리다이렉트 플래그 설정
        System.out.println("[INFO] 리다이렉트 경로 설정 완료");

        // 6. 처리 결과 반환
        // - 최종적으로 ModelAndView 객체를 반환하여 요청 처리를 완료합니다.
        // - 클라이언트는 지정된 URL로 리다이렉트됩니다.
        System.out.println("[INFO] 댓글 추가 요청 처리 완료");
        return view;
    }
}
