package controller;

import java.io.IOException;

import dto.BoardMemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardMemberService;
import view.ModelAndView;

public class LoginController implements Controller {

    // LoginController 클래스는 로그인 요청을 처리하는 컨트롤러입니다.
    // - 클라이언트가 로그인 폼에서 입력한 데이터를 받아 인증을 처리합니다.
    // - 인증 결과에 따라 세션에 사용자 정보를 저장하거나 로그인 페이지로 이동시킵니다.

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. 클라이언트 요청에서 아이디(id)와 비밀번호(password)를 추출
        // - request.getParameter(): 클라이언트가 HTML 폼에서 입력한 데이터를 키-값 형태로 가져옵니다.
        // - 아이디와 비밀번호는 로그인 폼에서 전달된 입력 데이터입니다.
        String id = request.getParameter("id"); // 사용자가 입력한 아이디
        String password = request.getParameter("password"); // 사용자가 입력한 비밀번호
        System.out.println("[DEBUG] 입력된 아이디(id): " + id + ", 비밀번호(password): " + password);

        // 2. BoardMemberService를 통해 아이디와 비밀번호 검증
        // - BoardMemberService는 비즈니스 로직을 처리하는 서비스 계층입니다.
        // - getInstance(): 싱글톤 패턴으로 BoardMemberService의 인스턴스를 반환합니다.
        //   - 싱글톤(Singleton): 클래스의 인스턴스를 하나만 생성하여 어디서든 동일한 인스턴스를 사용하도록 보장하는 디자인 패턴입니다.
        //   - getInstance() 메서드는 클래스 내부에 생성된 단일 객체를 반환합니다.
        // - login() 메서드는 전달받은 아이디와 비밀번호로 인증을 시도합니다.
        //   - 성공 시, BoardMemberDTO 객체(사용자 정보 포함)를 반환
        //   - 실패 시, null 반환
        BoardMemberDTO dto = BoardMemberService.getInstance().login(id, password);
        System.out.println("[DEBUG] 로그인 결과(dto): " + dto);

        // 3. ModelAndView 객체 생성
        // - ModelAndView는 요청 처리 결과와 이동할 뷰(View)를 설정하는 객체입니다.
        // - 뷰 경로를 설정하고 클라이언트에게 데이터를 전달할 수 있습니다.
        ModelAndView view = new ModelAndView();

        // 4. 로그인 결과에 따라 처리 분기
        if (dto != null) { // 로그인 성공
            // [성공 처리]
            // - HttpSession 객체를 통해 사용자 정보를 세션에 저장합니다.
            // - request.getSession(): 현재 요청과 연관된 세션을 가져옵니다.
            // - setAttribute(): 세션에 데이터를 저장하는 메서드입니다.
            //   - key: "user" (사용자 정보를 저장하는 키)
            //   - value: 로그인된 사용자 정보를 담은 BoardMemberDTO 객체
            System.out.println("[INFO] 로그인 성공, 사용자 정보를 세션에 저장합니다.");
            request.getSession().setAttribute("user", dto);

            // - ModelAndView의 setPath() 메서드를 사용하여 이동할 페이지를 설정합니다.
            // - "./boardMain.do": 로그인 성공 후 이동할 메인 페이지 경로
            // - setRedirect(true): 리다이렉트 방식으로 클라이언트를 해당 페이지로 이동시킵니다.
            view.setPath("./boardMain.do"); // 메인 페이지 경로 설정
            view.setRedirect(true); // 리다이렉트 방식 설정
        } else { // 로그인 실패
            // [실패 처리]
            // - 로그인 실패 시 클라이언트를 로그인 페이지(login.jsp)로 이동시킵니다.
            // - 로그인 페이지 경로를 설정하고, 리다이렉트를 사용하지 않습니다(포워딩).
            System.out.println("[INFO] 로그인 실패, 로그인 페이지로 이동합니다.");
            view.setPath("login.jsp"); // 로그인 페이지 경로 설정
        }

        // 5. 처리 결과 반환
        // - ModelAndView 객체를 반환하여 클라이언트 요청 처리를 종료합니다.
        // - 반환된 객체는 컨트롤러의 결과를 뷰(View)로 전달하는 데 사용됩니다.
        System.out.println("[INFO] 로그인 요청 처리 완료");
        return view;
    }
}




