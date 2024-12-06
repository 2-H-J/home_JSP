package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import controller.HandlerMapping;
import controller.Controller;
import view.ModelAndView;

import java.io.IOException;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DispatcherServlet() {
        super();
        System.out.println("[DispatcherServlet] 생성자 호출 -> DispatcherServlet 인스턴스 생성");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getRequestURI().split("/")[2].replace(".do", "");
        Controller controller = HandlerMapping.getInstance().createController(command);
        ModelAndView view = controller.execute(request, response);

        if (view != null) {
            if (view.isRedirect()) {
                response.sendRedirect(view.getPath());
            } else {
                request.getRequestDispatcher(view.getPath()).forward(request, response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
