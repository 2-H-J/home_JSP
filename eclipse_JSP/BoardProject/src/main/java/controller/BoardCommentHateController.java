package controller;

import java.io.IOException;

import org.json.JSONObject;

import dto.BoardMemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardService;
import view.ModelAndView;

public class BoardCommentHateController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 메서드 설명:
        // - 요청으로부터 댓글 번호와 사용자 정보를 추출합니다.
        // - 댓글에 "싫어요"를 추가하거나 취소하는 작업을 수행합니다.
        // - 작업 결과 메시지를 JSON 형식으로 반환합니다.

        // [INFO] 댓글 싫어요 요청 처리 시작
        System.out.println("[INFO] 댓글 싫어요 요청 처리 시작");

        // 1. 요청 파라미터에서 댓글 번호(cno) 추출
        // - 클라이언트 요청에서 특정 댓글의 고유 식별 번호를 가져옵니다.
        System.out.println("[INFO] 요청 파라미터 추출 중");
        int cno = Integer.parseInt(request.getParameter("cno")); // 댓글 번호 추출
        System.out.println("[DEBUG] 추출된 댓글 번호(cno): " + cno);

        // 2. 세션에서 사용자 ID 추출
        // - 현재 로그인된 사용자의 정보를 세션에서 가져옵니다.
        // - BoardMemberDTO 객체로 캐스팅하여 사용자 ID를 추출합니다.
        System.out.println("[INFO] 사용자 ID 추출 중");
        String id = ((BoardMemberDTO) request.getSession().getAttribute("user")).getId();
        System.out.println("[DEBUG] 추출된 사용자 ID: " + id);

        // 3. 결과 저장용 JSON 객체 생성
        // - 작업 결과를 클라이언트에 전달하기 위해 JSON 객체를 생성합니다.
        JSONObject json = new JSONObject();

        try {
            // 4. 댓글에 싫어요 추가 처리
            // - BoardService의 insertBoardCommentHate 메서드를 호출하여 "싫어요" 추가를 수행합니다.
            System.out.println("[INFO] 댓글 싫어요 추가 처리 시작");
            BoardService.getInstance().insertBoardCommentHate(cno, id);
            json.put("msg", "해당 댓글에 싫어요를 하셨습니다."); // 성공 메시지 저장
            System.out.println("[INFO] 댓글 싫어요 추가 처리 완료");
        } catch (Exception e) {
            // 5. 예외 발생 시 싫어요 취소 처리
            // - 이미 "싫어요"가 추가된 상태에서 예외가 발생하면 "싫어요"를 취소합니다.
            System.out.println("[ERROR] 싫어요 추가 중 예외 발생: " + e.getMessage());
            System.out.println("[INFO] 댓글 싫어요 취소 처리 시작");
            BoardService.getInstance().deleteBoardCommentHate(cno, id);
            json.put("msg", "해당 댓글에 싫어요를 취소하셨습니다."); // 취소 메시지 저장
            System.out.println("[INFO] 댓글 싫어요 취소 처리 완료");
        }

        // 6. JSON 결과 반환
        // - 작업 결과를 JSON 형식으로 클라이언트에 반환합니다.
        System.out.println("[INFO] JSON 결과 반환 중");
        response.getWriter().println(json.toString());
        System.out.println("[DEBUG] 반환된 JSON: " + json.toString());

        // 7. 처리 결과 반환
        // - 이 컨트롤러는 비동기 요청을 처리하므로 null을 반환합니다.
        // - 클라이언트는 JSON 데이터를 받아서 처리합니다.
        System.out.println("[INFO] 댓글 싫어요 요청 처리 완료");
        return null;
    }

}
