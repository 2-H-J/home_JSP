package controller;

import java.io.IOException;

import dto.UsersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import service.UsersService;
import view.ModelAndView;

public class PasswordRecoveryController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String loginId = request.getParameter("loginId");
		String userEmail = request.getParameter("userEmail");

		System.out.println("[Controller] 입력값 확인: 이름=" + userName + ", 아이디=" + loginId + ", 이메일=" + userEmail);

		JSONObject jsonResponse = new JSONObject();

		// 사용자 인증
		UsersDTO user = UsersService.getInstance().verifyUserForPasswordReset(userName, loginId, userEmail);

		if (user != null) {
			jsonResponse.put("status", "success");
			jsonResponse.put("message", "사용자 인증 성공");
			// 세션에 사용자 정보를 저장하여 비밀번호 변경 시 사용할 수 있게 함
			request.getSession().setAttribute("verifiedUserNumber", user.getUserNumber());
		} else {
			jsonResponse.put("status", "error");
			jsonResponse.put("message", "입력한 정보와 일치하는 사용자를 찾을 수 없습니다.");
		}

		response.setContentType("application/json");
		response.getWriter().write(jsonResponse.toString());

		return null;
	}
}