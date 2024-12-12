package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UsersService;
import view.ModelAndView;

/**
 * 닉네임 수정 요청을 처리하는 컨트롤러.
 */
public class UpdateNickNameController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userNumber = Integer.parseInt(request.getParameter("userNumber"));
        String nickName = request.getParameter("nickName");

        boolean success = UsersService.getInstance().updateNickName(userNumber, nickName);

        ModelAndView mav = new ModelAndView();
        if (success) {
            mav.setPath("./mypageView.jsp"); // 성공 시 마이페이지로 이동
            mav.setRedirect(true);
        } else {
            mav.setPath("./error.jsp"); // 실패 시 에러 페이지로 이동
            mav.setRedirect(false);
        }
        return mav;
    }
}
