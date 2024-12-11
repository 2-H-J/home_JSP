package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

/**
 * Controller 인터페이스는 MVC 패턴에서 컨트롤러 역할을 하는 클래스들이 구현해야 할 표준 메서드를 정의합니다.
 */
public interface Controller {

	/**
	 * 클라이언트의 요청(HttpServletRequest)과 응답(HttpServletResponse)을 처리하고, 처리 결과를 담은
	 * ModelAndView 객체를 반환하는 메서드입니다.
	 * 
	 * @param request  클라이언트로부터 받은 HTTP 요청 객체
	 * @param response 클라이언트로 반환할 HTTP 응답 객체
	 * @return ModelAndView 객체 (뷰 정보와 데이터를 포함)
	 * @throws ServletException 요청 처리 중 서블릿 관련 예외가 발생할 경우
	 * @throws IOException      요청 처리 중 입출력 관련 예외가 발생할 경우
	 */
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
}