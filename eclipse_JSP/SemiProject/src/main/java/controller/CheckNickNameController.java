package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import service.UsersService;
import view.ModelAndView;

/**
 * CheckNickNameController는 닉네임 중복 여부를 확인하는 컨트롤러입니다.
 */
public class CheckNickNameController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1. 요청에서 닉네임 파라미터를 가져옴
        String nickName = request.getParameter("nickName");
        System.out.println("[CheckNickNameController] 닉네임 중복 체크 요청: " + nickName);

        // 2. 닉네임 중복 여부 확인 (DB 연동)
        boolean exists = UsersService.getInstance().isNickNameExists(nickName);

        // 3. JSON 응답 생성
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("exists", exists); // 중복 여부 저장

        // 4. 응답 설정
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());

        // JSON 응답이므로 뷰 이동 없음
        return null;
    }
}
