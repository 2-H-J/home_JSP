package service;

import java.util.List;

import config.DBManager;
import dto.UsersDTO;
import mapper.UsersMapper;

public class UsersService {
    private static UsersService instance = new UsersService();
    private UsersMapper mapper;

    public UsersService() {
        System.out.println("[UsersService] 생성자 호출됨 -> UsersService 인스턴스 생성");
        mapper = DBManager.getInstance().getSession().getMapper(UsersMapper.class);
        System.out.println("[UsersService] UsersMapper 인스턴스 생성 -> DBManager를 통해 MyBatis 매퍼 초기화 완료");
    }

    public static UsersService getInstance() {
        if (instance == null) {
            System.out.println("[UsersService] instance가 null이므로 새로운 인스턴스를 생성");
            instance = new UsersService();
        }
        System.out.println("[UsersService] 기존 인스턴스 반환 -> UsersService 인스턴스 제공");
        return instance;
    }

    public List<UsersDTO> selectAllUsers() {
        System.out.println("[UsersService] selectAllUsers() 호출 -> 모든 사용자 정보 조회 시작");
        return mapper.selectAllUsers();
    }

    public UsersDTO login(String loginId, String password) {
        System.out.println("[UsersService] login() 호출 -> loginId: " + loginId + ", 비밀번호 인증 시작");
        return mapper.selectUserByLoginIdAndPassword(loginId, password);
    }
    
    public UsersDTO getUserByLoginId(String loginId) {
        System.out.println("[UsersService] getUserByLoginId() 호출 -> loginId: " + loginId + " 사용자 정보 조회 시작");
        return mapper.selectUserByLoginId(loginId);
    }
}

// 이 클래스는 UsersService로 사용자와 관련된 데이터베이스 접근 로직을 처리합니다.
// 싱글턴 패턴으로 인스턴스를 관리하며, MyBatis를 사용해 데이터베이스와 상호작용합니다.
// 각 메서드는 사용자 데이터를 조회하거나 인증하는 기능을 제공하며, System.out.println()을 통해 호출 흐름을 추적할 수 있습니다.
