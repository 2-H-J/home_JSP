package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import service.UsersService;

public class EmailCheckController implements Controller {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");

        // 디버깅: 이메일 값 확인
        System.out.println("중복 확인 요청 이메일: " + email);

        boolean exists = UsersService.getInstance().isEmailExists(email);

        // JSON 응답 생성
        Map<String, Boolean> result = new HashMap<>();
        result.put("exists", exists);

        response.setContentType("application/json");
        response.getWriter().write(new JSONObject(result).toString());
    }
}
