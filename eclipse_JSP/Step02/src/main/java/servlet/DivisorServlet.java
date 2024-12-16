package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class DivisorServlet
 * 클라이언트로부터 받은 숫자의 약수를 계산하여 JSP 페이지에 전달하는 서블릿입니다.
 */
@WebServlet("/divisor.do")
public class DivisorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * 기본 생성자 - 서블릿이 생성될 때 호출됩니다.
     */
    public DivisorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * GET 요청을 처리하는 메서드입니다.
     * 클라이언트로부터 숫자를 입력받아 해당 숫자의 약수를 계산하고 JSP로 전달합니다.
     * @param request  클라이언트의 요청 정보를 담고 있는 HttpServletRequest 객체
     * @param response 서버의 응답 정보를 담고 있는 HttpServletResponse 객체
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // q1_divisor.jsp에서 받은 숫자를 파라미터로 받아옴
        // 클라이언트가 입력한 "number" 파라미터 값을 가져와서 정수로 변환합니다.
        int number = Integer.parseInt(request.getParameter("number"));

        // 약수 목록을 저장할 리스트 생성
        // 입력된 숫자의 약수를 저장할 ArrayList를 생성합니다.
        ArrayList<Integer> list = new ArrayList<>();

        // 약수 계산
        // 1부터 입력된 숫자까지 반복하면서 약수를 찾습니다.
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                // 현재 i 값이 입력된 숫자의 약수라면 리스트에 추가합니다.
                list.add(i);
            }
        }

        // request 영역에 숫자와 약수 리스트 저장
        // 입력된 숫자와 계산된 약수 목록을 request 객체에 속성으로 저장합니다.
        request.setAttribute("number", number);
        request.setAttribute("list", list);

        // q1_divisor_result.jsp로 포워딩
        // 요청과 응답을 q1_divisor_result.jsp로 전달하여 약수 목록을 출력합니다.
        request.getRequestDispatcher("q1_divisor_result.jsp").forward(request, response);
    }

    /**
     * POST 요청을 처리하는 메서드입니다.
     * POST 요청도 GET 요청과 동일하게 처리하도록 doGet 메서드를 호출합니다.
     * @param request  클라이언트의 요청 정보를 담고 있는 HttpServletRequest 객체
     * @param response 서버의 응답 정보를 담고 있는 HttpServletResponse 객체
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // POST 요청을 GET 요청으로 처리하여 동일한 로직을 수행합니다.
        doGet(request, response);
    }
}
