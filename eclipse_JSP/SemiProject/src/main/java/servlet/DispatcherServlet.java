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
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        super();
        System.out.println("[DispatcherServlet] 생성자 호출됨 -> DispatcherServlet 인스턴스 생성됨");
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("[DispatcherServlet] doGet() 호출됨 -> 클라이언트의 GET 요청 수신");

        // 요청한 command 추출
        String[] path = request.getRequestURI().split("/");
        String command = path[path.length - 1].replace(".do", "");
        System.out.println("[DispatcherServlet] 요청된 command: " + command + " -> HandlerMapping에서 해당 Controller를 생성할 것");

        // Controller 생성
        Controller controller = HandlerMapping.getInstance().createController(command);
        System.out.println("[DispatcherServlet] 생성된 Controller: " + controller + " -> Controller가 요청을 처리함");

        // execute 메서드 호출
        ModelAndView view = null;
        if (controller != null) {
            System.out.println("[DispatcherServlet -> Controller] execute() 메서드 호출 -> 요청 로직 처리 시작");
            view = controller.execute(request, response);
            System.out.println("[Controller -> DispatcherServlet] 생성된 ModelAndView: " + view + " -> 요청 처리 결과를 포함하여 반환");
        }

        if (view != null) {
            // 데이터 request 영역에 저장
            Iterator<String> it = view.getModel().keySet().iterator();
            System.out.println("[DispatcherServlet] Model 데이터 저장 시작");
            while (it.hasNext()) {
                String key = it.next();
                System.out.println("[DispatcherServlet] key: " + key + " -> request 영역에 저장");
                request.setAttribute(key, view.getModel().get(key));
            }

            // 페이지 이동 처리
            if (view.isRedirect()) {
                System.out.println("[DispatcherServlet] 리다이렉트 처리: " + view.getPath() + " -> 클라이언트를 해당 경로로 리다이렉트");
                response.sendRedirect(view.getPath());
            } else {
                System.out.println("[DispatcherServlet] 포워드 처리: " + view.getPath() + " -> 요청을 해당 JSP로 포워드");
                request.getRequestDispatcher(view.getPath()).forward(request, response);
            }
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("[DispatcherServlet] doPost() 호출됨 -> 클라이언트의 POST 요청 수신, doGet() 호출");
        doGet(request, response);
    }
}

// 이 코드는 사용자가 요청한 *.do 패턴에 대해 요청을 처리하는 DispatcherServlet입니다.
// 요청된 URL로부터 command를 추출하여 적절한 Controller를 생성하고, 생성된 Controller의 execute 메서드를 호출하여 로직을 처리합니다.
// 그 후, ModelAndView 객체를 사용하여 페이지 이동을 수행합니다. 리다이렉트인지 포워드인지에 따라 다른 이동 방식을 적용합니다.
// 각 과정에서 System.out.println()을 통해 디버깅 및 흐름 추적이 가능하도록 로그를 남기며, 파일 간의 흐름과 데이터 이동 경로를 명확히 알 수 있도록 합니다.
