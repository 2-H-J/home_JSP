package controller;

/**
 * HandlerMapping 클래스는 클라이언트 요청(command)에 따라
 * 적절한 Controller 객체를 생성하고 반환하는 역할을 담당합니다.
 * 싱글톤(Singleton) 패턴을 적용하여 애플리케이션 전역에서 하나의 인스턴스를 공유합니다.
 */
public class HandlerMapping {
    
    // HandlerMapping 클래스의 단일 인스턴스를 저장하는 정적 필드
    private static HandlerMapping instance = new HandlerMapping();

    /**
     * private 생성자: 외부에서 인스턴스를 생성하지 못하도록 제한합니다.
     */
    private HandlerMapping() { }

    /**
     * HandlerMapping 인스턴스를 반환하는 메서드.
     * 싱글톤 패턴을 유지하기 위해 static으로 구현되었습니다.
     * 
     * @return HandlerMapping의 단일 인스턴스
     */
    public static HandlerMapping getInstance() {
        // 이미 생성된 인스턴스가 없으면 새로 생성
        if (instance == null) {
            instance = new HandlerMapping();
        }
        return instance;
    }

    /**
     * 클라이언트 요청(command)에 따라 적절한 Controller 객체를 생성하여 반환합니다.
     * 
     * @param command 클라이언트 요청을 나타내는 문자열
     * @return 요청에 맞는 Controller 객체
     */
    public Controller createController(String command) {
        // Controller 객체를 저장할 변수 초기화
        Controller controller = null;

        // 요청(command)에 따라 적절한 컨트롤러를 생성
        switch (command) {
            case "loginView":
                controller = new LoginViewController(); // 로그인 화면 요청
                break;
            case "login":
                controller = new LoginController(); // 로그인 요청
                break;
            case "boardMain":
                controller = new BoardMainController(); // 게시판 메인 화면 요청
                break;
            case "boardWriteView":
                controller = new BoardWriteViewController(); // 게시글 작성 화면 요청
                break;
            case "boardWrite":
                controller = new BoardWriteController(); // 게시글 작성 요청
                break;
            case "boardView":
                controller = new BoardViewController(); // 게시글 상세 보기 요청
                break;
            case "commentWrite":
                controller = new BoardCommentInsertController(); // 댓글 작성 요청
                break;
            case "boardDelete":
                controller = new BoardDeleteController(); // 게시글 삭제 요청
                break;
            case "boardCommentDelete":
                controller = new BoardCommentDeleteController(); // 댓글 삭제 요청
                break;
            case "boardUpdateView":
                controller = new BoardUpdateViewController(); // 게시글 수정 화면 요청
                break;
            case "boardUpdate":
                controller = new BoardUpdateController(); // 게시글 수정 요청
                break;
        }

        // 요청에 맞는 Controller 객체를 반환
        return controller;
    }
}
