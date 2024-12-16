package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import dto.BoardMemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardMemberService;
import view.ModelAndView;

public class SelectMemberNameController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 클라이언트로부터 'search'라는 이름의 파라미터를 받아 출력
        // 여기서 null이 나올 수 있는데, 그 이유는 데이터를 URL 쿼리 스트링으로 보내지 않았기 때문임
        System.out.println(request.getParameter("search"));

        // POST 요청으로 body 영역에 데이터를 보낼 경우 이를 읽어오기 위한 부분
        // request의 InputStream을 통해 데이터를 읽음
        BufferedReader br = request.getReader();
        String str = "";
        StringBuilder builder = new StringBuilder();

        // 실제 클라이언트가 보낸 데이터를 한 줄씩 읽어서 문자열로 변환
        while ((str = br.readLine()) != null) {
            builder.append(str);
        }

        // 읽어온 데이터를 출력 (JSON 형태의 문자열로 예상됨)
        System.out.println(builder.toString());

        // 문자열을 JSON 객체로 변환 후 'search' 키에 해당하는 값 추출
        JSONObject json = new JSONObject(builder.toString());
        String search = json.getString("search");
        System.out.println(search);

        // 'search' 값을 사용하여 회원 이름 검색 수행
        List<BoardMemberDTO> list = BoardMemberService.getInstance().selectNameMember(search);

        // 검색된 회원 정보의 개수를 확인
        int count = list.size();

        // 현재 조회한 날짜와 시간을 문자열로 저장 (형식: YYYY-MM-DD HH:mm:ss)
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Date date = Calendar.getInstance().getTime();
        String dateString = sdf.format(date);

        // JSON 객체로 결과를 변환
        json = new JSONObject();
        
        // 검색 결과 목록, 결과 개수, 현재 시간 등을 JSON에 추가
        json.put("list", list);
        json.put("count", count);
        json.put("date", dateString);

        // 최종 JSON을 출력하고, 클라이언트로 전송
        System.out.println(json.toString());

        response.getWriter().println(json.toString());

        // 페이지 이동이 없으므로 null 반환
        return null;
    }

}
