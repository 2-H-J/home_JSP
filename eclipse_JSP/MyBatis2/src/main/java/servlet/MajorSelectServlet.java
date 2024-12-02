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
 * Servlet implementation class MajorSelectServlet
 */
@WebServlet("/majorSelectList.do")
public class MajorSelectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 기본 생성자 - 서블릿 객체 생성 시 호출됨
    // 서블릿이 생성될 때 제일 먼저 호출되는 기본 생성자입니다. 보통 특별한 초기화 작업은 필요하지 않지만,
    // 여기서는 부모 클래스의 생성자를 호출하여 서블릿을 초기화하는 역할을 합니다.
    public MajorSelectServlet() {
        super();
    }

    // GET 요청 처리 메서드 - majorSelectList.do 경로로 GET 요청이 들어올 때 실행됨
    // 사용자가 /majorSelectList.do 경로로 GET 요청을 보낼 때 호출됩니다.
    // 이 메서드는 데이터 조회와 결과를 JSP 페이지로 전달하는 역할을 합니다.
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. MajorMapper 객체를 얻기 위해 DBManager를 통해 세션을 가져옴
        //    DBManager를 통해 데이터베이스 세션을 얻고, 그 세션을 사용하여 MajorMapper 객체를 생성합니다.
        //    MajorMapper는 데이터베이스와 상호작용하여 전공 정보를 가져오기 위한 인터페이스입니다.
        MajorMapper mapper = 
                DBManager.getInstance().getSession().getMapper(MajorMapper.class);
        
        // 2. 조회할 전공 번호를 배열로 선언 (전공 번호는 문자열로 구성)
        //    예시로 전공 번호 "02", "07", "3"을 배열로 선언하여 이들에 해당하는 전공 정보를 조회합니다.
        //    이 배열은 검색 조건으로 사용되며, 특정 전공에 대한 정보를 조회하는 데 사용됩니다.
        String[] arr = {"02", "07", "3"};
        
        // 3. MajorMapper의 selectMajorList 메서드를 호출하여 전공 목록을 가져옴
        //    MajorMapper 인터페이스의 selectMajorList 메서드를 호출하여 전공 번호 배열에 해당하는 전공 리스트를 가져옵니다.
        //    이 과정에서 데이터베이스와의 상호작용이 일어나며, 해당하는 전공 정보를 List 형태로 반환받습니다.
        List<MajorDTO> list = mapper.selectMajorList(arr);
        
        // 4. request 객체에 조회된 전공 리스트를 "majorList"라는 이름으로 저장
        //    조회된 전공 리스트를 "majorList"라는 이름으로 request 객체에 저장합니다.
        //    이 정보는 JSP 페이지에서 사용하기 위해 저장되며, JSP에서 ${majorList}로 접근할 수 있게 됩니다.
        request.setAttribute("majorList", list);
        
        // 5. major_list.jsp로 페이지 이동 (forward 방식으로 이동)
        //    데이터를 보여주기 위해 major_list.jsp로 페이지를 이동합니다. forward 방식은 서버 내부에서 페이지를 전환하는 방식으로,
        //    클라이언트에게는 URL이 변경되지 않으며, request 객체에 저장된 데이터가 그대로 전달됩니다.
        request.getRequestDispatcher("major_list.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    // POST 요청 처리 메서드 - majorSelectList.do 경로로 POST 요청이 들어올 때 실행됨
    // 사용자가 /majorSelectList.do 경로로 POST 요청을 보낼 때 호출됩니다.
    // 이 메서드는 기본적으로 doGet 메서드를 호출하여 GET 요청과 동일하게 처리합니다.
    // 즉, GET 요청과 동일한 로직으로 처리하여 데이터를 조회하고 JSP로 전달합니다.
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
