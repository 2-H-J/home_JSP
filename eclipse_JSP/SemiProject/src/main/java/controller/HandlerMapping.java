package controller;

public class HandlerMapping {
	private static HandlerMapping instance = new HandlerMapping();

	public HandlerMapping() {
	}

	public static HandlerMapping getInstance() {
		if (instance == null)
			instance = new HandlerMapping();
		return instance;
	}

	public Controller createController(String command) {
		Controller controller = null;
		switch (command) {
		case "allUser":
			controller = new SelectAllUsers();
			break;
		case "allBoard":
			controller = new SelectAllBoards();
			break;
		case "boardDetail":
			controller = new SelectBoardByPostNumber();
			break;
		case "deleteBoard":
			controller = new DeleteBoardByPostNumber();
			break;
		case "insertBoard":
			controller = new InsertBoard();
			break;
		case "updateBoard":
			controller = new UpdateBoardPage();
			break;
		case "syncBoard":
			controller = new UpdateBoard();
			break;
		case "region":
			controller = new regionintro();
			break;
		case "regionDetail":
			controller = new regionDetail();
			break;
		case "insertMember":
			controller = new InsertMember(); // 회원 추가
			break;
		case "checkLoginId":
			controller = new CheckLoginIdController(); // 아이디 중복 체크
			break;
		case "login":
			System.out.println("[HandlerMapping] LoginController 생성 -> LoginController.java");
			controller = new LoginController();
			break;
		case "logout":
			System.out.println("[HandlerMapping] LogoutController 생성 -> LogoutController.java");
			controller = new LogoutController();
			break;
		}
		return controller;
	}

}
