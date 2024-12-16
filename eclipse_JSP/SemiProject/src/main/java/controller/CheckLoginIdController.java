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

		// 요청 시작 로그
		System.out.println("[CheckLoginIdController] execute() 시작");

		// 클라이언트로부터 전달받은 아이디
		String loginId = request.getParameter("loginId");
		System.out.println("[CheckLoginIdController] 중복 체크 요청 아이디: " + loginId); // 요청된 ID 출력

		// UsersService를 사용하여 아이디 중복 확인
		boolean exists = UsersService.getInstance().isLoginIdExists(loginId);
		System.out.println("[CheckLoginIdController] 중복 체크 결과 (DB에서 반환): " + exists); // DB 결과 출력

		// JSON 응답 생성
		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("exists", exists);
		System.out.println("[CheckLoginIdController] JSON 응답 생성: " + jsonResponse);

		// 응답 설정 및 출력
		response.setContentType("application/json");
		response.getWriter().write(jsonResponse.toString());
		System.out.println("[CheckLoginIdController] 응답 전송 완료");

		// 뷰로 이동하지 않음
		System.out.println("[CheckLoginIdController] 뷰 이동 없음 (JSON 응답 처리 완료)");
		return null;
	}
}
