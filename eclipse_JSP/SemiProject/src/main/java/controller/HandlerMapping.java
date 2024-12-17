package controller;

public class HandlerMapping {
	private static HandlerMapping instance = new HandlerMapping();

	public HandlerMapping() {
		System.out.println("[HandlerMapping] 생성자 호출 -> HandlerMapping 객체 생성");
	}

	public static HandlerMapping getInstance() {
		if (instance == null) {
			System.out.println("[HandlerMapping] 인스턴스가 null -> 새로운 HandlerMapping 생성");
			instance = new HandlerMapping();
		} else {
			System.out.println("[HandlerMapping] 기존 HandlerMapping 인스턴스 반환");
		}
		return instance;
	}

	public Controller createController(String command) {
		System.out.println("[HandlerMapping] createController() 호출 -> 명령어: " + command);
		Controller controller = null;

		switch (command) {
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
//-------------------------------------------------------------------------
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
