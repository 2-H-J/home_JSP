package controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import dto.RegionDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import service.RegionService;
import view.ModelAndView;

public class RegionUpdateController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 폼에서 전달된 파라미터 받기
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String regionNumberStr = request.getParameter("regionNumber");
		String imageUrl = request.getParameter("existingImageUrl");
		double latitude = Double.parseDouble(request.getParameter("latitude"));
		double longitude = Double.parseDouble(request.getParameter("longitude"));

		int regionNumber = Integer.parseInt(regionNumberStr);

		System.out.println("imageURL : " + imageUrl);
		System.out.println("x : " + latitude);
		System.out.println("y : " + longitude);

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

		// RegionDTO 객체 생성 후 수정된 내용 설정
		RegionDTO region = new RegionDTO();
		region.setRegionNumber(regionNumber);
		region.setTitle(title);
		region.setDescription(description);
		region.setImageUrl(imageUrl);
		region.setLatitude(latitude);
		region.setLongitude(longitude);

		// 지역 정보 업데이트
		int result = RegionService.getInstance().updateRegion(region); // updateRegion 메서드가 영향을 받은 행 수를 반환한다고 가정
		System.out.println("지역 정보 업데이트 결과 : " + result);

		// ModelAndView 객체 생성
		ModelAndView view = new ModelAndView();
		view.setPath("./regionDetail.do?regionNumber=" + regionNumber);
		view.setRedirect(true);

		return view;
	}
}
