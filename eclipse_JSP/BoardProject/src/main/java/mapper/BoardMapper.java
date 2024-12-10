package mapper;

import java.util.List;

import dto.BoardDTO;

public interface BoardMapper {
	//페이지별 게시글 목록 조회	
	List<BoardDTO> getBoardList();
	//게시글 등록
	int insertBoard(BoardDTO dto);
	BoardDTO selectBoard(int bno);
	int updateBoardCount(int bno);

}