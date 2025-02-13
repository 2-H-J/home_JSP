package controller;

// 필요한 클래스 및 인터페이스를 가져오기
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

/**
 * LoginViewController는 사용자가 로그인 페이지를 요청했을 때
 * 해당 JSP 파일로 이동하기 위해 실행되는 컨트롤러 클래스입니다.
 * Controller 인터페이스를 구현합니다.
 */
public class LoginViewController implements Controller {

    /**
     * HTTP 요청을 처리하고 해당하는 뷰(JSP)로 이동하기 위해 ModelAndView 객체를 반환합니다.
     * 
     * @param request  클라이언트로부터 전달된 HTTP 요청 객체
     * @param response 서버가 클라이언트로 응답하기 위한 HTTP 응답 객체
     * @return ModelAndView 뷰 정보와 이동 방식(redirect 여부)을 포함한 객체
     * @throws ServletException 서블릿에서 발생할 수 있는 예외
     * @throws IOException      입출력 관련 예외
     */
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 실행 메서드의 시작을 알리는 로그 출력
        System.out.println("LoginViewController: execute 메서드 시작");

        // ModelAndView 객체 생성
        // 이 객체는 뷰로 전달할 데이터와 이동 방식을 정의하는 데 사용됩니다.
        ModelAndView view = new ModelAndView();

        // 클라이언트 요청 처리 후 해당 뷰로 포워딩 설정
        // Redirect는 false로 설정하여 서버 내부에서 뷰로 이동하도록 합니다.
        view.setRedirect(false);

        // 이동할 뷰 파일 경로를 설정 (login.jsp)
        view.setPath("login.jsp");
        System.out.println("LoginViewController: 경로를 login.jsp로 설정 (redirect = false)");

        // 실행 메서드가 정상적으로 완료되었음을 알리는 로그 출력
        System.out.println("LoginViewController: execute 메서드 완료");

        // 생성한 ModelAndView 객체를 반환하여 Dispatcher가 이를 처리하도록 합니다.
        return view;
    }
}
