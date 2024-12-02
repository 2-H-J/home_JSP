package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapper.BoardMemberMapper;

import java.io.IOException;
import java.util.List;

import config.DBManager;
import dto.BoardMemberDTO;

/**
 * Servlet implementation class MemberAllServlet
 */
@WebServlet("/all.do")
public class MemberAllServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberAllServlet() {
        super();
        // 서블릿 클래스 생성자 - 초기화 필요시 사용 가능
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. BoardMemberMapper 인스턴스 가져오기
        // DBManager 인스턴스를 통해 BoardMemberMapper를 가져와서 데이터베이스 연결 및 사용 설정
        BoardMemberMapper mapper = DBManager.getInstance().getSession().getMapper(BoardMemberMapper.class);
        
        // 2. DAO에서 전체 회원 정보를 가져오기
        // 데이터베이스에서 전체 회원 리스트를 조회하여 List 형태로 받음
        List<BoardMemberDTO> list = mapper.selectAllMember();
        
        // 3. 가져온 회원 정보를 콘솔에 출력
        // 서버 측에서 정상적으로 데이터가 조회되었는지 확인하기 위해 리스트 출력
        System.out.println(list);
        
        // 4. request 영역에 회원 정보 리스트 저장
        // 조회된 회원 리스트를 JSP 페이지에서 접근할 수 있도록 request 객체에 설정
        request.setAttribute("list", list);
        
        // 5. 페이지 이동 - forward 방식으로 이동
        // 회원 정보를 포함한 member_list.jsp 페이지로 request와 response 객체를 넘겨 페이지 이동 수행
        request.getRequestDispatcher("member_list.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // POST 요청 처리 시, doGet 메소드를 호출하여 동일하게 처리
        doGet(request, response);
    }
}
