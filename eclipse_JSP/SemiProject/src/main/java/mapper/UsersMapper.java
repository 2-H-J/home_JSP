package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import dto.UsersDTO;

public interface UsersMapper {

    // 모든 사용자 정보를 조회하는 메서드
    List<UsersDTO> selectAllUsers();

    // 로그인 아이디와 비밀번호를 사용하여 사용자 조회하는 메서드
    UsersDTO selectUserByLoginIdAndPassword(@Param("loginId") String loginId, @Param("password") String password);
    
    // 특정 로그인 아이디로 사용자 조회하는 메서드 (애너테이션을 사용하여 직접 쿼리 작성)
    @Select("SELECT * FROM users WHERE login_id = #{loginId}")
    UsersDTO selectUserByLoginId(@Param("loginId") String loginId);
    
    
}

// 이 인터페이스는 MyBatis 매퍼로서 데이터베이스의 users 테이블과 상호작용하기 위한 메서드를 정의합니다.
// 각 메서드는 사용자의 데이터를 조회하기 위한 다양한 쿼리를 수행합니다.
// @Select 애너테이션을 사용하면 간단한 쿼리를 매퍼 메서드에 직접 작성할 수 있으며, 복잡한 쿼리는 XML 매퍼 파일로 작성할 수 있습니다.
// @Param 애너테이션은 SQL 쿼리에서 사용될 파라미터에 이름을 지정하여 쉽게 참조할 수 있도록 해줍니다.
