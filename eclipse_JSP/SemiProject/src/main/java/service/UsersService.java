package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import config.DBManager;
import dto.UsersDTO;
import mapper.UsersMapper;

/**
 * UsersService 클래스는 사용자 관련 비즈니스 로직을 처리하는 서비스 계층입니다.
 * 데이터베이스와 직접 상호작용하지 않고, MyBatis의 UsersMapper를 통해 데이터베이스 작업을 수행합니다.
 * 싱글톤 패턴을 사용하여 애플리케이션 전역에서 하나의 인스턴스를 공유합니다.
 */
public class UsersService {

    // UsersService의 유일한 인스턴스를 저장하는 변수 (싱글톤 패턴)
    private static UsersService instance = new UsersService();

    // UsersMapper 객체 (MyBatis 매퍼 인터페이스)
    private UsersMapper mapper;

    /**
     * 기본 생성자: DBManager를 통해 UsersMapper를 초기화합니다.
     * MyBatis의 매핑을 통해 데이터베이스 작업을 수행할 Mapper 객체를 얻습니다.
     */
    public UsersService() {
        mapper = DBManager.getInstance().getSession().getMapper(UsersMapper.class);
    }

    /**
     * UsersService의 싱글톤 인스턴스를 반환합니다.
     * @return UsersService 인스턴스
     */
    public static UsersService getInstance() {
        // 인스턴스가 null인 경우 새로운 인스턴스를 생성
        if (instance == null)
            instance = new UsersService();
        return instance;
    }

    /**
     * 모든 사용자 정보를 조회합니다.
     * 데이터베이스에서 모든 사용자 정보를 가져와 List 형태로 반환합니다.
     * 
     * @return 사용자 목록 (List<UsersDTO>)
     */
    public List<UsersDTO> selectAllUsers() {
        return mapper.selectAllUsers();
    }

    /**
     * 새로운 사용자를 데이터베이스에 삽입합니다.
     * @param dto 사용자 정보를 담은 UsersDTO 객체
     * @return 삽입된 행의 수 (1이면 성공, 0이면 실패)
     */
    public int insertMember(UsersDTO dto) {
        return mapper.insertMember(dto);
    }

    /**
     * 특정 로그인 ID가 데이터베이스에 존재하는지 확인합니다.
     * @param loginId 확인할 로그인 ID
     * @return true이면 ID가 존재, false이면 ID가 존재하지 않음
     */
    public boolean isLoginIdExists(String loginId) {
        return mapper.selectLoginIdCount(loginId) > 0; // 0보다 크면 아이디가 존재
    }

    /**
     * 특정 로그인 ID를 가진 사용자의 정보를 조회합니다.
     * @param loginId 조회할 사용자의 로그인 ID
     * @return 사용자의 정보 (UsersDTO), 존재하지 않을 경우 null
     */
    public UsersDTO getUserByLoginId(String loginId) {
        System.out.println("[UsersService] getUserByLoginId() 호출 -> loginId: " + loginId + " 사용자 정보 조회 시작");
        return mapper.selectUserByLoginId(loginId);
    }

    /**
     * 사용자 ID와 비밀번호를 사용하여 로그인합니다.
     * 데이터베이스에서 ID와 비밀번호가 일치하는 사용자를 조회합니다.
     * 
     * @param loginId 사용자 로그인 ID
     * @param password 사용자 비밀번호
     * @return 로그인 성공 시 사용자 정보(UsersDTO), 실패 시 null
     */
    public UsersDTO login(String loginId, String password) {
        System.out.println("[UsersService] : 로그인 요청 메서드 ");
        try {
            // ID와 비밀번호를 매퍼에 전달하기 위한 Map 생성
            Map<String, String> map = new HashMap<>();
            map.put("loginId", loginId);
            map.put("password", password);

            // Mapper를 통해 사용자 정보 조회
            UsersDTO dto = mapper.findUserByIdAndPassword(map);
            System.out.println("로그인 결과: " + (dto != null ? "성공: " + dto : "실패: 사용자 정보 없음"));
            return dto;
        } catch (Exception e) {
            // 예외 발생 시 로그 출력
            System.out.println("로그인 처리 중 예외 발생: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
