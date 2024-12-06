package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

public interface Controller {
	ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	// 이 메서드는 클라이언트 요청을 처리하고, 해당 요청에 대한 응답으로 어떤 뷰(View)와 데이터를 사용자에게 반환할지를 결정
	
//	 1. ModelAndView 반환 타입: 
//		- ModelAndView는 Spring MVC에서 데이터와 뷰의 논리적 이름을 함께 담는 객체입니다.
//		- Model은 데이터(Model) 객체를 표현하며, 컨트롤러에서 뷰로 전달하고자 하는 데이터를 담습니다.
//		- View는 클라이언트에게 응답으로 반환할 JSP, Thymeleaf, Freemarker와 같은 뷰의 논리적 이름입니다.
}
