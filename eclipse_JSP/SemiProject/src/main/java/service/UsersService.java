package service;

import java.util.List;

import config.DBManager;
import dto.UsersDTO;
import mapper.UsersMapper;

/**
 * UsersService 클래스는 사용자 관련 비즈니스 로직을 처리하는 서비스 계층입니다.
 * 이 클래스는 UsersMapper와 상호작용하여 데이터베이스 작업을 수행합니다.
 */
public class UsersService {

    // UsersService의 유일한 인스턴스를 저장하는 변수 (싱글톤 패턴)
    private static UsersService instance = new UsersService();

    // UsersMapper 객체 (MyBatis 매퍼)
    private UsersMapper mapper;

    /**
     * 기본 생성자: DBManager를 통해 UsersMapper를 초기화합니다.
     */
    public UsersService() {
        System.out.println("[UsersService] 생성자 호출 -> UsersMapper 초기화");
        mapper = DBManager.getInstance().getSession().getMapper(UsersMapper.class);
    }

    /**
     * UsersService의 싱글톤 인스턴스를 반환합니다.
     * 
     * @return UsersService 인스턴스
     */
    public static UsersService getInstance() {
        if (instance == null) {
            System.out.println("[UsersService] 새로운 UsersService 인스턴스 생성");
            instance = new UsersService();
        }
        return instance;
    }

    /**
     * 모든 사용자 정보를 조회합니다.
     * 
     * @return 사용자 목록 (List 형태로 반환)
     */
    public List<UsersDTO> selectAllUsers() {
        System.out.println("[UsersService] selectAllUsers() 호출 -> 모든 사용자 조회 시작");
        List<UsersDTO> users = mapper.selectAllUsers();
        System.out.println("[UsersService] selectAllUsers() 완료 -> 사용자 수: " + users.size());
        return users;
    }

    /**
     * 새로운 사용자를 데이터베이스에 삽입합니다.
     * 
     * @param dto 사용자 정보를 담은 DTO 객체
     * @return 삽입된 행의 수
     */
    public int insertMember(UsersDTO dto) {
        System.out.println("[UsersService] insertMember() 호출 -> 사용자 삽입 시도: " + dto);
        int rowsInserted = mapper.insertMember(dto);
        System.out.println("[UsersService] insertMember() 완료 -> 삽입된 행 수: " + rowsInserted);
        return rowsInserted;
    }

    /**
     * 주어진 loginId가 데이터베이스에 존재하는지 확인합니다.
     * 
     * @param loginId 확인할 사용자 아이디
     * @return 존재 여부 (true/false)
     */
    public boolean isLoginIdExists(String loginId) {
        System.out.println("[UsersService] isLoginIdExists() 호출 -> loginId: " + loginId);
        boolean exists = mapper.selectLoginIdCount(loginId) > 0;
        System.out.println("[UsersService] isLoginIdExists() 완료 -> 존재 여부: " + exists);
        return exists;
    }

    /**
     * 사용자 로그인 처리를 수행합니다.
     * 
     * @param loginId  로그인 아이디
     * @param password 비밀번호
     * @return 인증된 사용자 정보 또는 null
     */
    public UsersDTO login(String loginId, String password) {
        System.out.println("[UsersService] login() 호출 -> loginId: " + loginId + ", 비밀번호 인증 시작");
        UsersDTO user = mapper.selectUserByLoginIdAndPassword(loginId, password);
        if (user != null) {
            System.out.println("[UsersService] login() 성공 -> 사용자: " + user.getNickName());
        } else {
            System.out.println("[UsersService] login() 실패 -> 아이디 또는 비밀번호 불일치");
        }
        return user;
    }

    /**
     * 주어진 loginId로 사용자 정보를 조회합니다.
     * 
     * @param loginId 조회할 사용자 아이디
     * @return 조회된 사용자 정보 또는 null
     */
    public UsersDTO getUserByLoginId(String loginId) {
        System.out.println("[UsersService] getUserByLoginId() 호출 -> loginId: " + loginId + " 사용자 정보 조회 시작");
        UsersDTO user = mapper.selectUserByLoginId(loginId);
        if (user != null) {
            System.out.println("[UsersService] getUserByLoginId() 완료 -> 사용자 닉네임: " + user.getNickName());
        } else {
            System.out.println("[UsersService] getUserByLoginId() 실패 -> 사용자 정보 없음");
        }
        return user;
    }

    public int updateUser(UsersDTO dto) {
        System.out.println("[UsersService] updateUser() 호출 -> 사용자 정보 수정 시도");
        int result = mapper.updateUser(dto);
        if (dto.getPassword() != null) {
            System.out.println("[UsersService] 비밀번호 수정 -> PWUPDATETIME 업데이트");
            mapper.updatePassword(dto.getUserNumber(), dto.getPassword());
        }
        System.out.println("[UsersService] updateUser() 완료 -> 수정된 행 수: " + result);
        return result;
    }
    
    public boolean isNickNameExists(String nickName) {
        return mapper.countByNickName(nickName) > 0; // 0보다 크면 중복된 닉네임
    }



}
