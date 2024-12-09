package mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import dto.UsersDTO;

/**
 * UsersMapper 인터페이스는 사용자 관련 데이터베이스 작업을 정의하는 MyBatis 매퍼입니다. 이 인터페이스는 SQL 쿼리와
 * Java 메서드 간의 매핑을 제공합니다.
 */
public interface UsersMapper {

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
	 * 새로운 사용자를 데이터베이스에 삽입합니다.
	 * 
	 * @param dto 사용자 정보를 담은 DTO 객체
	 * @return 삽입된 행의 수
	 */
	int insertMember(UsersDTO dto);

	/**
	 * 특정 사용자의 정보를 업데이트합니다.
	 * 
	 * @param dto 수정할 사용자 정보를 담은 DTO 객체
	 * @return 업데이트된 행의 수
	 */
	int updateUser(UsersDTO dto);

	/**
	 * 특정 사용자의 암호를 업데이트합니다.
	 * 
	 * @param userNumber  사용자 번호
	 * @param newPassword 새로운 암호
	 * @return 업데이트된 행의 수
	 */
	int updatePassword(@Param("userNumber") int userNumber, @Param("newPassword") String newPassword);

	/**
	 * 특정 사용자 번호를 기준으로 사용자를 삭제합니다.
	 * 
	 * @param userNumber 사용자 번호
	 * @return 삭제된 행의 수
	 */
	int deleteUserByUserNumber(int userNumber);

	// 아이디 중복 체크
	int selectLoginIdCount(String loginId);

	/**
	 * 로그인 아이디와 비밀번호를 사용하여 사용자 조회하는 메서드
	 * 
	 * @param loginId  로그인 아이디
	 * @param password 패스워드
	 */
	// 로그인 아이디와 비밀번호를 사용하여 사용자 조회하는 메서드
	UsersDTO selectUserByLoginIdAndPassword(@Param("loginId") String loginId, @Param("password") String password);
	
	
	public int countByNickName(String nickName);

}