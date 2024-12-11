package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import view.ModelAndView;

/**
 * CheckNickNameController는 닉네임 중복 여부를 확인하는 컨트롤러입니다.
 */
public class CheckNickNameController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String nickName = request.getParameter("nickName");
	    System.out.println("[CheckNickNameController] 닉네임 중복 체크 요청: " + nickName);

	    // 테스트용 임시 응답
	    JSONObject jsonResponse = new JSONObject();
	    if (nickName.equals("test")) {
	        jsonResponse.put("exists", true);
	    } else {
	        jsonResponse.put("exists", false);
	    }

	    response.setContentType("application/json");
	    response.getWriter().write(jsonResponse.toString());

	    return null;
	}

}
