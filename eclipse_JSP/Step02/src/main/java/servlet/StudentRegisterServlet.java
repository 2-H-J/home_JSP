package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.StudentVO;

import java.io.IOException;

/**
 * Servlet implementation class StudentRegisterServlet
 */
@WebServlet("/studentRegister.do")
public class StudentRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentRegisterServlet() {
		super();
		// 기본 생성자 - 서블릿 객체가 생성될 때 호출됩니다.
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트로부터 전달된 파라미터를 request 객체에서 받아옵니다.
		String studentNo = request.getParameter("studentNo");
		String studentName = request.getParameter("studentName");
		String majorName = request.getParameter("majorName");
		String studentScore = request.getParameter("studentScore");

		// 학생 정보를 하나의 문자열로 합쳐서 콘솔에 출력합니다.
		String result = "학번 : " + studentNo + " / 이름 : " + studentName + " / 학과 : " + majorName + " / 학점 : "
				+ studentScore;
		System.out.println(result);
		
		// StudentVO 객체 생성 - 학생 정보를 저장합니다.
		StudentVO vo = new StudentVO(studentNo, studentName, majorName, Double.parseDouble(studentScore));
		
		// request 영역에 생성한 StudentVO 객체를 "vo"라는 이름으로 저장합니다.
		request.setAttribute("vo", vo);
		
		// student_result.jsp로 이동해서 vo에 있는 내용을 전부 출력합니다.
		// 기존 방식: RequestDispatcher 객체를 사용해 forward 메서드를 호출하여 페이지 이동
		// 다른 방식: request.getRequestDispatcher()를 직접 사용해 페이지 이동
		request.getRequestDispatcher("student_result.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST 요청에서 한글 인코딩 처리를 위해 설정 (한글 깨짐 방지)
		request.setCharacterEncoding("utf-8");
		// POST 요청에서도 doGet 메서드를 호출하여 동일한 로직을 수행합니다.
		doGet(request, response);
	}

}
