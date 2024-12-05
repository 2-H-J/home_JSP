package service;

import java.util.List;

import config.DBManager;
import dto.UsersDTO;
import mapper.UsersMapper;

public class UsersService {
	private static UsersService instance = new UsersService();
	private UsersMapper mapper;
	
	public UsersService() {
		mapper = DBManager.getInstance().getSession().getMapper(UsersMapper.class);
	}

	public static UsersService getInstance() {
		if(instance == null)
			instance = new UsersService();
		return instance;
	}

	public List<UsersDTO> selectAllUsers() {
		return mapper.selectAllUsers();
	}

}
