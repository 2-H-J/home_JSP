package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import dto.BoardFileDTO;
import dto.BoardsDTO;
import dto.CommentsDTO;

public interface BoardsMapper {

	List<BoardsDTO> selectAllBoards();

	BoardsDTO selectBoardByPostNumber(int postNumber);

	int deleteBoardByPostNumber(int postNumber);

	int insertBoard(BoardsDTO dto);

	int updateBoard(BoardsDTO board);

	List<BoardsDTO> searchBoardsByTitle(Map<String, Object> params);

	List<BoardsDTO> searchBoardsByWriter(Map<String, Object> params);

	List<BoardsDTO> searchBoardsByTitleSorted(Map<String, Object> params);

	List<BoardsDTO> searchBoardsByWriterSorted(Map<String, Object> params);

	// 조회수
	int updateBoardsCount(int postNumber);

	int insertComment(CommentsDTO comment);

	List<CommentsDTO> getCommentList(int postNumber);

	int deleteComment(int commentNumber);

	List<BoardsDTO> searchBoards(Map<String, Object> params);

	int getBoardCount(Map<String, Object> params);

	List<BoardsDTO> selectBoards(@Param("offset") int offset, @Param("limit") int limit);

	List<BoardsDTO> getBoardsByTag(String tag);

	int getBoardCountByTag(@Param("tag") String tag);

	List<BoardsDTO> getBoardsByTagWithPaging(@Param("tag") String tag, @Param("offset") int offset,
			@Param("limit") int pageSize);

	// 파일업로드 부분 글번호 조회
	int selectPostNumber();

	// 파일업로드
	int insertBoardFile(BoardFileDTO item);

	List<BoardFileDTO> selectFileList(int postNumber);

	String selectFilePath(int fileNumber);

	int insertBoardLike(Map<String, Object> map);

	int deleteBoardLike(Map<String, Object> map);

	int getBoardLike(int postNumber);

	int insertCommentLike(Map<String, Object> map);

	int deleteCommentLike(Map<String, Object> map);

	int getCommentLike(int commentNumber);

	int getCommentUserNumber(int commentNumber);

	BoardFileDTO getFileByNumber(int fileNumber);

	int deleteFile(int fileNumber);

	int deleteBoardFile(int postNumber);

}