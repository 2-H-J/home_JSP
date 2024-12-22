package controller;

import java.io.IOException;

import dto.UsersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import view.ModelAndView; // 패키지명과 클래스명을 실제 환경에 맞게 수정하세요.

public class ReportUserController implements Controller {

	private service.ReportService reportService = new service.ReportService();

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // 세션에서 사용자 정보 가져오기
	    HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("user") == null) {
	        // 로그인하지 않은 사용자는 신고할 수 없도록 처리
	        System.out.println("비로그인 사용자 신고 시도");
	        response.sendRedirect("login.jsp"); // 로그인 페이지로 리다이렉트
	        return null;
	    }

	    UsersDTO user = (UsersDTO) session.getAttribute("user");
	    int userNumber = user.getUserNumber(); // userNumber가 int 타입인 경우
	    System.out.println("신고자 번호: " + userNumber);

	    // 신고 폼에서 받은 postNumber와 reason만 가져오기
	    String postNumberStr = request.getParameter("postNumber");
	    String reason = request.getParameter("reason");
	    System.out.println("신고 게시글 번호: " + postNumberStr);
	    System.out.println("신고 사유: " + reason);

	    int postNumber;
	    try {
	        postNumber = Integer.parseInt(postNumberStr);
	        System.out.println("파싱된 게시글 번호: " + postNumber);
	    } catch (NumberFormatException e) {
	        System.out.println("잘못된 게시글 번호 형식: " + postNumberStr);
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid post number.");
	        return null;
	    }

	    if (reason == null || reason.trim().isEmpty()) {
	        System.out.println("신고 사유가 비어 있습니다.");
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Reason for report is required.");
	        return null;
	    }

	    // 신고 생성
	    reportService.createReport(userNumber, postNumber, reason);
	    System.out.println("신고가 성공적으로 처리되었습니다.");

	    // 신고 성공 페이지로 포워드
	    ModelAndView view = new ModelAndView();
	    view.setPath("reportSuccess.jsp");
	    view.setRedirect(false);
	    return view;
	}
}