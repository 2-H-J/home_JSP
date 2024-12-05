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

public class SelectAllMember2Controller implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 전체 회원정보 리스트로 받음
		List<BoardMemberDTO> list = BoardMemberService.getInstance().selectAllMember();
		// 받은 회원정보 개수
		int count = list.size();
		// 조회한 현재 날짜 시간도 문자열 저장 YYYY-MM-DD HH:mm:ss
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		Date date = Calendar.getInstance().getTime();
		String dateString = sdf.format(date);

		// JSON으로 변환
		JSONObject json = new JSONObject();
		// JSON에 데이터 추가
		json.put("list", list);
		json.put("count", count);
		json.put("date", dateString);

		System.out.println(json.toString());

		response.getWriter().println(json.toString());
		return null;// ajax로 호출시 페이지 이동이 없어서 ModelAndView 객체를 리턴하지 않음
	}

}