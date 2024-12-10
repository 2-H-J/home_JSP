package controller;

import java.io.IOException;

import dto.UsersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UsersService;
import view.ModelAndView;

/**
 * LoginController는 클라이언트의 로그인 요청을 처리하는 컨트롤러입니다. 클라이언트로부터 아이디와 비밀번호를 입력받아 인증을
 * 수행하고, 성공 또는 실패 여부에 따라 적절한 뷰로 이동합니다.
 */
public class LoginController implements Controller {

    /**
     * 클라이언트의 요청을 처리하고 결과에 따라 뷰를 반환합니다.
     * 
     * @param request  클라이언트의 HTTP 요청 객체
     * @param response 서버의 HTTP 응답 객체
     * @return ModelAndView 클라이언트 요청 결과에 따라 이동할 경로 및 데이터
     */
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("[LoginController] execute() 호출 -> 로그인 처리 시작");

        // 1. 클라이언트로부터 전달된 id와 password 값을 가져오기
        String loginId = request.getParameter("loginId"); // 로그인 아이디
        String password = request.getParameter("password"); // 비밀번호
        System.out.println("[LoginController] 입력된 값 -> loginId: " + loginId + ", password: " + password);

        // 2. UsersService를 통해 로그인 검증
        UsersDTO dto = UsersService.getInstance().login(loginId, password);
        if (dto != null) {
            System.out.println("[LoginController] 로그인 성공 -> 사용자 정보: " + dto);
        } else {
            System.out.println("[LoginController] 로그인 실패 -> 잘못된 자격 증명");
        }

        // ModelAndView 객체 생성
        ModelAndView view = new ModelAndView();

        // 3. 로그인 성공 처리
        if (dto != null) {
            // 세션에 사용자 정보를 저장
            request.getSession().setAttribute("user", dto);
            System.out.println("[LoginController] 세션에 사용자 정보 저장 완료 -> 사용자: " + dto.getNickName());

            // 성공 시 메인 페이지로 리다이렉트 설정
            view.setPath("./index.do");
            view.setRedirect(true);
            System.out.println("[LoginController] 리다이렉트 설정 -> Path: ./index.do");
        } else {
            // 4. 로그인 실패 처리
            view.setPath("./loginView.jsp");
            view.setRedirect(false);
            System.out.println("[LoginController] 포워드 설정 -> Path: ./loginView.jsp");
        }

        System.out.println("[LoginController] execute() 완료 -> ModelAndView 반환: Path=" + view.getPath() + ", Redirect=" + view.isRedirect());
        return view;
    }
}
