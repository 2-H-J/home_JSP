package controller;

import java.io.IOException;

import dto.BoardDTO;
import dto.BoardMemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.BoardService;
import view.ModelAndView;

public class BoardWriteController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // title, content 파라미터 읽어오기
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        // 세션에서 user 가져오기
        HttpSession session = request.getSession();
        BoardMemberDTO user = (BoardMemberDTO) session.getAttribute("user");

        if (user == null) {
            // 세션에 user 정보가 없을 경우 처리
            response.sendRedirect("./login.jsp"); // 로그인 페이지로 리다이렉트
            return null;
        }

        // 작성자 ID 가져오기
        String id = user.getId();

        // 게시글 등록
        BoardDTO dto = new BoardDTO();
        dto.setId(id);
        dto.setTitle(title);
        dto.setContent(content);

        int count = BoardService.getInstance().insertBoard(dto);

        // 게시글 등록 후 메인 페이지로 리다이렉트
        ModelAndView view = new ModelAndView();
        view.setPath("./boardMain.do");
        view.setRedirect(true);
        return view;
    }
}