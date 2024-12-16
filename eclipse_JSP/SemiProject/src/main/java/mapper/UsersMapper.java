package mapper;

import dto.UsersDTO;

/**
 * UsersMapper 인터페이스는 사용자 관련 데이터베이스 작업을 정의하는 MyBatis 매퍼입니다.
 * 이 인터페이스는 SQL 쿼리와 Java 메서드 간의 매핑을 제공합니다.
 * 각 메서드는 데이터베이스의 users 테이블에 대한 작업을 수행합니다.
 */
public interface UsersMapper {
	
	// 회원가입---------------------------------------------------------
	/**
	 * 새로운 사용자를 데이터베이스에 삽입합니다.
	 * 
	 * @param dto 사용자 정보를 담은 DTO 객체
	 * @return 삽입된 행의 수
	 */
	int insertMember(UsersDTO dto);

    /**
     * 아이디 중복 여부를 확인합니다.
     * @param loginId 확인할 아이디
     * @return 동일한 아이디의 수 (0이면 중복 없음)
     */
	// 아이디 중복 체크
	int selectLoginIdCount(String loginId);

    /**
     * 이메일 중복 여부를 확인합니다.
     * @param email 확인할 이메일
     * @return 동일한 이메일의 수 (0이면 중복 없음)
     */
	// 이메일 중복 체크
	int selectEmailCount(String email);

    /**
     * 닉네임 중복 여부를 확인합니다.
     * @param nickName 확인할 닉네임
     * @return 동일한 닉네임의 수 (0이면 중복 없음)
     */
	// 닉네임 중복 체크
	int selectNickNameCount(String nickName);
	//	----------------------------------------------------------------
	
	// 로그인 ----------------------------------------------------------
    /**
     * 로그인 아이디로 사용자 정보를 조회합니다.
     * @param loginId 로그인 아이디
     * @return 조회된 사용자 정보 (UsersDTO)
     */
	UsersDTO findUserByLoginId(String loginId);
	//	----------------------------------------------------------------

	// 내정보 수정------------------------------------------------------
	UsersDTO findUserByUserNumber(int userNumber);
    /**
     * 사용자 정보를 업데이트합니다.
     * @param user 업데이트할 사용자 정보 (UsersDTO)
     * @return 업데이트된 행의 수
     */
	int updateUser(UsersDTO user);
	// -----------------------------------------------------------------
	
    
}
