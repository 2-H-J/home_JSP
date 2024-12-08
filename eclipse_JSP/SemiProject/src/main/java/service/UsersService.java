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

	public UsersDTO login(String loginId, String password) {
		System.out.println("[UsersService] login() 호출 -> loginId: " + loginId + ", 비밀번호 인증 시작");
		return mapper.selectUserByLoginIdAndPassword(loginId, password);
	}

	public UsersDTO getUserByLoginId(String loginId) {
		System.out.println("[UsersService] getUserByLoginId() 호출 -> loginId: " + loginId + " 사용자 정보 조회 시작");
		return mapper.selectUserByLoginId(loginId);
	}
}