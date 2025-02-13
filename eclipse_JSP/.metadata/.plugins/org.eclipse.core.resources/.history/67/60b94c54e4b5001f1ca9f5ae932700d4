package service;

import java.util.List;

import config.DBManager;
import dto.BoardMemberDTO;
import mapper.BoardMemberMapper;

public class BoardMemberService {
	private static BoardMemberService instance = new BoardMemberService();
	private BoardMemberMapper mapper;
	
	private BoardMemberService() {
		mapper = DBManager.getInstance().getSession().getMapper(BoardMemberMapper.class);
	}

	public static BoardMemberService getInstance() {
		if(instance == null)
			instance = new BoardMemberService();
		return instance;
	}

	public List<BoardMemberDTO> selectNameMember(String name) {
		return mapper.selectNameMember(name);
	}

	public int deleteMember(String id) {
		return mapper.deleteMember(id);
	}

	public List<BoardMemberDTO> selectAllMember() {
		return mapper.selectAllMember();
	}

	public int selectIdMember(String id) {
		return mapper.selectIdMember(id);
	}

	
	
}






