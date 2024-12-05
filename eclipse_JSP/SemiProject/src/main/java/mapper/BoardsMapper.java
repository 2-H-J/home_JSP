package mapper;

import java.util.List;
import dto.BoardsDTO;

/**
 * Boardsmapper
 * - 게시판 관련 데이터베이스 작업을 정의하는 인터페이스
 * - MyBatis Mapper XML과 연결되어 실제 SQL 실행
 */
public interface BoardsMapper {

    /**
     * 게시판의 모든 게시물을 조회
     * 
     * @return 게시물 리스트 (List<BoardsDTO>)
     */
    List<BoardsDTO> selectAllBoards();

    /**
     * 게시물 번호를 기반으로 특정 게시물을 조회
     * 
     * @param postNumber 게시물 번호
     * @return 게시물 정보 (BoardsDTO)
     */
    List<BoardsDTO> selectBoardByPostNumber(int postNumber);

    /**
     * 게시물 번호를 기반으로 특정 게시물을 삭제
     * 
     * @param postNumber 삭제할 게시물 번호
     * @return 삭제된 행의 개수 (int)
     */
    int deleteBoardByPostNumber(int postNumber);

}
