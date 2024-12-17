package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dto.UsersDTO;
import service.UsersService;
import view.ModelAndView;

/**
 * MyPageViewController 클래스는 마이페이지로 이동하는 요청을 처리하는 컨트롤러 역할을 수행한다.
 */
public class MyPageViewController implements Controller {

    private UsersService usersService = UsersService.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("[MyPageViewController] execute() 호출 -> 시작");

        // 1. 세션에서 사용자 번호 가져오기
        Integer userNumber = (Integer) request.getSession().getAttribute("userNumber");

        if (userNumber == null) {
            System.out.println("[MyPageViewController] 세션에 사용자 번호가 없습니다. -> 로그인 페이지로 이동");
            request.getSession().invalidate(); // 세션 초기화
            ModelAndView view = new ModelAndView();
            view.setPath("./loginView.jsp");
            view.setRedirect(true);
            return view;
        }


        // 2. 사용자 정보 새로 조회
        UsersDTO user = usersService.findUserByUserNumber(userNumber);
        if (user == null) {
            System.out.println("[MyPageViewController] 세션에 사용자 정보가 없습니다. -> 로그인 페이지로 리다이렉트");
            ModelAndView view = new ModelAndView();
            view.setPath("./loginView.jsp");
            view.setRedirect(true); // 로그인 페이지로 리다이렉트
            return view;
        } else {
            // 사용자 정보 확인 로그
            System.out.println("[MyPageViewController] 세션 사용자 정보 확인 -> "
                + "이름: " + user.getUserName()
                + ", 아이디: " + user.getLoginId()
                + ", 닉네임: " + user.getNickName()
                + ", 이메일: " + user.getUserEmail());
        }

        // 3. 사용자 정보를 request 및 세션에 설정
        request.setAttribute("user", user);
        request.getSession().setAttribute("user", user); // 세션에 최신 사용자 정보 저장
        System.out.println("[MyPageViewController] 사용자 정보 확인 -> 프로필 이미지: " + user.getProfileImageUrl());

        // 4. 마이페이지 JSP로 포워드
        ModelAndView view = new ModelAndView();
        view.setPath("./mypageView.jsp");
        view.setRedirect(false);

        System.out.println("[MyPageViewController] 마이페이지로 포워드 설정 완료 -> Path: " + view.getPath());
        System.out.println("[MyPageViewController] execute() 완료 -> ModelAndView 반환");

        return view;
    }
}
