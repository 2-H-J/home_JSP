package controller;

import java.io.IOException;

import dto.UsersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UsersService;
import view.ModelAndView;

public class UserDetailController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 사용자 번호 가져오기
        int userNumber = Integer.parseInt(request.getParameter("user_number"));

        // 사용자 상세 정보 조회
        UsersDTO user = UsersService.getInstance().selectUserByNumber(userNumber);
        request.setAttribute("user", user);

        // 상세 정보 페이지로 포워딩
        ModelAndView view = new ModelAndView();
        view.setPath("user_detail.jsp");
        view.setRedirect(false);
        return view;
    }
}