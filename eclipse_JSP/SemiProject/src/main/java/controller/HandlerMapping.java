package controller;

import java.io.File;

public class HandlerMapping {
    // 싱글톤 패턴: HandlerMapping 클래스의 유일한 인스턴스를 생성
    private static HandlerMapping instance = new HandlerMapping();

    // 기본 생성자
    public HandlerMapping() {
        String fileName = new File(HandlerMapping.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName();
        System.out.println("[HandlerMapping] 파일명: " + fileName);
        System.out.println("[HandlerMapping] HandlerMapping 인스턴스 생성");
    }

    // HandlerMapping 인스턴스 반환 메서드
    public static HandlerMapping getInstance() {
        System.out.println("[HandlerMapping] getInstance() 호출");
        if (instance == null) {
            System.out.println("[HandlerMapping] 새로운 HandlerMapping 인스턴스 생성");
            instance = new HandlerMapping();
        }
        return instance;
    }

    // 입력받은 command에 따라 적절한 Controller 생성
    public Controller createController(String command) {
        Controller controller = null;
        System.out.println("[HandlerMapping] createController() 호출 - command: " + command);

        switch (command) {
            case "allUser":
                System.out.println("[HandlerMapping] SelectAllUsers 컨트롤러 생성");
                controller = new SelectAllUsers();
                break;
            case "allBoard":
                System.out.println("[HandlerMapping] SelectAllBoards 컨트롤러 생성");
                controller = new SelectAllBoards();
                break;
            case "boardDetail":
                System.out.println("[HandlerMapping] SelectBoardByPostNumber 컨트롤러 생성");
                controller = new SelectBoardByPostNumber();
                break;
            case "deleteBoard":
                System.out.println("[HandlerMapping] DeleteBoardByPostNumber 컨트롤러 생성");
                controller = new DeleteBoardByPostNumber();
                break;
            case "insertBoard":
                System.out.println("[HandlerMapping] InsertBoard 컨트롤러 생성");
                controller = new InsertBoard();
                break;
            case "updateBoard":
                System.out.println("[HandlerMapping] UpdateBoardPage 컨트롤러 생성");
                controller = new UpdateBoardPage();
                break;
            case "syncBoard":
                System.out.println("[HandlerMapping] UpdateBoard 컨트롤러 생성");
                controller = new UpdateBoard();
                break;
            case "region":
                System.out.println("[HandlerMapping] regionintro 컨트롤러 생성");
                controller = new regionintro();
                break;
            case "regionDetail":
                System.out.println("[HandlerMapping] regionDetail 컨트롤러 생성");
                controller = new regionDetail();
                break;
            case "insertMember":
                System.out.println("[HandlerMapping] InsertMember 컨트롤러 생성 (회원 추가 작업)");
                controller = new InsertMember();
                break;
            case "checkLoginId":
                System.out.println("[HandlerMapping] CheckLoginIdController 컨트롤러 생성 (아이디 중복 체크)");
                controller = new CheckLoginIdController();
                break;
            case "login":
                System.out.println("[HandlerMapping] LoginController 생성 -> LoginController.java");
                controller = new LoginController();
                break;
            case "logout":
                System.out.println("[HandlerMapping] LogoutController 생성 -> LogoutController.java");
                controller = new LogoutController();
                break;
            case "updateUser":
                controller = new UpdateUserController();
                break;
            case "checkNickname":
                controller = new CheckNickNameController();
                break;
            default:
                System.out.println("[HandlerMapping] 유효하지 않은 command: " + command);
                break;
        }

        if (controller != null) {
            System.out.println("[HandlerMapping] 생성된 컨트롤러: " + controller.getClass().getSimpleName());
        } else {
            System.out.println("[HandlerMapping] 생성된 컨트롤러가 없음 (command 처리 실패)");
        }

        return controller;
    }
}
