package mapper;

import java.util.List;
import java.util.Map;

import dto.UsersDTO;

/**
 * UsersMapper
 * - 사용자 관련 데이터베이스 작업을 정의하는 인터페이스
 * - MyBatis Mapper XML과 연동되어 SQL을 실행
 */
public interface UsersMapper {

    /**
     * 모든 사용자 정보를 조회
     * 
     * @return 사용자 정보 리스트 (List<UsersDTO>)
     */
    List<UsersDTO> selectAllUsers();
    
    UsersDTO selectUserLogin(Map<String, Object> params);

}
