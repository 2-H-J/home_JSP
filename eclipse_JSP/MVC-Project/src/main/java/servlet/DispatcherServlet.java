package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

import java.io.IOException;
import java.util.Iterator;

import controller.Controller;
import controller.HandlerMapping;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("*.do") // 모든 ".do"로 끝나는 요청을 이 서블릿으로 매핑함
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        super();
        // 기본 생성자
    }

    /**
     * GET 요청을 처리하는 메소드
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청한 URL에서 command를 추출하는 과정
        // 예를 들어, "/example.do"라는 요청이 들어왔을 때 "example"을 추출함
        String[] path = request.getRequestURI().split("/");
        String command = path[path.length - 1].replace(".do", "");

        // 요청에 따라 적절한 컨트롤러를 생성
        Controller controller = HandlerMapping.getInstance().createController(command);

        // 컨트롤러의 execute 메소드를 호출하여 요청 처리 및 결과를 받음
        ModelAndView view = null;
        if (controller != null) {
            view = controller.execute(request, response);
        }

        if (view != null) {
            // Model 데이터를 request 영역에 저장
            // 컨트롤러에서 ModelAndView 객체에 저장한 데이터를 request에 셋팅하여 뷰에서 사용할 수 있도록 함
            Iterator<String> it = view.getModel().keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                request.setAttribute(key, view.getModel().get(key));
            }

            // 페이지 이동 처리 (Redirect 또는 Forward)
            if (view.isRedirect()) {
                // 리다이렉트 방식으로 페이지 이동
                response.sendRedirect(view.getPath());
            } else {
                // 포워드 방식으로 페이지 이동
                request.getRequestDispatcher(view.getPath()).forward(request, response);
            }
        }
    }

    /**
     * POST 요청을 처리하는 메소드
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // POST 요청도 동일하게 doGet 메소드에서 처리함
        doGet(request, response);
    }
}
