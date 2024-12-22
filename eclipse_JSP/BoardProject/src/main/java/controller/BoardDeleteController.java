package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import dto.BoardFileDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardService;
import view.ModelAndView;

public class BoardDeleteController implements Controller {

    // 이 메서드는 특정 게시글 삭제 요청을 처리합니다.
    // - 게시글 번호를 기반으로 데이터베이스에서 게시글을 삭제하고,
    //   해당 게시글과 연관된 파일들을 물리적으로 삭제합니다.
    // - 삭제 완료 후 메인 게시판 페이지로 리다이렉트합니다.
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // [INFO] 게시글 삭제 요청 처리 시작
        System.out.println("[INFO] 게시글 삭제 요청 처리 시작");

        // 1. 요청 파라미터에서 게시글 번호(bno) 추출
        // - 클라이언트가 삭제를 요청한 게시글의 고유 번호를 가져옵니다.
        System.out.println("[INFO] 요청 파라미터 추출 중");
        int bno = Integer.parseInt(request.getParameter("bno")); // 게시글 번호 추출
        System.out.println("[DEBUG] 추출된 게시글 번호(bno): " + bno);

        // 2. 해당 게시글과 관련된 파일 경로 리스트 가져오기
        // - BoardService의 getBoardFileList 메서드를 호출하여 게시글에 첨부된 파일들의 경로를 가져옵니다.
        System.out.println("[INFO] 게시글의 파일 경로 리스트 가져오기");
        List<BoardFileDTO> list = BoardService.getInstance().getBoardFileList(bno);
        System.out.println("[DEBUG] 가져온 파일 경로 리스트: " + list);

        // 3. 파일 경로를 기반으로 물리적 파일 삭제
        // - java.io.File 클래스를 사용하여 해당 경로에 있는 파일을 삭제합니다.
        System.out.println("[INFO] 게시글 첨부 파일 삭제 시작");
        list.forEach(item -> {
            File file = new File(item.getFpath()); // 파일 객체 생성
            if (file.exists()) { // 파일이 존재하는 경우에만 삭제
                boolean deleted = file.delete(); // 물리적 파일 삭제
                System.out.println("[DEBUG] 파일 삭제: " + item.getFpath() + " 결과: " + deleted);
            } else {
                System.out.println("[WARN] 삭제하려는 파일이 존재하지 않음: " + item.getFpath());
            }
        });
        System.out.println("[INFO] 게시글 첨부 파일 삭제 완료");

        // 4. 데이터베이스에서 게시글 삭제
        // - BoardService의 deleteBoard 메서드를 호출하여 데이터베이스에서 게시글을 삭제합니다.
        System.out.println("[INFO] 데이터베이스에서 게시글 삭제 시작");
        BoardService.getInstance().deleteBoard(bno);
        System.out.println("[INFO] 데이터베이스에서 게시글 삭제 완료");

        // 5. 리다이렉트 경로 설정
        // - 삭제 완료 후, 게시판 메인 페이지로 리다이렉트합니다.
        // - ModelAndView 객체를 사용하여 리다이렉트 경로를 설정합니다.
        System.out.println("[INFO] 리다이렉트 경로 설정 중");
        ModelAndView view = new ModelAndView(); // ModelAndView 객체 생성
        view.setPath("./boardMain.do"); // 이동할 URL 설정
        System.out.println("[DEBUG] 설정된 리다이렉트 경로: ./boardMain.do");
        view.setRedirect(true); // 리다이렉트 플래그 설정
        System.out.println("[INFO] 리다이렉트 경로 설정 완료");

        // 6. 처리 결과 반환
        // - 최종적으로 ModelAndView 객체를 반환하여 요청 처리를 종료합니다.
        System.out.println("[INFO] 게시글 삭제 요청 처리 완료");
        return view;
    }
}
