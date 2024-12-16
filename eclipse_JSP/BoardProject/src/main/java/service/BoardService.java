package service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import config.DBManager;
import dto.BoardCommentDTO;
import dto.BoardDTO;
import dto.BoardFileDTO;
import mapper.BoardMapper;

public class BoardService {
	private static BoardService instance = new BoardService();

	private BoardService() {	}

	public static BoardService getInstance() {
		if(instance == null)
			instance = new BoardService();
		return instance;
	}

	public List<BoardDTO> getBoardList(int pageNo, int pageContentEa) {
		try(SqlSession session = DBManager.getInstance().getSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageNo", pageNo);
			map.put("pageContentEa", pageContentEa);
			
			return mapper.getBoardList(map);
		}
	}

	public int insertBoard(BoardDTO dto, List<BoardFileDTO> fList) {
		try(SqlSession session = DBManager.getInstance().getSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			int bno = mapper.selectBoardNo();
			dto.setBno(bno);
			int count = mapper.insertBoard(dto);
			fList.forEach(item -> {
				item.setBno(bno);
				mapper.insertBoardFile(item);
			});
			return count;
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

	public int insertBoardComment(BoardCommentDTO dto) {
		try(SqlSession session = DBManager.getInstance().getSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			return mapper.insertBoardComment(dto);
		}
	}

	public List<BoardCommentDTO> getCommentList(int bno) {
		try(SqlSession session = DBManager.getInstance().getSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			return mapper.getCommentList(bno);
		}
	}

	public int deleteBoard(int bno) {
		try(SqlSession session = DBManager.getInstance().getSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			return mapper.deleteBoard(bno);
		} 
	}

	public int deleteBoardComment(int cno) {
		try(SqlSession session = DBManager.getInstance().getSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			return mapper.deleteBoardComment(cno);
		}
	}

	public int updateBoard(BoardDTO dto) {
		try(SqlSession session = DBManager.getInstance().getSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			return mapper.updateBoard(dto);
		} 
	}

	public List<BoardFileDTO> getBoardFileList(int bno) {
		try(SqlSession session = DBManager.getInstance().getSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			return mapper.getBoardFileList(bno);
		}
	}

	public String selectFilePath(int fno) {
		try(SqlSession session = DBManager.getInstance().getSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			return mapper.selectFilePath(fno);
		}
	}

	public int insertBoardLike(int bno, String id) {
		try(SqlSession session = DBManager.getInstance().getSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bno", bno);
			map.put("id", id);
			return mapper.insertBoardLike(map);
		}
	}
	public int deleteBoardLike(int bno, String id) {
		try(SqlSession session = DBManager.getInstance().getSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bno", bno);
			map.put("id", id);
			return mapper.deleteBoardLike(map);
		}
	}

	public int getBoardLike(int bno) {
		try(SqlSession session = DBManager.getInstance().getSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			return mapper.getBoardLike(bno);
		}
	}
	public int getBoardHate(int bno) {
		try(SqlSession session = DBManager.getInstance().getSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			return mapper.getBoardHate(bno);
		}
	}

	public int insertBoardHate(int bno, String id) {
		try(SqlSession session = DBManager.getInstance().getSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bno", bno);
			map.put("id", id);
			return mapper.insertBoardHate(map);
		}
	}
	public int deleteBoardHate(int bno, String id) {
		try(SqlSession session = DBManager.getInstance().getSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bno", bno);
			map.put("id", id);
			return mapper.deleteBoardHate(map);
		}
	}

	public int insertBoardCommentLike(int cno, String id) {
		try(SqlSession session = DBManager.getInstance().getSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cno", cno);
			map.put("id", id);
			return mapper.insertBoardCommentLike(map);
		}
	}
	public int deleteBoardCommentLike(int cno, String id) {
		try(SqlSession session = DBManager.getInstance().getSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cno", cno);
			map.put("id", id);
			return mapper.deleteBoardCommentLike(map);
		}
		
	}
	public int insertBoardCommentHate(int cno, String id) {
		try(SqlSession session = DBManager.getInstance().getSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cno", cno);
			map.put("id", id);
			return mapper.insertBoardCommentHate(map);
		}
	}
	public int deleteBoardCommentHate(int cno, String id) {
		try(SqlSession session = DBManager.getInstance().getSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cno", cno);
			map.put("id", id);
			return mapper.deleteBoardCommentHate(map);
		}
		
	}

	public int selectBoardTotalCount() {
		try(SqlSession session = DBManager.getInstance().getSession()){
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			return mapper.selectBoardTotalCount();
		}
	}

	
	
	
}





