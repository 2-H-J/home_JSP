package service;

import java.util.List;

import config.DBManager;
import dto.UsersDTO;
import mapper.UsersMapper;

/**
 * UsersService 클래스는 사용자 관련 비즈니스 로직을 처리하는 서비스 계층입니다. 이 클래스는 UsersMapper와 상호작용하여
 * 데이터베이스 작업을 수행합니다.
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
		mapper = DBManager.getInstance().getSession().getMapper(UsersMapper.class);
	}

	/**
	 * UsersService의 싱글톤 인스턴스를 반환합니다.
	 * 
	 * @return UsersService 인스턴스
	 */
	public static UsersService getInstance() {
		if (instance == null)
			instance = new UsersService();
		return instance;
	}

	/**
	 * 모든 사용자 정보를 조회합니다.
	 * 
	 * @return 사용자 목록 (List 형태로 반환)
	 */
	public List<UsersDTO> selectAllUsers() {
		return mapper.selectAllUsers();
	}

	/**
	 * 새로운 사용자를 데이터베이스에 삽입합니다.
	 * 
	 * @param dto 사용자 정보를 담은 DTO 객체
	 * @return 삽입된 행의 수
	 */
	public int insertMember(UsersDTO dto) {
		return mapper.insertMember(dto);
	}

	public boolean isLoginIdExists(String loginId) {
		return mapper.selectLoginIdCount(loginId) > 0; // 0보다 크면 아이디가 존재
	}

	public UsersDTO getUserByLoginId(String loginId) {
		System.out.println("[UsersService] getUserByLoginId() 호출 -> loginId: " + loginId + " 사용자 정보 조회 시작");
		return mapper.selectUserByLoginId(loginId);
	}

	// 이메일 중복 확인 메서드 추가
	/**
	 * 이메일 중복 여부를 확인합니다.
	 * 
	 * @param email 확인할 이메일 주소
	 * @return true (존재), false (미존재)
	 */
	public boolean isEmailExists(String email) {
		return mapper.selectEmailCount(email) > 0; // 0보다 크면 이메일이 존재
	}

	// 닉네임 중복 확인 메서드 추가
	/**
	 * 닉네임 중복 여부를 확인합니다.
	 * 
	 * @param nickName 확인할 닉네임
	 * @return true (존재), false (미존재)
	 */
	public boolean isNickNameExists(String nickName) {
	    System.out.println("닉네임 확인 요청: " + nickName); // 로그 출력
	    return mapper.selectNickNameCount(nickName) > 0;
	}
//	이메일로 ID 찾기
    public String findLoginIdByEmail(String email) {
        return mapper.selectLoginIdByEmail(email);
    }
    
//	----------------------------------PW찾기
	public UsersDTO getUserByLoginIdAndEmail(String loginId, String useremail) {
	    return mapper.selectUserByLoginIdAndEmail(loginId, useremail);
	}

	public int updatePassword(UsersDTO user) {
	    return mapper.updatePassword(user);
	}

	

}
