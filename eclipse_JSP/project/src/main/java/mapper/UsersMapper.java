package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import dto.UsersDTO;

/**
 * UsersMapper 인터페이스는 사용자 관련 데이터베이스 작업을 정의하는 MyBatis 매퍼입니다. 이 인터페이스는 SQL 쿼리와
 * Java 메서드 간의 매핑을 제공합니다. 각 메서드는 데이터베이스의 users 테이블에 대한 작업을 수행합니다.
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
	 * 
	 * @param loginId 확인할 아이디
	 * @return 동일한 아이디의 수 (0이면 중복 없음)
	 */
	// 아이디 중복 체크
	int selectLoginIdCount(String loginId);

	/**
	 * 이메일 중복 여부를 확인합니다.
	 * 
	 * @param email 확인할 이메일
	 * @return 동일한 이메일의 수 (0이면 중복 없음)
	 */
	// 이메일 중복 체크
	int selectEmailCount(String email);

	/**
	 * 닉네임 중복 여부를 확인합니다.
	 * 
	 * @param nickName 확인할 닉네임
	 * @return 동일한 닉네임의 수 (0이면 중복 없음)
	 */
	// 닉네임 중복 체크
	int selectNickNameCount(String nickName);
	// ----------------------------------------------------------------

	// 로그인 ----------------------------------------------------------
	/**
	 * 로그인 아이디로 사용자 정보를 조회합니다.
	 * 
	 * @param loginId 로그인 아이디
	 * @return 조회된 사용자 정보 (UsersDTO)
	 */
	UsersDTO findUserByLoginId(String loginId);
	// ----------------------------------------------------------------

	// 내정보 수정------------------------------------------------------
	UsersDTO findUserByUserNumber(int userNumber);

	/**
	 * 사용자 정보를 업데이트합니다.
	 * 
	 * @param user 업데이트할 사용자 정보 (UsersDTO)
	 * @return 업데이트된 행의 수
	 */
	int updateUser(UsersDTO user);
	// -----------------------------------------------------------------
	// 사용자 이름과 이메일로 로그인 ID 찾기 12/17 추가

	String findLoginIdByUserNameAndEmail(@Param("userName") String userName, @Param("userEmail") String userEmail);

	// 이름과 이메일로 사용자 ID 조회
	String findUserByNameAndEmail(@Param("userName") String userName, @Param("userEmail") String userEmail);

	// 새작업 12월 16일
	// 사용자 비밀번호 업데이트
	UsersDTO findUserForPasswordReset(Map<String, String> params);

	int updatePassword(Map<String, Object> params);
	// -----------------------------------------------------------------

	/**
	 * 모든 사용자 정보를 조회합니다.
	 * 
	 * @return 사용자 목록 (List 형태로 반환)
	 */
	List<UsersDTO> selectAllUsers();

	/**
	 * 특정 사용자 번호를 기준으로 사용자 정보를 조회합니다.
	 * 
	 * @param userNumber 사용자 번호
	 * @return 해당 사용자의 상세 정보
	 */
	UsersDTO selectUserByUserNumber(int userNumber);

	/**
	 * 특정 사용자 ID를 기준으로 사용자 정보를 조회합니다.
	 * 
	 * @param loginId 사용자 로그인 ID
	 * @return 해당 사용자의 상세 정보
	 */
	UsersDTO selectUserByLoginId(String loginId);

	/**
	 * 특정 사용자의 암호를 업데이트합니다.
	 * 
	 * @param userNumber  사용자 번호
	 * @param newPassword 새로운 암호
	 * @return 업데이트된 행의 수
	 */
	int updatePassword(@Param("userNumber") int userNumber, @Param("newPassword") String newPassword);

	/**
	 * 로그인 아이디와 비밀번호를 사용하여 사용자 조회하는 메서드
	 * 
	 * @param loginId  로그인 아이디
	 * @param password 패스워드
	 */
	// 로그인 아이디와 비밀번호를 사용하여 사용자 조회하는 메서드
	UsersDTO selectUserByLoginIdAndPassword(@Param("loginId") String loginId, @Param("password") String password);

	List<UsersDTO> selectAllUsersExcludeAdmin();

	UsersDTO selectUserByNumber(int userNumber);

	int deleteCommentsByUserNumber(int userNumber); // 댓글 삭제 /admin

	int deleteBoardsByUserNumber(int userNumber); // 게시물 삭제 /admin

	int deleteUserByUserNumber(int userNumber); // 사용자 삭제 /admin
}