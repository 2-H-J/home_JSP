package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dto.BoardsDTO;

public interface BoardsMapper {

	List<BoardsDTO> selectAllBoards();

	List<BoardsDTO> selectBoardByPostNumber(int postNumber);

	int deleteBoardByPostNumber(int postNumber);

	int insertBoard(BoardsDTO dto);

	int updateBoard(BoardsDTO board);

	List<BoardsDTO> selectPagedBoards(@Param("startRow") int startRow, @Param("endRow") int endRow);

	int getTotalBoardCount();

}
