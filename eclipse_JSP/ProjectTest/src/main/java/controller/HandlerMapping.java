package controller;

public class HandlerMapping {
    // HandlerMapping의 단일 인스턴스를 유지하기 위한 static 필드
    private static HandlerMapping instance = new HandlerMapping();

    // 기본 생성자, 외부에서 직접 호출하지 못하게 설계되어 있음
    public HandlerMapping() {
        System.out.println("[HandlerMapping] 생성자 호출됨 -> HandlerMapping 인스턴스 생성 완료");
    }

    // 싱글턴 패턴으로 인스턴스를 반환하는 메서드
    public static HandlerMapping getInstance() {
        System.out.println("[HandlerMapping] getInstance() 호출됨 -> HandlerMapping 인스턴스 반환");
        // 인스턴스가 null이면 새로 생성
        if (instance == null) {
            System.out.println("[HandlerMapping] 인스턴스가 null이므로 새로운 인스턴스를 생성합니다");
            instance = new HandlerMapping();
        }
        // 기존 인스턴스를 반환
        return instance;
    }

    // 클라이언트의 요청에 따라 적절한 컨트롤러 객체를 생성하는 메서드
    public Controller createController(String command) {
        Controller controller = null;
        System.out.println("[HandlerMapping] createController() 호출됨 - command: " + command);

        // command 값을 기준으로 적절한 컨트롤러 객체를 생성
        switch (command) {
            case "allUser":
                System.out.println("[HandlerMapping] SelectAllUsers 컨트롤러 생성 -> SelectAllUsers.java");
                controller = new SelectAllUsers();
                break;
            case "allBoard":
                System.out.println("[HandlerMapping] SelectAllBoards 컨트롤러 생성 -> SelectAllBoards.java");
                controller = new SelectAllBoards();
                break;
            case "boardDetail":
                System.out.println("[HandlerMapping] SelectBoardByPostNumber 컨트롤러 생성 -> SelectBoardByPostNumber.java");
                controller = new SelectBoardByPostNumber();
                break;
            case "deleteBoard":
                System.out.println("[HandlerMapping] DeleteBoardByPostNumber 컨트롤러 생성 -> DeleteBoardByPostNumber.java");
                controller = new DeleteBoardByPostNumber();
                break;
            case "insertBoard":
                System.out.println("[HandlerMapping] InsertBoard 컨트롤러 생성 -> InsertBoard.java");
                controller = new InsertBoard();
                break;
            case "updateBoard":
                System.out.println("[HandlerMapping] UpdateBoardPage 컨트롤러 생성 -> UpdateBoardPage.java");
                controller = new UpdateBoardPage();
                break;
            case "syncBoard":
                System.out.println("[HandlerMapping] UpdateBoard 컨트롤러 생성 -> UpdateBoard.java");
                controller = new UpdateBoard();
                break;
            case "login":
                System.out.println("[HandlerMapping] LoginController 생성 -> LoginController.java");
                controller = new LoginController();
                break;
            case "logout":
                System.out.println("[HandlerMapping] LogoutController 생성 -> LogoutController.java");
                controller = new LogoutController();
                break;
            default:
                System.out.println("[HandlerMapping] 알 수 없는 command: " + command + " -> 요청에 해당하는 컨트롤러가 없습니다");
                break;
        }
        // 생성된 컨트롤러 객체 반환
        return controller;
    }
}

// 참고로 이 코드는 요청을 받아서 해당 요청을 처리하기 위한 적절한 컨트롤러 객체를 반환하는 역할을 합니다.
// 싱글턴 패턴을 사용하여 클래스 인스턴스가 하나만 존재하도록 보장하며, 각 요청(command)에 따라 적절한 컨트롤러를 생성하고 반환합니다.
// 이를 통해 MVC 패턴에서 다양한 사용자 요청에 대한 분기를 효과적으로 처리할 수 있습니다.
// System.out.println()을 사용하여 각 단계에서 로그를 남겨서 호출 흐름을 명확히 추적할 수 있습니다.
