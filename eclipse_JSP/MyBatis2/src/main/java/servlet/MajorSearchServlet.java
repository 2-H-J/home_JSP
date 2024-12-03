package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapper.MajorMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import config.DBManager;
import dto.MajorDTO;

/**
 * Servlet implementation class MajorSearchServlet
 * 전공 검색을 처리하는 서블릿 클래스
 * 클라이언트의 요청에 따라 전공 정보를 검색하고 그 결과를 반환하는 역할을 합니다.
 */
@WebServlet("/searchMajor.do") // 이 서블릿은 "searchMajor.do" 경로로 접근할 수 있습니다.
public class MajorSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L; // 클래스의 버전 관리 용도로 사용되는 고유 ID
       
    public MajorSearchServlet() {
        super(); // HttpServlet의 기본 생성자를 호출합니다.
    }
    
    /**
     * GET 요청을 처리하는 메서드
     * @param request 클라이언트로부터의 요청 정보
     * @param response 서버의 응답 정보
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 검색 종류와 검색어 받기
        String type = request.getParameter("type"); // 검색 종류 (예: 학과 번호로 검색 또는 학과 이름으로 검색)
        String search = request.getParameter("search"); // 검색어
        
        // Mapper로 보낼 데이터를 Map 형태로 생성 (데이터는 키-값 쌍으로 묶음)
        // 받는 값이 문자열일 경우 String, String을 사용할 수 있으나 다양한 유형의 데이터가 포함될 수 있어 Object 사용
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", type); // 검색 종류를 맵에 추가 (예: 학과 번호 또는 학과 이름 검색)
        map.put("search", search); // 검색어를 맵에 추가
        
        System.out.println(map); // 디버깅 용도로 데이터 출력 (요청된 검색 정보를 콘솔에 출력)
        
        // MajorMapper 객체 생성 및 데이터베이스 연결
        // MyBatis를 통해 MajorMapper 인터페이스의 구현체를 생성하여 데이터베이스와 연결합니다.
        MajorMapper mapper = DBManager.getInstance().getSession().getMapper(MajorMapper.class);
        
        // 검색 결과 리스트를 가져옴
        // mapper의 searchMajor 메서드를 호출하여 전공 검색 결과를 가져옵니다.
        List<MajorDTO> list = mapper.searchMajor(map);
        
        // 검색 결과를 request 객체에 저장하여 JSP로 전달
        // 검색 결과를 "majorList"라는 이름으로 request 객체에 저장합니다. JSP에서 이 데이터를 사용할 수 있습니다.
        request.setAttribute("majorList", list);
        
        // 검색 결과 페이지로 포워딩
        // 검색 결과를 표시하기 위해 "major_search_view.jsp"로 요청을 포워딩합니다.
        request.getRequestDispatcher("major_search_view.jsp").forward(request, response);
    }

    /**
     * POST 요청을 처리하는 메서드 (GET 메서드를 호출하여 동일하게 처리)
     * @param request 클라이언트로부터의 요청 정보
     * @param response 서버의 응답 정보
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // POST 요청도 GET 요청과 동일하게 처리
        // 클라이언트가 POST 방식으로 요청을 보내더라도 동일한 검색 처리를 수행합니다.
        doGet(request, response);
    }

}
