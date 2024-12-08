package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import service.UsersService;
import view.ModelAndView;

/**
 * CheckLoginIdController는 입력받은 아이디의 중복 여부를 확인하는 컨트롤러입니다.
 */
public class CheckLoginIdController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 클라이언트로부터 전달받은 아이디
		String loginId = request.getParameter("loginId");
		System.out.println("중복 체크 요청 아이디: " + loginId); // 요청된 ID 출력

		// UsersService를 사용하여 아이디 중복 확인
		boolean exists = UsersService.getInstance().isLoginIdExists(loginId);
		System.out.println("중복 체크 결과 (DB에서 반환): " + exists); // DB 결과 출력

		// JSON 응답 생성
		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("exists", exists);

		response.setContentType("application/json");
		response.getWriter().write(jsonResponse.toString());

		return null; // AJAX 요청이므로 페이지 이동 없음
	}
}