package controller;

import java.io.IOException;

import dto.BoardMemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardMemberService;
import view.ModelAndView;

public class LoginController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("LoginController: execute 메서드 시작");

        // 1. id, password 받아오기
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        System.out.println("LoginController: 입력받은 id = " + id + ", password = " + password);

        // 2. BoardMemberService로 아이디와 비밀번호 전달 - 결과로 BoardMemberDTO 받음
        BoardMemberDTO dto = BoardMemberService.getInstance().login(id, password);
        if (dto != null) {
            System.out.println("LoginController: 로그인 성공, 사용자 정보 = " + dto);
        } else {
            System.out.println("LoginController: 로그인 실패, 잘못된 자격 증명");
        }

        ModelAndView view = new ModelAndView();

        // 3. 로그인 성공
        if (dto != null) {
            request.getSession().setAttribute("user", dto);
            view.setPath("./boardMain.do");
            view.setRedirect(true);
            System.out.println("LoginController: ./boardMain.do로 리다이렉트");
        } else {
            // 4. 로그인 실패
            view.setPath("login.jsp");
            System.out.println("LoginController: login.jsp로 포워드");
        }

        System.out.println("LoginController: execute 메서드 종료");
        return view;
    }
}
