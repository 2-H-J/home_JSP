package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import service.UsersService;
import view.ModelAndView;

public class PasswordUpdateController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newPassword = request.getParameter("newPassword");
        int userNumber = (int) request.getSession().getAttribute("verifiedUserNumber");

        JSONObject jsonResponse = new JSONObject();

        boolean resetSuccess = UsersService.getInstance().resetPassword(userNumber, newPassword);

        if (resetSuccess) {
            jsonResponse.put("status", "success");
            jsonResponse.put("message", "비밀번호가 성공적으로 변경되었습니다.");
        } else {
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "비밀번호 변경에 실패했습니다. 다시 시도해주세요.");
        }

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());

        return null;
    }
}
