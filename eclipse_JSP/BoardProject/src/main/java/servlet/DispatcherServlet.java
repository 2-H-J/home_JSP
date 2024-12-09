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

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String rootPath = "/WEB-INF/views/";

    public DispatcherServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DispatcherServlet: GET 요청 처리 시작");

        // 요청한 command 추출
        String[] path = request.getRequestURI().split("/");
        String command = path[path.length - 1].replace(".do", "");
        System.out.println("추출된 명령어: " + command);

        // Controller 생성
        Controller controller = HandlerMapping.getInstance().createController(command);
        if (controller != null) {
            System.out.println("생성된 컨트롤러: " + controller.getClass().getSimpleName());
        } else {
            System.out.println("명령어에 매칭되는 컨트롤러를 찾을 수 없음: " + command);
        }

        // execute 호출
        ModelAndView view = null;
        if (controller != null) {
            view = controller.execute(request, response);
            System.out.println("컨트롤러 실행 완료. ModelAndView 경로: " + view.getPath());
        }

        if (view != null) {
            // 데이터 request 영역에 저장
            Iterator<String> it = view.getModel().keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                request.setAttribute(key, view.getModel().get(key));
                System.out.println("Request 스코프에 추가된 데이터: " + key + " = " + view.getModel().get(key));
            }

            // 페이지 이동 처리
            if (view.isRedirect()) {
                System.out.println("리다이렉트 경로: " + view.getPath());
                response.sendRedirect(view.getPath());
            } else {
                System.out.println("포워딩 경로: " + rootPath + view.getPath());
                request.getRequestDispatcher(rootPath + view.getPath()).forward(request, response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DispatcherServlet: POST 요청 처리 시작");
        doGet(request, response);
    }
}
