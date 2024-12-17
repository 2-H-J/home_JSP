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
			controller = new BoardMainView();
			break;
		case "boardDetail":
			controller = new BoardViewController();
			break;
		case "deleteBoard":
			controller = new BoardDeleteController();
			break;
		case "boardWriteView":
			controller = new BoardInsertViewController();
			break;
		case "insertBoard":
			controller = new BoardInsertController();
			break;
		case "updateBoard":
			controller = new BoardUpdateViewController();
			break;
		case "syncBoard":
			controller = new BoardUpdateController();
			break;
		case "region":
			controller = new RegionIntro();
			break;
		case "regionDetail":
			controller = new RegionDetail();
			break;
		case "deleteRegion": // 새로 추가된 컨트롤러
			System.out.println("deleteRegion controller");
			controller = new RegionDeleteController();
			break;
		case "RegionWriteView":
			controller = new RegionInsertViewController(); // 회원 추가
			break;
		case "insertRegion":
			controller = new RegionInsertController(); // 회원 추가
			break;
		case "updateRegion":
			controller = new RegionUpdateViewController();
			break;
		case "syncRegion":
			controller = new RegionUpdateController();
			break;
		case "commentWrite":
			controller = new CommentWriteController();
			break;
		case "commentDelete":
			controller = new CommentDeleteController();
			break;
		case "fileDown":
			controller = new BoardFileDownController();
			break;
		case "boardsCategory": // 새로 추가된 컨트롤러
			controller = new BoardsCategoryController();
			System.out.println("[HandlerMapping] Command: " + command);
			break;
		case "boardsList": // 새로 추가된 컨트롤러
			controller = new BoardSelectController();
			break;
		case "boardLike": // 좋아요
			controller = new BoardLikeController();
			break;
		case "boardCommentLike": // 댓글 좋아요
			controller = new BoardCommentLikeController();
			break;
		case "reportWrite": // 신고
			controller = new ReportWriteController();
			break;
		case "adminUser":
			controller = new AdminUserController();
			break;
		case "reportUser": // 신고하기 기능
			controller = new ReportUserController();
			break;
		case "adminReports": // 관리자 신고 조회
			controller = new AdminReportListController();
			break;
		case "index":
			controller = new IndexController();
			System.out.println("[HandlerMapping] IndexController 생성됨");
			break;
		// 회원가입 --------------------------------------------------------
		case "checkLoginId":
			System.out.println("[HandlerMapping] CheckLoginIdController 생성 시작");
			controller = new CheckLoginIdController(); // 아이디 중복 체크
			System.out.println("[HandlerMapping] CheckLoginIdController 생성 완료");
			break;
		case "checkNickName": // 기존 "CheckNickName"에서 소문자로 수정
			System.out.println("[HandlerMapping] CheckNickNameController 생성 시작");
			controller = new CheckNickNameController();
			System.out.println("[HandlerMapping] CheckNickNameController 생성 완료");
			break;
		case "checkEmail":
			System.out.println("[HandlerMapping] CheckEmailController 생성 시작");
			controller = new CheckEmailController();
			System.out.println("[HandlerMapping] CheckEmailController 생성 완료");
			break;
		case "insertMember":
			System.out.println("[HandlerMapping] InsertMemberController 생성 시작");
			controller = new InsertMember(); // 회원 추가
			System.out.println("[HandlerMapping] InsertMemberController 생성 완료");
			break;

		// 로그인 ---------------------------------------------------------
		case "loginView":
			System.out.println("[HandlerMapping] LoginViewController 생성 시작");
			controller = new LoginViewController();
			System.out.println("[HandlerMapping] LoginViewController 생성 완료");
			break;
		case "login":
			System.out.println("[HandlerMapping] LoginController 생성 시작");
			controller = new LoginController();
			System.out.println("[HandlerMapping] LoginController 생성 완료");
			break;
		case "logout":
			System.out.println("[HandlerMapping] LogoutController 생성 시작");
			controller = new LogoutController();
			System.out.println("[HandlerMapping] LogoutController 생성 완료");
			break;

		// 마이페이지 -----------------------------------------------------
		case "mypageView":
			System.out.println("[HandlerMapping] MyPageViewController 생성 시작");
			controller = new MyPageViewController();
			System.out.println("[HandlerMapping] MyPageViewController 생성 완료");
			break;
		case "updateUserView":
			System.out.println("[HandlerMapping] UpdateUserViewController 생성 시작");
			controller = new UpdateUserViewController();
			System.out.println("[HandlerMapping] UpdateUserViewController 생성 완료");
			break;
		case "updateUser":
			System.out.println("[HandlerMapping] UpdateUserController 생성 시작");
			controller = new UpdateUserController(); // 사용자 정보 수정
			System.out.println("[HandlerMapping] UpdateUserController 생성 완료");
			break;
		// 아이디/비밀번호 찾기--------------------------------------------
		case "findLoginId":
			controller = new FindLoginIdController();
			break;
		case "recoverPassword":
			controller = new PasswordRecoveryController();
			break;
		case "updatePassword":
			controller = new PasswordUpdateController();
			break;
		// -------------------------------------------------------------------------
		default:
			System.out.println("[HandlerMapping] 알 수 없는 명령어 -> " + command);
			break;
		}

		if (controller != null) {
			System.out.println("[HandlerMapping] Controller 반환 -> " + controller.getClass().getSimpleName());
		} else {
			System.out.println("[HandlerMapping] Controller 생성 실패 -> 명령어를 확인하세요: " + command);
		}
		return controller;
	}

}
