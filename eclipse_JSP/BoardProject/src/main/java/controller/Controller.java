package controller;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

public interface Controller {
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}