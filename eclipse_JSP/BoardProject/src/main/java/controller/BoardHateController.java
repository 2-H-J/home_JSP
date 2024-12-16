package controller;

import java.io.IOException;

import org.json.JSONObject;

import dto.BoardMemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardService;
import view.ModelAndView;

public class BoardHateController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        // 클라이언트로부터 전달받은 게시글 번호
        int bno = Integer.parseInt(request.getParameter("bno"));
        // 현재 세션에서 사용자 정보를 가져와 사용자 ID 추출
        String id = ((BoardMemberDTO) request.getSession().getAttribute("user")).getId();

        // JSON 객체를 생성하여 결과 데이터를 담을 예정
        JSONObject json = new JSONObject();

        try {
            // 좋아요 등록
            BoardService.getInstance().insertBoardHate(bno, id); // 서비스 계층을 통해 좋아요 추가
            json.put("msg", "해당 게시글에 싫어요를 하셨습니다."); // 성공 메시지 저장
        } catch (Exception e) {
            // 예외 발생 시(이미 좋아요 등록된 경우), 좋아요를 취소하는 로직 처리
            BoardService.getInstance().deleteBoardHate(bno, id); // 서비스 계층을 통해 좋아요 삭제
            json.put("msg", "해당 게시글에 싫어요를 취소 하셨습니다."); // 취소 메시지 저장
        }

        // 좋아요와 싫어요 개수를 서비스 계층에서 가져와 JSON 객체에 추가
        json.put("blike", BoardService.getInstance().getBoardLike(bno)); // 좋아요 개수
        json.put("bhate", BoardService.getInstance().getBoardHate(bno)); // 싫어요 개수

        // JSON 데이터를 클라이언트에게 응답으로 전송
        response.getWriter().println(json.toString());

        // 페이지 이동이 없으므로 null 반환
        return null;
	}

}
