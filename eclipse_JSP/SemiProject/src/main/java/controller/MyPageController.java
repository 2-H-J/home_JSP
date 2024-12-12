package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dto.UsersDTO;
import view.ModelAndView;

public class MyPageController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 세션에서 사용자 정보 가져오기
        UsersDTO user = (UsersDTO) request.getSession().getAttribute("user");

        if (user == null) {
            // 로그인되지 않은 경우 로그인 페이지로 리다이렉트
            ModelAndView mav = new ModelAndView();
            mav.setPath("./loginView.jsp");
            mav.setRedirect(true);
            return mav;
        }

        // 사용자 정보를 request에 설정
        request.setAttribute("user", user);

        // 디버깅: 사용자 정보 출력
        System.out.println("사용자 이름: " + user.getUserName());

        // 마이페이지 JSP로 포워드
        ModelAndView mav = new ModelAndView();
        mav.setPath("./mypageView.jsp");
        mav.setRedirect(false);
        return mav;
    }
}
