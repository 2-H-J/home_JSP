package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Base64;

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
            response.sendRedirect("login.do");
            return null;
        }

        int userNumber = user.getUserNumber();
        // 파라미터로 전달된 게시글 제목과 설명을 가져옴
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String tag = request.getParameter("tag");

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

        // 게시글 객체 생성 후 설정
        BoardsDTO dto = new BoardsDTO();
        dto.setUserNumber(userNumber);
        dto.setTag(tag);
        dto.setTitle(title);
        dto.setDescription(description);

        // 게시글 및 파일 정보를 DB에 저장
        int count = BoardsService.getInstance().insertBoard(dto, fileList);

        ModelAndView view = new ModelAndView();
        view.setPath("./boardDetail.do?postNumber=" + dto.getPostNumber());
        view.setRedirect(true);

        return view;
    }
}
