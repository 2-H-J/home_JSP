package controller;

import java.io.IOException;

import org.json.JSONObject; // JSON 데이터 구조를 처리하기 위한 클래스 (외부 라이브러리)
import dto.BoardMemberDTO; // 사용자 정보를 저장하기 위한 데이터 전송 객체 (DTO)
import jakarta.servlet.ServletException; // 서블릿에서 발생하는 예외를 처리하기 위한 클래스
import jakarta.servlet.http.HttpServletRequest; // 클라이언트 요청 정보를 담고 있는 클래스
import jakarta.servlet.http.HttpServletResponse; // 클라이언트로 응답 데이터를 작성하기 위한 클래스
import service.BoardService; // 비즈니스 로직을 처리하는 서비스 계층
import view.ModelAndView; // 뷰(View)와 모델(Model)을 포함하는 객체로, 페이지 이동 정보를 제공

// BoardLikeController 클래스는 게시글에 "좋아요" 기능을 처리하는 컨트롤러입니다.
// 클라이언트 요청으로부터 게시글 번호와 사용자 정보를 받아 "좋아요"를 추가하거나 취소한 후,
// 작업 결과를 JSON 형태로 응답합니다.
public class BoardLikeController implements Controller {

    // 이 메서드는 클라이언트 요청을 처리하고 결과를 반환합니다.
    // - HttpServletRequest: 클라이언트 요청 정보를 포함하는 객체입니다.
    // - HttpServletResponse: 서버가 클라이언트로 보낼 응답 정보를 포함하는 객체입니다.
    // - ModelAndView: 요청 처리 결과와 뷰(View)로 이동하기 위한 경로를 포함하는 객체입니다.
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // [INFO] 게시글 좋아요 요청 처리 시작
        System.out.println("[INFO] 게시글 좋아요 요청 처리 시작");

        // 1. 게시글 번호(bno) 추출
        // - 요청 파라미터에서 게시글의 고유 번호를 가져옵니다.
        // - 게시글 번호는 "좋아요" 작업을 수행할 게시글을 식별하는 데 사용됩니다.
        int bno = Integer.parseInt(request.getParameter("bno")); // 파라미터로 전달된 게시글 번호
        System.out.println("[DEBUG] 게시글 번호(bno): " + bno);

        // 2. 사용자 ID 추출
        // - 세션에 저장된 사용자 정보를 가져옵니다.
        // - 사용자 정보는 BoardMemberDTO 객체로 저장되어 있습니다.
        // - 사용자 ID는 "좋아요" 작업이 수행된 사용자를 식별하는 데 사용됩니다.
        String id = ((BoardMemberDTO) request.getSession().getAttribute("user")).getId();
        System.out.println("[DEBUG] 사용자 ID: " + id);

        // 3. 작업 결과를 저장할 JSON 객체 생성
        // - JSON(JavaScript Object Notation): 데이터를 구조적으로 표현하기 위한 경량 데이터 교환 형식입니다.
        // - 작업 결과 메시지와 게시글의 좋아요/싫어요 수를 클라이언트로 반환하기 위해 사용됩니다.
        JSONObject json = new JSONObject(); // JSON 객체 생성

        try {
            // 4. 좋아요 추가 처리
            // - BoardService의 insertBoardLike 메서드를 호출하여 좋아요를 추가합니다.
            // - 좋아요가 정상적으로 추가되면 성공 메시지를 JSON에 추가합니다.
            System.out.println("[INFO] 좋아요 추가 처리 시작");
            BoardService.getInstance().insertBoardLike(bno, id); // 좋아요 추가 로직 실행
            json.put("msg", "해당 게시글에 좋아요를 하셨습니다."); // 성공 메시지 저장
            System.out.println("[INFO] 좋아요 추가 처리 완료");
        } catch (Exception e) {
            // 5. 좋아요 취소 처리
            // - 예외 발생 시 이미 추가된 좋아요를 취소합니다.
            // - BoardService의 deleteBoardLike 메서드를 호출하여 기존 좋아요를 삭제합니다.
            System.out.println("[ERROR] 좋아요 처리 중 예외 발생: " + e.getMessage());
            System.out.println("[INFO] 좋아요 취소 처리 시작");
            BoardService.getInstance().deleteBoardLike(bno, id); // 좋아요 취소 로직 실행
            json.put("msg", "해당 게시글에 좋아요를 취소하셨습니다."); // 취소 메시지 저장
            System.out.println("[INFO] 좋아요 취소 처리 완료");
        }

        // 6. 좋아요 및 싫어요 수 갱신
        // - BoardService의 메서드를 호출하여 게시글의 최신 좋아요(blike) 및 싫어요(bhate) 수를 가져옵니다.
        int blike = BoardService.getInstance().getBoardLike(bno); // 게시글 좋아요 수
        int bhate = BoardService.getInstance().getBoardHate(bno); // 게시글 싫어요 수
        json.put("blike", blike); // JSON에 좋아요 수 추가
        json.put("bhate", bhate); // JSON에 싫어요 수 추가
        System.out.println("[DEBUG] 좋아요 수(blike): " + blike + ", 싫어요 수(bhate): " + bhate);

        // 7. JSON 결과 반환
        // - JSON 데이터를 클라이언트로 반환합니다.
        // - `response.getWriter().println(json.toString())`: JSON 데이터를 문자열로 변환하여 응답 스트림에 씁니다.
        response.getWriter().println(json.toString());
        System.out.println("[DEBUG] 반환된 JSON 데이터: " + json.toString());

        // 8. 처리 완료
        // - AJAX 요청에 대한 응답이므로 null을 반환하여 뷰 이동 없이 작업을 종료합니다.
        System.out.println("[INFO] 게시글 좋아요 요청 처리 완료");
        return null;
    }
}
