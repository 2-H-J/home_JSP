package controller;

import java.io.IOException;
import java.util.List;

import dto.BoardsDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardsService;
import view.ModelAndView;

public class BoardsCategoryController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 태그 및 페이징 파라미터 받기
        String tag = request.getParameter("tag"); // 필터링할 카테고리 태그
        String pageParam = request.getParameter("page"); // 현재 페이지
        int currentPage = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1; // 페이지 값 기본값 1
        int pageSize = 40; // 페이지당 게시글 수
        
        BoardsService service = BoardsService.getInstance();

        // 전체 게시글 수 가져오기
        int totalRecords = (tag != null && !tag.isEmpty())
                ? service.getBoardCountByTag(tag) // 특정 태그에 속한 게시글 수
                : service.getTotalRecords(); // 전체 게시글 수
        
        // 페이징 처리에 필요한 계산
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize); // 전체 페이지 수
        int offset = (currentPage - 1) * pageSize; // 시작 위치 계산

        // 게시글 가져오기
        List<BoardsDTO> boards = (tag != null && !tag.isEmpty())
                ? service.getBoardsByTagWithPaging(tag, offset, pageSize) // 특정 태그 페이징 처리
                : service.selectBoards(offset, pageSize); // 전체 게시글 페이징 처리

        // 결과를 확인하기 위한 디버깅 로그
        System.out.println("Tag: " + tag);
        System.out.println("Current Page: " + currentPage);
        System.out.println("Total Pages: " + totalPages);
        System.out.println("Total Records: " + totalRecords);

        for (BoardsDTO board : boards) {
            System.out.println("Post Number: " + board.getPostNumber());
            System.out.println("Title: " + board.getTitle());
            System.out.println("Tag: " + board.getTag());
        }

        // 데이터와 경로를 ModelAndView에 설정
        ModelAndView view = new ModelAndView();
        view.addObject("list", boards); // 게시글 리스트 전달
        view.addObject("currentPage", currentPage); // 현재 페이지 전달
        view.addObject("totalPages", totalPages); // 전체 페이지 수 전달
        view.addObject("tag", tag); // 현재 필터링된 태그 전달
        view.setPath("/board_list.jsp"); // 이동할 JSP 경로
        view.setRedirect(false);
        return view;
    }
}