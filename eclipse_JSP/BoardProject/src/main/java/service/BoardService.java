package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import config.DBManager;
import dto.BoardDTO;
import jakarta.websocket.Session;
import mapper.BoardMapper;

public class BoardService {
	private static BoardService instance = new BoardService();

	private BoardService() {	}

	public static BoardService getInstance() {
		if(instance == null)
			instance = new BoardService();
		return instance;
	}

	public List<BoardDTO> getBoardList() {
		try(SqlSession session = DBManager.getInstance().getSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			return mapper.getBoardList();
		}
	}

	public int insertBoard(BoardDTO dto) {
		try(SqlSession session = DBManager.getInstance().getSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			return mapper.insertBoard(dto);
		}
	}

	public BoardDTO selectBoard(int bno) {
	 try(SqlSession session = DBManager.getInstance().getSession()){
		 BoardMapper mapper = session.getMapper(BoardMapper.class);
		 return mapper.selectBoard(bno);
	 }
	}

	public int updateBoardCount(int bno) {
		 try(SqlSession session = DBManager.getInstance().getSession()){
			 BoardMapper mapper = session.getMapper(BoardMapper.class);
			 return mapper.updateBoardCount(bno);
		 }
	}

	
	
	
}