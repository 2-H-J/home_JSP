package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.StudentVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class JSTLServlet
 */
@WebServlet({ "/JSTLServlet", "/jstl.do" })
public class JSTLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JSTLServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. StudentVO 하나 생성
		StudentVO vo = new StudentVO("20001111","홍길동","컴퓨터 공학과",3.5);
		
		// 2. ArrayList 하나 생성, StudentVO 객체들 5개 저장
		ArrayList<StudentVO> list = new ArrayList<StudentVO>();
		list.add(new StudentVO("20011102", "김철수", "컴퓨터공학", 4.0));
		list.add(new StudentVO("20011132", "이영희", "전자공학", 3.8));
		list.add(new StudentVO("20011522", "박민수", "기계공학", 3.9));
		list.add(new StudentVO("20011232", "정수진", "경영학", 4.2));
		list.add(new StudentVO("20214423", "최지혜", "경제학", 3.7));
		// 3. 정수하나 선언해서 임의 값으로 초기화
		int age = 44;
		
		// 4. request영역에 데이터를 전부 저장
		request.setAttribute("vo", vo);
		request.setAttribute("list", list);
		request.setAttribute("age", age);
		
		// 5. 페이지 이동은 jstl_el.jsp로 이동 - forward
		request.getRequestDispatcher("jstl_el.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
