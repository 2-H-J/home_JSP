package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dto.BoardFileDTO;
import dto.BoardsDTO;
import dto.UsersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import service.BoardsService;
import view.ModelAndView;

public class BoardInsertController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 세션에서 UsersDTO 객체 가져오기
		HttpSession session = request.getSession();
		UsersDTO user = (UsersDTO) session.getAttribute("user");

		if (user == null) {
			// 세션에 사용자 정보가 없을 경우 로그인 페이지로 리다이렉트
			response.sendRedirect("signin.jsp");
			return null;
		}

		int userNumber = user.getUserNumber();
		// 파라미터로 전달된 게시글 제목과 설명을 가져옴
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String tag = request.getParameter("tag");
		
		System.out.println(title);
		System.out.println(description);
		System.out.println(tag);
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

		BoardsDTO dto = new BoardsDTO();
		dto.setUserNumber(userNumber);
		dto.setTag(tag);
		dto.setTitle(title);
		dto.setDescription(description);
		

		int count = BoardsService.getInstance().insertBoard(dto, fileList);

		ModelAndView view = new ModelAndView();
		view.setPath("./boardDetail.do?postNumber="+dto.getPostNumber());
		view.setRedirect(true);

		return view;
	}
}