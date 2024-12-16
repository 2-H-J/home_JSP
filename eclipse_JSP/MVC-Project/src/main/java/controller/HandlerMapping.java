package controller;

// 핸들러 매핑 클래스
// 특정 요청(command)에 따라 적절한 컨트롤러 객체를 생성하고 반환하는 역할을 함
public class HandlerMapping {
    
    // 싱글톤 인스턴스 생성
    private static HandlerMapping instance = new HandlerMapping();
    
    // private 생성자 - 외부에서 인스턴스를 생성하지 못하도록 막음
    // 이로써 외부에서 HandlerMapping 객체를 직접 생성할 수 없게 함
    private HandlerMapping() { }

    // 싱글톤 인스턴스를 반환하는 메소드
    // 외부에서 HandlerMapping 객체를 얻고자 할 때는 이 메소드를 통해 접근
    public static HandlerMapping getInstance() {
        // 기존 인스턴스가 null이면 새로 생성하고, 이미 존재하면 기존 인스턴스를 반환
        if (instance == null) {
            instance = new HandlerMapping();
        }
        return instance;
    }

    // 특정 command에 따라 적절한 Controller 객체를 생성하여 반환하는 메소드
    // command는 요청의 종류를 나타내며, 각 요청에 대해 알맞은 Controller를 반환함
    public Controller createController(String command) {
        // Controller 객체를 초기화
        Controller controller = null;
        
        // command에 따라 알맞은 컨트롤러 객체를 생성
        switch (command) {
            case "allMember":
                // 전체 회원 목록 조회를 처리하는 SelectAllMemberController 반환
                controller = new SelectAllMemberController();
                break;
            case "main":
                // 메인 페이지로 이동하는 MainController 반환
                controller = new MainController();
                break;
            case "allMember2":
                // 전체 회원 목록을 JSON 형태로 반환하는 SelectAllMember2Controller 반환
                controller = new SelectAllMember2Controller();
                break;
            case "searchNameMember":
                // 특정 이름을 가진 회원을 검색하는 SelectMemberNameController 반환
                controller = new SelectMemberNameController();
                break;
            // 여기서 default를 정의하지 않으면 command가 일치하지 않을 때 controller는 null로 남음
        }
        
        // 생성된 Controller 객체를 반환
        return controller;
    }
}
