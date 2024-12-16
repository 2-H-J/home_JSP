package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import service.UsersService;
import view.ModelAndView;

public class CheckEmailController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        boolean exists = UsersService.getInstance().isEmailExists(email);

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("exists", exists);

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());

        return null; // JSON 응답이므로 뷰로 이동하지 않음
    }
}
