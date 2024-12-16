package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Servlet implementation class LottoServlet 사용자가 입력한 로또 셋트 개수에 따라 로또 번호를 생성하여
 * 결과를 JSP로 전달하는 서블릿입니다.
 */
@WebServlet("/lotto.do")
// @WebServlet 어노테이션은 해당 서블릿이 /lotto.do URL로 접근되도록 설정합니다.
public class LottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 기본 생성자 - 서블릿 객체가 생성될 때 호출됩니다.
	 */
	public LottoServlet() {
		super();
	}

	/**
	 * GET 요청을 처리하는 메서드입니다. 클라이언트로부터 요청받은 로또 셋트 개수에 따라 로또 번호를 생성하고 JSP로 전달합니다.
	 * 
	 * @param request  클라이언트의 요청 정보를 담고 있는 HttpServletRequest 객체
	 * @param response 서버의 응답 정보를 담고 있는 HttpServletResponse 객체
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// GET 방식의 요청도 POST 방식과 동일하게 처리합니다.
		doPost(request, response);
	}

	/**
	 * POST 요청을 처리하는 메서드입니다. 클라이언트로부터 요청받은 로또 셋트 개수에 따라 로또 번호를 생성하고 JSP로 전달합니다.
	 * 
	 * @param request  클라이언트의 요청 정보를 담고 있는 HttpServletRequest 객체
	 * @param response 서버의 응답 정보를 담고 있는 HttpServletResponse 객체
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트가 입력한 "lotto_count" 파라미터 값을 가져와서 정수로 변환합니다.
		int lotto_count = Integer.parseInt(request.getParameter("lotto_count"));
		// request.getParameter("lotto_count")는 클라이언트가 입력한 값을 가져옵니다.
		// Integer.parseInt()를 사용하여 문자열을 정수로 변환합니다.

		// 로또 번호 목록을 저장할 리스트 생성
		List<List<Integer>> lottoSets = new ArrayList<>();

		// 사용자가 요청한 개수만큼 로또 번호 생성
		for (int i = 0; i < lotto_count; i++) {
			// 로또 번호를 저장할 Set을 생성하여 중복을 방지합니다.
			Set<Integer> lottoNumbers = new HashSet<>();
			// 6개의 로또 번호가 생성될 때까지 반복합니다.
			while (lottoNumbers.size() < 6) {
				int number = (int) (Math.random() * 45) + 1; // 1부터 45까지의 난수를 생성
				lottoNumbers.add(number); // 생성된 번호를 Set에 추가하여 중복을 방지
			}
			// 생성된 번호를 정렬하여 리스트에 추가합니다.
			List<Integer> sortedLottoNumbers = new ArrayList<>(lottoNumbers);
			Collections.sort(sortedLottoNumbers); // 오름차순 정렬
			// 정렬된 로또 번호 리스트를 lottoSets에 추가합니다.
			lottoSets.add(sortedLottoNumbers);
		}

		// request 영역에 로또 번호 목록 저장
		request.setAttribute("lottoSets", lottoSets); // 로또 번호 목록을 "lottoSets"라는 이름으로 request 객체에 저장

		// q2_lotto_result.jsp로 포워딩하여 결과를 출력하도록 합니다.
		request.getRequestDispatcher("q2_lotto_result.jsp").forward(request, response);
	}
}