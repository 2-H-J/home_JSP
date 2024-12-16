package controller;

import java.io.IOException;

import dto.UsersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UsersService;
import view.ModelAndView;

public class ResetPasswordController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String loginId = request.getParameter("loginId");
        String userEmail = request.getParameter("userEmail");

        if (loginId == null || loginId.isEmpty() || userEmail == null || userEmail.isEmpty()) {
            response.getWriter().println("ID와 이메일을 모두 입력해주세요.");
            return new ModelAndView();
        }

        UsersDTO user = UsersService.getInstance().getUserByLoginIdAndEmail(loginId, userEmail);

        ModelAndView view = new ModelAndView();
        if (user != null) {
            // 새로운 임시 비밀번호 생성
            String tempPassword = "NewPass123!"; // 임의로 생성
            String hashedPassword = BCrypt.hashpw(tempPassword, BCrypt.gensalt());
            user.setPassword(hashedPassword);

            // 비밀번호 업데이트
            int result = UsersService.getInstance().updatePassword(user);

            if (result > 0) {
                request.setAttribute("tempPassword", tempPassword);
                view.setPath("./PwFindSuccessPage.jsp");
            } else {
                response.getWriter().println("비밀번호 초기화에 실패했습니다. 다시 시도해주세요.");
                view.setPath("./PwFindErrorPage.jsp");
            }
        } else {
            response.getWriter().println("입력하신 정보와 일치하는 계정을 찾을 수 없습니다.");
            view.setPath("./PwFindErrorPage.jsp");
        }

        return view;
    }
}
