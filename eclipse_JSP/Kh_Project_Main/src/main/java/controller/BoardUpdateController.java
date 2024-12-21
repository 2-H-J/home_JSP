package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import dto.BoardFileDTO;
import dto.BoardsDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import service.BoardsService;
import view.ModelAndView;

public class BoardUpdateController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		int postNumber = Integer.parseInt(request.getParameter("postNumber"));
		String tag = request.getParameter("tag");
		
		BoardsService.getInstance().deleteBoardFile(postNumber);

		// 파일 업로드 처리 (Base64 인코딩)
        Iterator<Part> it = request.getParts().iterator();
        List<BoardFileDTO> fileList = new ArrayList<BoardFileDTO>();
        
        // 업로드할 파일 정보를 읽는 부분
        while (it.hasNext()) {
            Part part = it.next();
            if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
                // 파일 이름을 가져오기
                String fileName = part.getSubmittedFileName();

                // 파일을 바이트 배열로 읽어 들이고
                byte[] fileContent = part.getInputStream().readAllBytes();

                // Base64로 인코딩
                String base64Encoded = Base64.getEncoder().encodeToString(fileContent);
                
                // BoardFileDTO 생성 후 Base64 데이터와 파일 이름 저장
                BoardFileDTO fdto = new BoardFileDTO();
                fdto.setFileName(fileName); // 파일 이름 저장
                fdto.setFilePath(base64Encoded); // Base64 문자열 저장
                fileList.add(fdto);
            }
        }

		// 게시글 객체 생성 후 수정 내용 설정
		BoardsDTO dto = new BoardsDTO();
		dto.setPostNumber(postNumber);
		dto.setTitle(title);
		dto.setDescription(description);
		dto.setTag(tag);

		// 게시글 업데이트
		int count = BoardsService.getInstance().updateBoard(dto, fileList);
		System.out.println("데이터 등록 결과 : " + count);

		// ModelAndView 객체 생성
		ModelAndView view = new ModelAndView();
		view.setPath("./boardDetail.do?postNumber=" + postNumber);
		view.setRedirect(true);

		return view;
	}
}
