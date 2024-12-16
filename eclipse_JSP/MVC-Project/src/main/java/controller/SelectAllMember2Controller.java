package controller;

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

// 전체 회원 정보를 조회하여 JSON 형식으로 클라이언트에 응답하는 컨트롤러 클래스
public class SelectAllMember2Controller implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 전체 회원 정보를 데이터베이스로부터 받아옴
        // BoardMemberService 클래스의 getInstance() 메소드를 통해 서비스 객체를 가져옴 (싱글톤 패턴 사용)
        // selectAllMember() 메소드를 호출하여 모든 회원 정보(List 형태)를 받아옴
        List<BoardMemberDTO> list = BoardMemberService.getInstance().selectAllMember();

        // 받은 회원 정보의 개수를 count 변수에 저장
        int count = list.size();

        // 현재 조회한 날짜와 시간을 문자열로 저장하기 위한 포맷 설정
        // SimpleDateFormat을 사용하여 형식을 "YYYY-MM-dd HH:mm:ss"로 설정
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Date date = Calendar.getInstance().getTime(); // 현재 시간 정보 가져오기
        String dateString = sdf.format(date); // 포맷에 맞게 현재 시간을 문자열로 변환

        // 결과를 JSON 형식으로 변환하기 위해 JSONObject 생성
        // JSON 객체는 클라이언트가 이해할 수 있는 형식으로 데이터를 전달하는데 적합함
        JSONObject json = new JSONObject();

        // JSON 객체에 조회한 회원 목록, 개수, 현재 시간 데이터를 추가함
        // JSON의 키와 값을 이용해 데이터를 삽입하여 클라이언트가 JSON 형태로 이해할 수 있도록 함
        json.put("list", list); // "list" 키에 전체 회원 목록을 저장
        json.put("count", count); // "count" 키에 회원 정보의 개수를 저장
        json.put("date", dateString); // "date" 키에 현재 시간 정보를 저장

        // 변환된 JSON 데이터를 콘솔에 출력 (디버깅 용도)
        // 실제 운영 시에는 불필요한 정보일 수 있지만, 개발 단계에서는 디버깅을 위해 매우 유용함
        System.out.println(json.toString());

        // JSON 형식의 데이터를 클라이언트에게 응답으로 보냄
        // 클라이언트는 이 데이터를 AJAX 요청에 대한 응답으로 받음
        // response.getWriter()를 사용하여 JSON 문자열을 HTTP 응답으로 전송함
        response.getWriter().println(json.toString());

        // AJAX 요청이기 때문에 페이지 이동이 필요 없으므로 null 반환
        // 페이지 이동은 없고, 데이터를 응답으로 직접 전송하기 때문에 ModelAndView 객체를 반환하지 않음
        return null;
    }

}
