package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.BoardsDTO;
import service.BoardsService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/boardsList.do")
public class SelectBoard extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BoardsService boardsService = BoardsService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String sort = request.getParameter("sort");
        String order = request.getParameter("order");

        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);
        params.put("sort", sort);
        params.put("order", order);
 
        List<BoardsDTO> boardList = boardsService.searchBoardsByTitleSorted(params);
        
        
        request.setAttribute("list", boardList);
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("boards_list.jsp");
        dispatcher.forward(request, response);
    }
}