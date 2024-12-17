package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import service.UsersService;
import view.ModelAndView;

public class FindLoginIdController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String userName = request.getParameter("userName");
	    String userEmail = request.getParameter("userEmail");

	    System.out.println("[FindLoginIdController] 입력된 값 -> userName: " + userName + ", userEmail: " + userEmail);

	    // UsersService 호출
	    String loginId = UsersService.getInstance().findLoginId(userName, userEmail);
	    System.out.println("[FindLoginIdController] 반환된 loginId: " + loginId);

	    // JSON 응답 생성
	    JSONObject jsonResponse = new JSONObject();
	    if (loginId != null) {
	        jsonResponse.put("loginId", loginId);
	        System.out.println("[FindLoginIdController] JSON 반환 -> " + jsonResponse.toString());
	    } else {
	        jsonResponse.put("error", "일치하는 사용자가 없습니다.");
	        System.out.println("[FindLoginIdController] JSON 반환 -> " + jsonResponse.toString());
	    }

	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    
	    response.getWriter().write(jsonResponse.toString());

	    return null;
	}



}
