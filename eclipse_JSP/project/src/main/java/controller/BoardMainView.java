package controller;

import java.io.IOException;
import java.util.List;

import dto.BoardsDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardsService;
import view.ModelAndView;

public class BoardMainView implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        // 1. 현재 페이지 번호 가져오기
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;

        // 2. 페이지당 게시글 수 설정
        int pageSize = 40;

        // 3. 전체 게시글 수 가져오기
        int totalRecords = BoardsService.getInstance().getTotalRecords();

        // 4. 전체 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

        // 5. 현재 페이지의 데이터 범위 계산
        int offset = (page - 1) * pageSize;

        // 6. 데이터베이스에서 페이징 처리된 게시글 가져오기
        List<BoardsDTO> list = BoardsService.getInstance().selectBoards(offset, pageSize);

        // 7. JSP에 전달할 데이터 설정
        request.setAttribute("list", list);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        // 8. ModelAndView 설정
        ModelAndView view = new ModelAndView();
        view.addObject("list", list);
        view.setPath("board_list.jsp");
        view.setRedirect(false);

        return view;
	}

}
