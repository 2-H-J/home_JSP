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
		System.out.println("[LoginController] : execute 메서드 시작");

		// 1. 클라이언트로부터 전달된 id와 password 값을 가져오기
		String loginId = request.getParameter("loginId"); // 로그인 아이디
		String password = request.getParameter("loginId"); // 비밀번호 (오타 확인 필요)
		System.out.println("[LoginController] 입력 받은 loginId / password : " + loginId + " / " + password);

		// 2. UsersService를 통해 로그인 검증
		// 로그인 성공 시 사용자 정보를 담은 UsersDTO 객체를 반환
		UsersDTO dto = UsersService.getInstance().login(loginId, password);
		if (dto != null) {
			System.out.println("LoginController: 로그인 성공, 사용자 정보 = " + dto);
		} else {
			System.out.println("LoginController: 로그인 실패, 잘못된 자격 증명");
		}

		// ModelAndView 객체 생성
		ModelAndView view = new ModelAndView();
		System.out.println("[LoginController] : ModelAndView객체 "+view);
		// 3. 로그인 성공 처리
		if (dto != null) {
			// 세션에 사용자 정보를 저장
			request.getSession().setAttribute("user", dto);

			// 성공 시 메인 페이지로 리다이렉트 설정
			view.setPath("./index.do");
			view.setRedirect(true); // 클라이언트에게 새 요청을 보내도록 리다이렉트
			System.out.println("LoginController: ./index.do로 리다이렉트");
		} else {
			// 4. 로그인 실패 처리
			// 로그인 실패 시 로그인 화면으로 다시 포워드
			view.setPath("./loginView.jsp");
			view.setRedirect(false); // 서버 내부에서 포워드
			System.out.println("LoginController: ./loginView.jsp로 포워드");
		}

		// ModelAndView 반환
		return view;
	}
}
