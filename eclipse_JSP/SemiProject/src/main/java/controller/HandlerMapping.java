package controller;

public class HandlerMapping {
    // 싱글톤 패턴으로 HandlerMapping 인스턴스 생성
    private static HandlerMapping instance = new HandlerMapping();

    // 기본 생성자
    public HandlerMapping() {
        System.out.println("[HandlerMapping] 생성자 호출 -> HandlerMapping 인스턴스 생성됨");
    }

    // HandlerMapping 인스턴스 반환 (싱글톤 유지)
    public static HandlerMapping getInstance() {
        if (instance == null) {
            instance = new HandlerMapping();
            System.out.println("[HandlerMapping] 새로운 인스턴스 생성");
        }
        System.out.println("[HandlerMapping] 기존 인스턴스 반환");
        return instance;
    }

    // 전달받은 command 값에 따라 적절한 Controller 객체를 생성
    public Controller createController(String command) {
        Controller controller = null;
        System.out.println("[HandlerMapping] 전달된 명령어: " + command);

        // 명령어(command)에 따라 적합한 Controller 매핑
        switch (command) {
            case "allUser":
                controller = new SelectAllUsers();
                System.out.println("[HandlerMapping] SelectAllUsers 컨트롤러 생성");
                break;
            case "allBoard":
                controller = new SelectAllBoards();
                System.out.println("[HandlerMapping] SelectAllBoards 컨트롤러 생성");
                break;
            case "boardDetail":
                controller = new SelectBoardByPostNumber();
                System.out.println("[HandlerMapping] SelectBoardByPostNumber 컨트롤러 생성");
                break;
            case "deleteBoard":
                controller = new DeleteBoardByPostNumber();
                System.out.println("[HandlerMapping] DeleteBoardByPostNumber 컨트롤러 생성");
                break;
            case "insertBoard":
                controller = new InsertBoard();
                System.out.println("[HandlerMapping] InsertBoard 컨트롤러 생성");
                break;
            case "updateBoard":
                controller = new UpdateBoardPage();
                System.out.println("[HandlerMapping] UpdateBoardPage 컨트롤러 생성");
                break;
            case "syncBoard":
                controller = new UpdateBoard();
                System.out.println("[HandlerMapping] UpdateBoard 컨트롤러 생성");
                break;
            case "region":
                controller = new regionintro();
                System.out.println("[HandlerMapping] regionintro 컨트롤러 생성");
                break;
            case "regionDetail":
                controller = new regionDetail();
                System.out.println("[HandlerMapping] regionDetail 컨트롤러 생성");
                break;
            case "insertMember":
                controller = new InsertMember();
                System.out.println("[HandlerMapping] InsertMember 컨트롤러 생성 -> 회원 추가");
                break;
            case "checkLoginId":
                controller = new CheckLoginIdController();
                System.out.println("[HandlerMapping] CheckLoginIdController 컨트롤러 생성 -> 아이디 중복 체크");
                break;
            case "login":
                controller = new LoginController();
                System.out.println("[HandlerMapping] LoginController 컨트롤러 생성 -> 로그인 처리");
                break;
            case "logout":
                controller = new LogoutController();
                System.out.println("[HandlerMapping] LogoutController 컨트롤러 생성 -> 로그아웃 처리");
                break;
            case "updateUser":
            	controller = new UpdateUserController();
            	System.out.println("[HandlerMapping] UpdateUserController 컨트롤러 생성 -> 내정보수정 처리");
            	break;
            default:
                System.out.println("[HandlerMapping] 알 수 없는 명령어: " + command);
                break;
        }

        // 생성된 Controller 반환
        if (controller != null) {
            System.out.println("[HandlerMapping] 생성된 Controller: " + controller.getClass().getSimpleName());
        } else {
            System.out.println("[HandlerMapping] Controller 생성 실패");
        }
        return controller;
    }
}
