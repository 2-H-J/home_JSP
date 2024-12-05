package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardMemberService;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import dto.BoardMemberDTO;

/**
 * Servlet implementation class SelectMemberNameServlet
 */
@WebServlet("/searchNameMember.do")
public class SelectMemberNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectMemberNameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//null 나오는 이유, 데이터를 쿼리 스트링으로 보내지 않아서(데이터가 url에 없음)
		System.out.println(request.getParameter("search"));
		//Post로 body영역에 데이터를 보내면 inputStream으로 문자열을 읽어서 사용
		BufferedReader br = request.getReader();
		String str = "";
		StringBuilder builder = new StringBuilder();
		//실제 클라이언트가 보낸 데이터를 문자열로 읽어오는 부분
		while((str = br.readLine()) != null)
			builder.append(str);
		System.out.println(builder.toString());
		
		//문자열을 json으로 변환 후 search 데이터 추출
		JSONObject json = new JSONObject(builder.toString());
		String search = json.getString("search");
		System.out.println(search);
		
		//회원 이름 검색 수행해서 데이터 조회
		List<BoardMemberDTO> list = 
				BoardMemberService.getInstance().selectNameMember(search);
		//받은 회원정보 개수
		int count = list.size();
		//조회한 현재 날짜 시간도 문자열 저장 YYYY-MM-DD HH:mm:ss
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		Date date = Calendar.getInstance().getTime();
		String dateString = sdf.format(date);
		
		//JSON으로 변환
		json = new JSONObject();
		//JSON에 데이터 추가
		json.put("list", list);
		json.put("count", count);
		json.put("date",dateString);
		
		System.out.println(json.toString());
		
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
