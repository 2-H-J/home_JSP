package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardMemberService;

import java.io.IOException;
import java.util.List;

import dto.BoardMemberDTO;


@WebServlet({ "/SearchNameServlet", "/searchName.do" })
public class SearchNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SearchNameServlet() {
    	
    }


//		Servlet는 컨트롤러 클래스랑 같다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1. 클라이언트가 보낸 데이터 받음
		String name = request.getParameter("search");
		
//		2. 서버 클래스로 데이터 보내고 결과를 받음
		List<BoardMemberDTO> list = BoardMemberService.getInstance().selectNameMember(name);
		
//		3. 데이터 셋팅
		request.setAttribute("list", list);
		
//		4. 페이지 이동
		request.getRequestDispatcher("member_list.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
