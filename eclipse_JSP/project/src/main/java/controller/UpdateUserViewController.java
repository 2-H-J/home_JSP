package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dto.UsersDTO;
import view.ModelAndView;

/**
 * UpdateUserViewController
 * - 사용자 정보 수정 화면으로 이동하는 요청을 처리
 */
public class UpdateUserViewController implements Controller {

    /**
     * 사용자 정보 수정 화면으로 이동 요청을 처리하고 결과를 반환한다.
     *
     * @param request  클라이언트로부터 들어온 HTTP 요청 객체
     * @param response 클라이언트로 전송할 HTTP 응답 객체
     * @return ModelAndView 객체 (화면 정보 및 전환 방식을 포함)
     * @throws ServletException 서블릿 처리 중 발생할 수 있는 예외
     * @throws IOException      입출력 처리 중 발생할 수 있는 예외
     */
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("[UpdateUserViewController] execute() 호출 -> 시작");

        // 세션에서 사용자 정보 가져오기
        UsersDTO user = (UsersDTO) request.getSession().getAttribute("user");
        if (user == null) {
            // 로그인 정보가 없으면 로그인 페이지로 이동
            System.out.println("[UpdateUserViewController] 세션 정보 없음 -> 로그인 페이지로 이동");
            ModelAndView view = new ModelAndView();
            view.setPath("./loginView.jsp");
            view.setRedirect(true); // 리다이렉트 설정
            return view;
        }

        // 사용자 정보를 request에 설정 (수정 폼에 표시될 값)
        System.out.println("[UpdateUserViewController] 세션 사용자 정보: " + user);
        request.setAttribute("user", user);

        // 정보 수정 폼 JSP로 이동
        System.out.println("[UpdateUserViewController] 사용자 정보 확인 -> 수정 페이지로 이동");
        ModelAndView view = new ModelAndView();
        view.setPath("./updateUserView.jsp");
        view.setRedirect(false); // 포워드 설정

        System.out.println("[UpdateUserViewController] execute() 완료 -> ModelAndView 반환");
        return view;
    }
}
