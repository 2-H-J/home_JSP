package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import dto.UsersDTO;

/**
 * UsersMapper 인터페이스는 사용자 관련 데이터베이스 작업을 정의하는 MyBatis 매퍼입니다.
 * 이 인터페이스는 SQL 쿼리와 Java 메서드 간의 매핑을 제공합니다.
 * 각 메서드는 데이터베이스의 users 테이블에 대한 작업을 수행합니다.
 */
public interface UsersMapper {

    /**
     * 모든 사용자 정보를 조회합니다.
     * 
     * @return 모든 사용자 정보를 담은 UsersDTO 객체 리스트
     */
    List<UsersDTO> selectAllUsers();

    /**
     * 특정 사용자 번호를 기준으로 사용자 정보를 조회합니다.
     * 
     * @param userNumber 조회할 사용자 번호
     * @return 해당 사용자의 상세 정보를 담은 UsersDTO 객체
     */
    UsersDTO selectUserByUserNumber(int userNumber);

    /**
     * 특정 사용자 ID를 기준으로 사용자 정보를 조회합니다.
     * 
     * @param loginId 사용자 로그인 ID
     * @return 해당 사용자의 상세 정보를 담은 UsersDTO 객체
     */
    UsersDTO selectUserByLoginId(String loginId);

    /**
     * 새로운 사용자를 데이터베이스에 삽입합니다.
     * 
     * @param dto 삽입할 사용자 정보를 담은 UsersDTO 객체
     * @return 삽입된 행의 수 (1이면 성공, 0이면 실패)
     */
    int insertMember(UsersDTO dto);

    /**
     * 특정 사용자의 정보를 업데이트합니다.
     * 
     * @param dto 수정할 사용자 정보를 담은 UsersDTO 객체
     * @return 업데이트된 행의 수 (1이면 성공, 0이면 실패)
     */
    int updateUser(UsersDTO dto);

    /**
     * 특정 사용자의 암호를 업데이트합니다.
     * 
     * @param userNumber  사용자 번호
     * @param newPassword 새로 설정할 암호
     * @return 업데이트된 행의 수 (1이면 성공, 0이면 실패)
     */
    int updatePassword(@Param("userNumber") int userNumber, @Param("newPassword") String newPassword);

    /**
     * 특정 사용자 번호를 기준으로 사용자를 삭제합니다.
     * 
     * @param userNumber 삭제할 사용자 번호
     * @return 삭제된 행의 수 (1이면 성공, 0이면 실패)
     */
    int deleteUserByUserNumber(int userNumber);

    /**
     * 특정 로그인 ID의 개수를 조회합니다.
     * 중복 확인 또는 해당 ID의 존재 여부를 확인할 때 사용됩니다.
     * 
     * @param loginId 확인할 로그인 ID
     * @return 해당 로그인 ID의 개수 (0 또는 1)
     */
    int selectLoginIdCount(String loginId);

    /**
     * 특정 로그인 ID와 비밀번호를 기준으로 사용자 정보를 조회합니다.
     * 
     * @param loginId  사용자 로그인 ID
     * @param password 사용자 비밀번호
     * @return 로그인 성공 시 사용자 정보를 담은 UsersDTO 객체, 실패 시 null
     */
    UsersDTO selectUserByLoginIdAndPassword(@Param("loginId") String loginId, @Param("password") String password);

    /**
     * 로그인 ID와 비밀번호를 Map 객체로 전달받아 사용자 정보를 조회합니다.
     * 
     * @param map ID와 비밀번호를 담은 Map 객체 (key: "loginId", "password")
     * @return 로그인 성공 시 사용자 정보를 담은 UsersDTO 객체, 실패 시 null
     */
    UsersDTO findUserByIdAndPassword(Map<String, String> map);
    
    /**
     * 특정 닉네임의 개수를 조회합니다.
     * 중복 확인 또는 해당 닉네임의 존재 여부를 확인할 때 사용됩니다.
     * 
     * @param nickName 확인할 닉네임
     * @return 해당 닉네임의 개수 (0 이상)
     */
    int countNickName(String nickName);

    /**
     * 특정 사용자의 닉네임을 업데이트합니다.
     * 
     * @param userNumber 사용자 번호
     * @param nickName 새로 설정할 닉네임
     * @return 업데이트된 행의 수 (1이면 성공, 0이면 실패)
     */
    int updateNickName(@Param("userNumber") int userNumber, @Param("nickName") String nickName);

	int countEmail(String email);
    
    
}
