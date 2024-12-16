package controller;

import java.io.IOException;

import dto.UsersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UsersService;
import view.ModelAndView;

public class FindLoginIdController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userEmail = request.getParameter("userEmail");

        if (userEmail == null || userEmail.isEmpty()) {
            response.getWriter().println("이메일을 입력해주세요.");
            return new ModelAndView();
        }

        UsersDTO user = UsersService.getInstance().getUserByEmail(userEmail);

        ModelAndView view = new ModelAndView();
        if (user != null) {
            request.setAttribute("foundLoginId", user.getLoginId());
            view.setPath("./IdFindSuccessPage.jsp");
        } else {
            response.getWriter().println("입력하신 이메일로 등록된 ID를 찾을 수 없습니다.");
            view.setPath("./IdFindErrorPage.jsp");
        }

        return view;
    }
}
