package controller;

import java.io.IOException;
import java.util.List;

import dto.BoardMemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardMemberService;
import view.ModelAndView;

// 모든 회원 정보를 조회하여 결과를 JSP로 전달하는 컨트롤러 클래스
public class SelectAllMemberController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 전체 회원 정보를 데이터베이스로부터 가져옴
        // BoardMemberService 클래스의 getInstance() 메소드를 통해 서비스 객체를 가져옴 (싱글톤 패턴 사용)
        // selectAllMember() 메서드를 호출하여 모든 회원 정보(List 형태)를 받아옴
        List<BoardMemberDTO> list = BoardMemberService.getInstance().selectAllMember();

        // ModelAndView 객체를 생성하여 뷰에 전달할 데이터 및 이동할 경로 정보를 설정할 준비를 함
        ModelAndView view = new ModelAndView();

        // 회원 정보 목록 데이터를 ModelAndView 객체에 추가함
        // "list"라는 키를 사용하여 회원 정보 목록을 저장 -> 나중에 JSP에서 이 키로 데이터를 참조하여 사용 가능
        view.addObject("list", list);

        // 이동할 페이지의 경로를 설정함
        // 여기서는 JSP 파일인 "member_list.jsp"로 이동하도록 지정
        // ./는 현재 경로 기준을 의미하며 상대경로로 지정됨
        view.setPath("./member_list.jsp");

        // 페이지 이동 방식을 포워드 방식으로 설정함
        // setRedirect(false)로 설정함으로써 서버 내부적으로 request와 response를 유지하며 페이지를 이동함
        // 이를 통해 클라이언트는 URL이 변경되지 않고 기존 요청 정보를 유지한 상태로 새로운 페이지로 이동하게 됨
        view.setRedirect(false);

        // 최종적으로 설정한 ModelAndView 객체를 반환하여 DispatcherServlet이 이를 처리할 수 있도록 함
        return view;
    }
}
