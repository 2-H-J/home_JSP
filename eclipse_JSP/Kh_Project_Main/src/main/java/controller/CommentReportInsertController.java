package controller;

import java.io.IOException;

import dto.CommentReportDTO;
import dto.UsersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ReportService;
import view.ModelAndView;

public class CommentReportInsertController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 세션에서 사용자 정보 가져오기
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			System.out.println("[AdminReportListController] 비로그인 상태 - 로그인 페이지로 리다이렉트");
			response.sendRedirect("login.do"); // .do로 변경하여 DispatcherServlet을 거치도록
			return null;
		}
		int postNumber = Integer.parseInt(request.getParameter("postNumber"));
		int commentNumber = Integer.parseInt(request.getParameter("commentNumber"));
		int userNumber = ((UsersDTO) request.getSession().getAttribute("user")).getUserNumber();
		String reason = request.getParameter("reason");

		CommentReportDTO dto = new CommentReportDTO();
		dto.setCommentNumber(commentNumber);
		dto.setUserNumber(userNumber);
		dto.setCommentReportReason(reason);

		ReportService.getinstance().insertCommentReport(dto);

		ModelAndView view = new ModelAndView();
		view.setPath("./boardDetail.do?postNumber=" + postNumber);
		view.setRedirect(true);

		return view;
	}

}
