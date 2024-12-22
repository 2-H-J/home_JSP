package controller;

import java.io.IOException;

import org.json.JSONObject;

import dto.BoardMemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardService;
import view.ModelAndView;

public class BoardCommentLikeController implements Controller {

    // 이 메서드는 클라이언트로부터 댓글 좋아요 요청을 받아 처리합니다.
    // - HttpServletRequest: 요청 정보를 담고 있는 객체로, 클라이언트가 전송한 데이터를 처리합니다.
    // - HttpServletResponse: 서버가 클라이언트로 전송할 응답 데이터를 처리합니다.
    // - ModelAndView: 뷰(View)와 모델(Model) 데이터를 포함하는 객체로, 여기서는 사용하지 않고 JSON 형식으로 직접 응답을 반환합니다.
    // - ServletException, IOException: 서블릿 및 입출력 처리 중 발생할 수 있는 예외를 선언하여 호출자에게 알립니다.

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // [INFO] 댓글 좋아요 요청 처리 시작
        System.out.println("[INFO] 댓글 좋아요 요청 처리 시작");

        // 1. 요청 파라미터에서 댓글 번호(cno) 추출
        // - 클라이언트 요청에서 특정 댓글의 고유 번호(cno)를 가져옵니다.
        // - Integer.parseInt: 문자열로 전달된 값을 정수로 변환합니다.
        System.out.println("[INFO] 요청 파라미터 추출 중");
        int cno = Integer.parseInt(request.getParameter("cno")); // 댓글 번호 추출
        System.out.println("[DEBUG] 추출된 댓글 번호(cno): " + cno);

        // 2. 세션에서 사용자 ID 추출
        // - 현재 로그인된 사용자의 정보를 세션에서 가져옵니다.
        // - `getSession().getAttribute("user")`: 세션에 저장된 사용자 객체를 가져옵니다.
        // - BoardMemberDTO: 사용자 정보를 담고 있는 DTO(Data Transfer Object)입니다.
        System.out.println("[INFO] 사용자 ID 추출 중");
        String id = ((BoardMemberDTO) request.getSession().getAttribute("user")).getId();
        System.out.println("[DEBUG] 추출된 사용자 ID: " + id);

        // 3. 결과를 저장할 JSON 객체 생성
        // - 결과 메시지를 JSON 형식으로 생성하여 클라이언트에 반환합니다.
        // - org.json.JSONObject: JSON 데이터 구조를 생성 및 조작하기 위한 클래스입니다.
        JSONObject json = new JSONObject();

        try {
            // 4. 댓글에 좋아요 추가 처리
            // - BoardService의 insertBoardCommentLike 메서드를 호출하여 댓글에 좋아요를 추가합니다.
            // - BoardService는 비즈니스 로직을 처리하는 서비스 계층입니다.
            System.out.println("[INFO] 댓글 좋아요 추가 처리 시작");
            BoardService.getInstance().insertBoardCommentLike(cno, id); // 좋아요 추가
            json.put("msg", "해당 댓글에 좋아요를 하셨습니다."); // 성공 메시지 추가
            System.out.println("[INFO] 댓글 좋아요 추가 처리 완료");
        } catch (Exception e) {
            // 5. 예외 발생 시 좋아요 취소 처리
            // - 이미 좋아요가 추가된 상태에서 예외가 발생하면, 기존 좋아요를 취소합니다.
            // - BoardService의 deleteBoardCommentLike 메서드를 호출합니다.
            System.out.println("[ERROR] 좋아요 추가 중 예외 발생: " + e.getMessage());
            System.out.println("[INFO] 댓글 좋아요 취소 처리 시작");
            BoardService.getInstance().deleteBoardCommentLike(cno, id); // 좋아요 취소
            json.put("msg", "해당 댓글에 좋아요를 취소하셨습니다."); // 취소 메시지 추가
            System.out.println("[INFO] 댓글 좋아요 취소 처리 완료");
        }

        // 6. JSON 결과 반환
        // - 클라이언트에게 작업 결과를 JSON 형식으로 응답합니다.
        // - `response.getWriter().println(json.toString())`: JSON 데이터를 문자열로 변환하여 응답 스트림에 씁니다.
        System.out.println("[INFO] JSON 결과 반환 중");
        response.getWriter().println(json.toString());
        System.out.println("[DEBUG] 반환된 JSON: " + json.toString());

        // 7. 처리 결과 반환
        // - 이 컨트롤러는 비동기 요청(AJAX 요청)을 처리하므로 ModelAndView 객체를 반환하지 않습니다.
        // - null을 반환하여 컨트롤러의 요청 처리를 종료합니다.
        System.out.println("[INFO] 댓글 좋아요 요청 처리 완료");
        return null;
    }

}
