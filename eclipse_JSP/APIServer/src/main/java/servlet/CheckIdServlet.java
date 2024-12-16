package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardMemberService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import dto.BoardMemberDTO;

/**
 * Servlet implementation class InsertMemberServlet
 */
@WebServlet("/checkMultipleId.do")
public class CheckIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = request.getReader();
		String str = "";
		StringBuilder builder = new StringBuilder();
		// 실제 클라이언트가 보낸 데이터를 문자열로 읽어오는 부분
		while ((str = br.readLine()) != null)
			builder.append(str);
		System.out.println(builder.toString());

		JSONObject json = new JSONObject(builder.toString());
		String id = json.getString("id");
		System.out.println(id);
		
		int result = BoardMemberService.getInstance().checkMemberId(id);
		
		String msg = (result == 0) ? "사용가능한 아이디입니다.":"사용 불가능한 아이디입니다.";
		json = new JSONObject();
		json.put("msg", msg);
		response.getWriter().println(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
