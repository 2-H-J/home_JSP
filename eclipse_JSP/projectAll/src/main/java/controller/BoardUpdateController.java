package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
		
		// 1. 해당 게시글의 파일 경로들을 가져옴
		List<BoardFileDTO> list = BoardsService.getInstance().selectFileList(postNumber);
		// 2. 경로에 해당하는 파일들을 물리적으로 삭제 - File 클래스 이용
		list.forEach(item -> {
			File file = new File(item.getFilePath());
			file.delete();// 물리적으로 삭제
		});
		BoardsService.getInstance().deleteBoardFile(postNumber);

		// 파일 업로드 처리
		File root = new File("c:\\fileupload");
		// 해당 경로가 있는지 체크, 없으면 해당 경로 생성
		if (!root.exists()) {
			System.out.println("파일 업로드할 폴더 및 경로 생성");
			root.mkdirs();
		}

		Iterator<Part> it = request.getParts().iterator();
		List<BoardFileDTO> fileList = new ArrayList<BoardFileDTO>();
		// 업로드할 파일 정보를 읽는 부분
		while (it.hasNext()) {
			Part part = it.next();
			if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
				// 파일 쓰기
				part.write(root.getAbsolutePath() + "\\" + part.getSubmittedFileName());
				// BoardFileDTO 만들어서 리스트 추가
				// 게시글 번호, 파일 경로 가지고서 BoardFileDTO 생성
				// 리스트에 추가
				BoardFileDTO fdto = new BoardFileDTO();
				fdto.setFilePath(root.getAbsolutePath() + "\\" + part.getSubmittedFileName());
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
