package service;

import java.util.List;

import config.DBManager;
import dto.BoardsDTO;
import mapper.BoardsMapper;

public class BoardsService {
	private static BoardsService instance = new BoardsService();
	private BoardsMapper mapper;

	public BoardsService() {
		mapper = DBManager.getInstance().getSession().getMapper(BoardsMapper.class);
	}

	public static BoardsService getInstance() {
		if(instance == null)
			instance = new BoardsService();
		return instance;
	}

	public List<BoardsDTO> selectAllBoards() {
		return mapper.selectAllBoards();
	}

	public List<BoardsDTO> selectBoardByPostNumber(int postNumber) {
		return mapper.selectBoardByPostNumber(postNumber);
	}

	public int deleteBoardByPostNumber(int postNumber) {
		return mapper.deleteBoardByPostNumber(postNumber);
	}

	public int insertBoard(BoardsDTO dto) {
		return mapper.insertBoard(dto);
	}

	public int updateBoard(BoardsDTO board) {
		return mapper.updateBoard(board);
	}

	
	
	
}
