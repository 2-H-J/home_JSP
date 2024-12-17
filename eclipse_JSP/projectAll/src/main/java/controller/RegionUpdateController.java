package controller;

import java.io.IOException;

import dto.RegionDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        int regionNumber = Integer.parseInt(regionNumberStr);
        String imageUrl = request.getParameter("imageUrl");

        // RegionDTO 객체 생성 후 수정된 내용 설정
        RegionDTO region = new RegionDTO();
        region.setRegionNumber(regionNumber);
        region.setTitle(title);
        region.setDescription(description);
        region.setImageUrl(imageUrl);

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
