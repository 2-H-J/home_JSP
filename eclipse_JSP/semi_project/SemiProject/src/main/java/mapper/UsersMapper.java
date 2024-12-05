package mapper;

import java.util.List;
import java.util.Map;

import dto.UsersDTO;

public interface UsersMapper {
	
	List<UsersDTO> selectAllUsers();
	
	// 로그인용 메서드 추가
    UsersDTO selectUserLogin(Map<String, Object> params);
}
