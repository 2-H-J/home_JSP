package controller;

import dto.RegionDTO;
import dto.UsersDTO;
import service.RegionService;
import view.ModelAndView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public class RegionInsertController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 세션에서 사용자 정보 가져오기
		HttpSession session = request.getSession();
		UsersDTO user = (UsersDTO) session.getAttribute("user");

		// 로그인 상태가 아니면 로그인 페이지로 리다이렉트
		if (user == null) {
			response.sendRedirect("signin.jsp");
			return null;
		}

		// 사용자 정보
		int userNumber = user.getUserNumber();

		// 파라미터에서 지역 정보 가져오기
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		double latitude = Double.parseDouble(request.getParameter("latitude"));
		double longitude = Double.parseDouble(request.getParameter("longitude"));

		System.out.println("x : " + latitude + "y : " + longitude);
		// 이미지 URL을 저장할 변수 (기본값 null로 초기화)
		String imageUrl = null;

		// 이미지 파일 처리
		Part imagePart = request.getPart("image"); // 업로드된 파일을 가져옵니다.
		if (imagePart != null && imagePart.getSize() > 0) {
			InputStream inputStream = imagePart.getInputStream(); // 이미지 파일의 InputStream을 가져옵니다.
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			byte[] data = new byte[1024];
			int length;

			// 파일 데이터를 읽어서 buffer에 저장
			while ((length = inputStream.read(data)) != -1) {
				buffer.write(data, 0, length);
			}

			// Base64로 변환하여 String으로 저장
			imageUrl = Base64.getEncoder().encodeToString(buffer.toByteArray());
		}

		// 디버깅용 출력
		System.out.println("title : " + title + " description : " + description);
		System.out.println("imageUrl : " + imageUrl);

		// RegionDTO 객체 생성
		RegionDTO region = new RegionDTO(title, description, imageUrl,latitude,longitude);

		// RegionService를 통해 데이터베이스에 지역 정보 삽입

		int count = RegionService.getInstance().insertRegion(region);

		ModelAndView view = new ModelAndView();
		if (count > 0) {
			view.setPath("./region.do"); // 지역 목록 페이지로 이동
		} else {
			view.setPath("./regionInsert.jsp"); // 실패 시 다시 작성 페이지로 돌아감
		}
		view.setRedirect(true);
		return view;
	}
}
