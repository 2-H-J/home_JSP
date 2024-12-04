package mapper;

import java.util.List;

import config.DBManager;
import dto.BoardMemberDTO;


public class BoardMemberMapper {
	private static BoardMemberMapper instantce = new BoardMemberMapper();
	private DBManager manager;
	
	private BoardMemberMapper() {
		manager = DBManager.getInstance();
	}
	
	public static BoardMemberMapper getInstance() {
		if(instantce == null)
			instantce = new BoardMemberMapper();
		return instantce;
	}
	
	public List<BoardMemberDTO> selectAllMember() {
		// member-mapper.xml에 있는 아이디 속성값으로 실행
		return manager.getSession().selectList("selectAllMember");
	}
}
