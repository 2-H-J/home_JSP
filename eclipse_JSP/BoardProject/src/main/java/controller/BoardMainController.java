package controller;

import java.io.IOException;
import java.util.List; // java.util.List: Java에서 컬렉션 객체를 다룰 때 사용되는 인터페이스입니다.
// - List는 순서가 있는 데이터를 저장하며, 중복 요소를 허용합니다.
// - ArrayList, LinkedList 등의 클래스가 List 인터페이스를 구현합니다.

import dto.BoardDTO; // 게시글 정보를 담는 데이터 전송 객체 (DTO)
import jakarta.servlet.ServletException; // 서블릿 관련 예외를 처리하는 클래스
import jakarta.servlet.http.HttpServletRequest; // 클라이언트 요청 정보를 담는 객체
import jakarta.servlet.http.HttpServletResponse; // 클라이언트에 응답 데이터를 전송하는 객체
import service.BoardService; // 비즈니스 로직 처리를 위한 서비스 계층
import view.ModelAndView; // 뷰(View)와 모델(Model)을 포함하는 객체
import vo.PaggingVO; // 페이징 정보를 관리하기 위한 값 객체 (VO)

// BoardMainController 클래스는 게시판 메인 페이지 요청을 처리하는 컨트롤러입니다.
// 이 클래스는 사용자가 요청한 페이지 번호와 게시글 개수에 따라 게시글 목록과 페이징 정보를 반환합니다.
public class BoardMainController implements Controller {

    // execute 메서드는 클라이언트 요청을 처리하고 결과를 반환합니다.
    // - HttpServletRequest: 클라이언트 요청 정보를 포함하는 객체
    //   (예: 요청 파라미터, 세션 정보 등)
    // - HttpServletResponse: 서버가 클라이언트로 보낼 응답 정보를 포함하는 객체
    //   (예: 응답 데이터, 상태 코드 등)
    // - ModelAndView: 요청 처리 결과와 이동할 뷰(View)를 설정하는 객체
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // [INFO] 게시판 메인 페이지 요청 처리 시작
        System.out.println("[INFO] 게시판 메인 페이지 요청 처리 시작");

        // 1. 요청 파라미터에서 페이지 번호 추출
        // - 클라이언트가 요청한 페이지 번호를 가져옵니다.
        // - 요청 파라미터 "page"가 없을 경우 기본값 1로 설정합니다.
        String page = request.getParameter("page"); // 요청된 페이지 번호
        int pageNo = (page == null ? 1 : Integer.parseInt(page)); // 페이지 번호가 없으면 기본값 1
        System.out.println("[DEBUG] 페이지 번호(pageNo): " + pageNo);

        // 2. 요청 파라미터에서 페이지당 게시글 개수 추출
        // - 클라이언트가 요청한 페이지당 게시글 개수를 가져옵니다.
        // - 요청 파라미터 "pageContentEa"가 없을 경우 기본값 30으로 설정합니다.
        int pageContentEa = (request.getParameter("pageContentEa") == null
                ? 30
                : Integer.parseInt(request.getParameter("pageContentEa")));
        System.out.println("[DEBUG] 페이지당 게시글 개수(pageContentEa): " + pageContentEa);

        // 3. 전체 게시글 개수 조회
        // - BoardService의 selectBoardTotalCount 메서드를 호출하여 전체 게시글 개수를 가져옵니다.
        int count = BoardService.getInstance().selectBoardTotalCount(); // 전체 게시글 개수
        System.out.println("[DEBUG] 전체 게시글 개수(count): " + count);

        // 4. 요청 페이지에 해당하는 게시글 목록 조회
        // - List<BoardDTO>: BoardDTO 객체들을 저장하는 리스트입니다.
        //   - BoardDTO: 게시글 정보를 저장하는 데이터 전송 객체입니다.
        //   - List는 순서대로 게시글 객체를 저장하며, 컬렉션의 기능(추가, 삭제 등)을 제공합니다.
        List<BoardDTO> list = BoardService.getInstance().getBoardList(pageNo, pageContentEa); // 게시글 목록
        System.out.println("[DEBUG] 게시글 목록(list): " + list);

        // 5. 페이징 정보 생성
        // - PaggingVO: 페이징 정보를 저장하는 값 객체(Value Object)입니다.
        //   - 전체 게시글 수, 현재 페이지 번호, 페이지당 게시글 개수를 포함합니다.
        PaggingVO pagging = new PaggingVO(count, pageNo, pageContentEa); // 페이징 정보 생성
        System.out.println("[DEBUG] 페이징 정보(pagging): " + pagging);

        // 6. ModelAndView 객체 생성 및 데이터 추가
        // - ModelAndView 객체를 생성하여 게시글 목록과 페이징 정보를 추가합니다.
        // - 뷰(View) 경로를 "main.jsp"로 설정하여 클라이언트에게 페이지를 렌더링합니다.
        ModelAndView view = new ModelAndView(); // ModelAndView 객체 생성
        view.addObject("boardList", list); // 게시글 목록 추가
        view.addObject("pagging", pagging); // 페이징 정보 추가
        view.setPath("main.jsp"); // 이동할 뷰(View) 경로 설정
        System.out.println("[DEBUG] 설정된 뷰 경로(path): main.jsp");

        // 7. 처리 결과 반환
        // - ModelAndView 객체를 반환하여 요청 처리를 종료합니다.
        System.out.println("[INFO] 게시판 메인 요청 처리 완료");
        return view;
    }
}
