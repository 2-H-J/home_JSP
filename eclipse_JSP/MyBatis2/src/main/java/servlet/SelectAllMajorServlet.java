package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapper.MajorMapper;

import java.io.IOException;
import java.util.List;

import config.DBManager;
import dto.MajorDTO;

/**
 * Servlet implementation class SelectAllMajorServlet
 */
@WebServlet("/allMajor.do")
public class SelectAllMajorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * 기본 생성자.
     * 서블릿 객체를 생성할 때 호출됩니다.
     */
    public SelectAllMajorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * GET 요청을 처리하는 메서드입니다.
     * 학과 정보를 조회하고, 이를 JSP 페이지에 전달하여 화면에 출력합니다.
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // MajorMapper 객체를 가져오기 위해 DBManager로부터 세션을 가져옵니다.
        MajorMapper mapper = DBManager.getInstance().getSession().getMapper(MajorMapper.class);
        
        // 1. 전체 학과 정보를 데이터베이스로부터 읽어옵니다.
        // MajorMapper 인터페이스의 selectAllMajor 메서드를 호출하여 major 테이블의 모든 행을 조회합니다.
        List<MajorDTO> list = mapper.selectAllMajor();
        
        // 2. 조회된 학과 리스트를 request 객체에 저장합니다.
        // 이렇게 저장된 데이터는 JSP 페이지에서 참조할 수 있습니다.
        request.setAttribute("majorList", list);
        
        // 3. JSP 페이지로 포워딩합니다.
        // request 객체와 response 객체를 major_list.jsp로 전달하여, 학과 정보를 출력할 수 있도록 합니다.
        request.getRequestDispatcher("./major_list.jsp").forward(request, response);
    }

    /**
     * POST 요청을 처리하는 메서드입니다.
     * 현재는 POST 요청도 GET 요청과 동일한 방식으로 처리합니다.
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // POST 요청을 GET 요청처럼 처리합니다.
        // 예를 들어, 데이터를 폼(form)을 통해 제출한 경우에도 학과 리스트를 보여줍니다.
        doGet(request, response);
    }
}
