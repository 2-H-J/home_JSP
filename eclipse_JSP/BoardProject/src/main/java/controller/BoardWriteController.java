package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dto.BoardDTO;
import dto.BoardFileDTO;
import dto.BoardMemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import service.BoardService;
import view.ModelAndView;

public class BoardWriteController implements Controller {

    // 이 클래스는 게시글 작성 요청을 처리하는 컨트롤러입니다.
    // - 클라이언트가 작성한 게시글 제목, 내용, 업로드된 파일 정보를 처리하고
    //   데이터를 데이터베이스에 저장한 후, 게시판 메인 페이지로 리다이렉트합니다.

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // [INFO] 게시글 작성 요청 처리 시작
        System.out.println("[INFO] 게시글 작성 요청 처리 시작");

        // 1. 요청 파라미터에서 게시글 제목(title)과 내용(content) 추출
        // - 클라이언트가 전송한 데이터를 HttpServletRequest 객체에서 가져옵니다.
        // - getParameter() 메서드는 HTML 폼에서 전송된 데이터를 키-값 형태로 추출합니다.
        String title = request.getParameter("title"); // 게시글 제목
        String content = request.getParameter("content"); // 게시글 내용
        System.out.println("[DEBUG] 게시글 제목(title): " + title);
        System.out.println("[DEBUG] 게시글 내용(content): " + content);

        // 2. 세션에서 작성자 ID 추출
        // - 현재 로그인된 사용자의 ID를 세션(HttpSession)에서 가져옵니다.
        // - getSession() 메서드는 클라이언트와 서버 간 상태를 유지하는 객체를 반환합니다.
        // - BoardMemberDTO는 로그인된 사용자의 정보를 저장하는 데이터 전송 객체(DTO)입니다.
        String id = ((BoardMemberDTO) request.getSession().getAttribute("user")).getId();
        System.out.println("[DEBUG] 작성자 ID(id): " + id);

        // 3. 파일 업로드를 위한 디렉터리 생성
        // - File 클래스는 파일이나 디렉터리를 나타내는 Java 표준 클래스입니다.
        //   - 생성자를 사용하여 파일 경로나 디렉터리를 설정합니다.
        //   - exists(): 경로가 존재하는지 확인
        //   - mkdirs(): 경로가 없으면 디렉터리를 생성
        File root = new File("c:\\fileupload"); // 업로드된 파일을 저장할 경로 설정
        if (!root.exists()) { // 경로가 존재하지 않을 경우
            System.out.println("[INFO] 파일 업로드 경로 생성");
            root.mkdirs(); // 디렉터리를 생성
        }

        // 4. 업로드된 파일 처리
        // - request.getParts() 메서드는 클라이언트가 전송한 파일 데이터를 포함하는 Part 객체를 반환합니다.
        // - Part는 파일 데이터와 메타정보(파일 이름, 크기 등)를 포함하는 객체입니다.
        // - Iterator는 컬렉션 객체(List, Set 등)를 순차적으로 접근하기 위해 사용하는 인터페이스입니다.
        Iterator<Part> it = request.getParts().iterator(); // 업로드된 파일 데이터 추출
        List<BoardFileDTO> fileList = new ArrayList<>(); // 파일 정보를 저장할 리스트 생성
        while (it.hasNext()) { // 업로드된 파일 데이터를 순차적으로 처리
            Part part = it.next(); // 현재 파일 데이터 가져오기
            if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
                // 파일 저장
                String filePath = root.getAbsolutePath() + "\\" + part.getSubmittedFileName();
                part.write(filePath); // 파일 데이터를 지정된 경로에 저장
                System.out.println("[DEBUG] 저장된 파일 경로(filePath): " + filePath);

                // BoardFileDTO 객체 생성 및 파일 정보 추가
                // - BoardFileDTO는 파일 정보를 저장하는 데이터 전송 객체입니다.
                BoardFileDTO fdto = new BoardFileDTO();
                fdto.setFpath(filePath); // 파일 경로 설정
                fileList.add(fdto); // 파일 정보 리스트에 추가
            }
        }
        System.out.println("[DEBUG] 업로드된 파일 목록(fileList): " + fileList);

        // 5. 게시글 데이터 설정 및 등록
        // - BoardDTO는 게시글 정보를 저장하는 데이터 전송 객체입니다.
        // - BoardService의 insertBoard 메서드는 게시글과 첨부 파일 정보를 데이터베이스에 저장합니다.
        BoardDTO dto = new BoardDTO();
        dto.setId(id); // 작성자 ID 설정
        dto.setTitle(title); // 게시글 제목 설정
        dto.setContent(content); // 게시글 내용 설정
        int count = BoardService.getInstance().insertBoard(dto, fileList); // 게시글 등록
        System.out.println("[INFO] 게시글 등록 결과(count): " + count);

        // 6. 게시판 메인 페이지로 이동
        // - ModelAndView는 뷰(View)와 데이터(Model)를 포함하는 객체입니다.
        // - setPath()는 이동할 페이지 경로를 설정합니다.
        // - setRedirect(true)는 리다이렉트 방식으로 이동하도록 설정합니다.
        ModelAndView view = new ModelAndView();
        view.setPath("./boardMain.do"); // 게시판 메인 페이지로 이동 경로 설정
        view.setRedirect(true); // 리다이렉트 플래그 설정
        System.out.println("[INFO] 게시판 메인 페이지로 이동 설정 완료");

        // 7. 처리 결과 반환
        // - ModelAndView 객체를 반환하여 요청 처리를 종료합니다.
        System.out.println("[INFO] 게시글 작성 요청 처리 완료");
        return view;
    }
}





