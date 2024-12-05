package controller;

public class HandlerMapping {
	private static HandlerMapping instance = new HandlerMapping();

	public HandlerMapping() {
	}

	public static HandlerMapping getInstance() {
		System.out.println("controller - HandlerMapping - 2");
		if (instance == null)
			instance = new HandlerMapping();
		return instance;
	}

	public Controller createController(String command) {
		Controller controller = null;
		System.out.println("controller - 3");
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

		}
		return controller;
	}

}
