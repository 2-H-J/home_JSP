package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		if (instance == null)
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

	public BoardMemberDTO login(String id, String password) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			map.put("password", password);
			return mapper.findMemberByIdAndPassword(map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}