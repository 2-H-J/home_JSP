package controller;

import java.io.IOException;
import java.util.List;

import dto.UserReportDTO;
import dto.UsersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ReportService;
import view.ModelAndView;


public class AdminReportListController implements Controller {

	 @Override
	    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        System.out.println("[AdminReportListController] execute() 호출됨");

	        // 세션에서 사용자 정보 가져오기
	        HttpSession session = request.getSession(false);
	        if (session == null || session.getAttribute("user") == null) {
	            System.out.println("[AdminReportListController] 비로그인 상태 - 로그인 페이지로 리다이렉트");
	            response.sendRedirect("login.do");
	            return null;
	        }

	        UsersDTO user = (UsersDTO) session.getAttribute("user");
	        if (!user.isAdmin()) {
	            // 관리자 여부 확인
	            System.out.println("[AdminReportListController] 비관리자 접근 - 접근 거부");
	            response.sendRedirect("accessDenied.jsp");
	            return null;
	        }

	        try {
	            // 신고 상태 업데이트 처리
	            handleReportAction(request, user);

	            // 검색 및 페이징 처리
	            processReportList(request);

	        } catch (NumberFormatException e) {
	            System.out.println("[AdminReportListController] 잘못된 요청 파라미터: " + e.getMessage());
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 요청 파라미터입니다.");
	            return null;
	        } catch (Exception e) {
	            System.out.println("[AdminReportListController] 데이터 처리 중 예외 발생: " + e.getMessage());
	            e.printStackTrace();
	            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.");
	            return null;
	        }

	        // 신고 목록 JSP로 포워드
	        ModelAndView view = new ModelAndView();
	        view.setPath("adminReportList.jsp");
	        view.setRedirect(false);
	        System.out.println("[AdminReportListController] adminReportList.jsp로 포워드");
	        return view;
	    }

	    /**
	     * 신고 상태 업데이트 처리
	     */
	    private void handleReportAction(HttpServletRequest request, UsersDTO user) {
	        String action = request.getParameter("action"); // "approve" 또는 "reject"
	        String reportNumberStr = request.getParameter("reportNumber");

	        if (action != null && reportNumberStr != null) {
	            int reportNumber = Integer.parseInt(reportNumberStr);
	            String status = action.equalsIgnoreCase("approve") ? "APPROVED" : "REJECTED";
	            int adminId = user.getUserNumber();

	            // 신고 상태 업데이트
	            boolean isUpdated = ReportService.getinstance().updateReportStatus(reportNumber, status, adminId);
	            System.out.println("[AdminReportListController] 신고 처리 " + (isUpdated ? "성공" : "실패") +
	                    " - ReportNumber: " + reportNumber + ", Status: " + status);
	        }
	    }

	    /**
	     * 검색 및 페이징 처리
	     */
	    private void processReportList(HttpServletRequest request) {
	        String memberIdParam = request.getParameter("memberId"); // 회원번호 검색
	        String pageParam = request.getParameter("page"); // 현재 페이지
	        int currentPage = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;
	        int pageSize = 10; // 한 페이지에 표시할 신고 수
	        int start = (currentPage - 1) * pageSize + 1;
	        int end = currentPage * pageSize;

	        ReportService service = ReportService.getinstance();
	        List<UserReportDTO> reports;
	        int totalRecords;

	        if (memberIdParam != null && !memberIdParam.isEmpty()) {
	            // 회원번호 검색 시
	            int memberId = Integer.parseInt(memberIdParam);
	            totalRecords = service.getTotalReportCountByMemberId(memberId);
	            reports = service.getReportsByMemberIdWithPagination(memberId, start, end);
	            System.out.println("[AdminReportListController] 회원번호 검색 - memberId: " + memberId + ", totalRecords: " + totalRecords);
	        } else {
	            // 전체 신고 목록 조회
	            totalRecords = service.getTotalReportCount();
	            reports = service.getReportsWithPagination(start, end);
	            System.out.println("[AdminReportListController] 전체 신고 조회 - totalRecords: " + totalRecords);
	        }

	        // 페이징 계산
	        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

	        // 데이터 JSP로 전달
	        request.setAttribute("reports", reports);
	        request.setAttribute("currentPage", currentPage);
	        request.setAttribute("totalPages", totalPages);
	        request.setAttribute("totalRecords", totalRecords);
	        request.setAttribute("memberId", memberIdParam);
	    }
	}