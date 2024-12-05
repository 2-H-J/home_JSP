package mapper;

import java.util.List;

import dto.UsersDTO;

public interface UsersMapper {

	List<UsersDTO> selectAllUsers();

}
