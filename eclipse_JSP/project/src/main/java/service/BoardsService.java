package service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import config.DBManager;
import dto.BoardFileDTO;
import dto.BoardsDTO;
import dto.CommentsDTO;
import mapper.BoardsMapper;

/**
 * BoardsService 클래스는 게시판 관련 비즈니스 로직을 처리하는 서비스 계층입니다.
 * 이 클래스는 BoardsMapper와 상호작용하여 데이터베이스 작업을 수행합니다.
 * 주로 게시글, 댓글, 파일, 좋아요 등과 관련된 기능을 제공합니다.
 */
public class BoardsService {

    // BoardsService의 유일한 인스턴스를 저장하는 변수 (싱글톤 패턴)
    private static BoardsService instance = new BoardsService();

    // BoardsMapper 객체 (MyBatis 매퍼)
    private BoardsMapper mapper;

    /**
     * 기본 생성자: DBManager를 통해 BoardsMapper를 초기화합니다.
     */
    public BoardsService() {
        mapper = DBManager.getInstance().getSession().getMapper(BoardsMapper.class);
    }

    /**
     * BoardsService의 싱글톤 인스턴스를 반환합니다.
     * 
     * @return BoardsService 인스턴스
     */
    public static BoardsService getInstance() {
        if (instance == null)
            instance = new BoardsService();
        return instance;
    }

    /**
     * 모든 게시글을 조회합니다.
     * 
     * @return 게시글 목록 (List 형태로 반환)
     */
    public List<BoardsDTO> selectAllBoards() {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            BoardsMapper mapper = session.getMapper(BoardsMapper.class);
            return mapper.selectAllBoards();  // 모든 게시글 목록 반환
        }
    }

    /**
     * 특정 게시글 번호를 기준으로 게시글 상세 정보를 조회합니다.
     * 
     * @param postNumber 게시글 번호
     * @return 해당 게시글의 상세 정보
     */
    public BoardsDTO selectBoardByPostNumber(int postNumber) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            BoardsMapper mapper = session.getMapper(BoardsMapper.class);
            session.clearCache();  // 캐시를 초기화하여 최신 데이터를 조회
            return mapper.selectBoardByPostNumber(postNumber);
        }
    }

    /**
     * 특정 게시글 번호를 기준으로 게시글을 삭제합니다.
     * 
     * @param postNumber 게시글 번호
     * @return 삭제된 행의 수
     */
    public int deleteBoardByPostNumber(int postNumber) {
        return mapper.deleteBoardByPostNumber(postNumber);  // 게시글 삭제
    }

    /**
     * 새로운 게시글을 데이터베이스에 삽입합니다.
     * 
     * @param dto 게시글 정보를 담은 DTO 객체
     * @param fList 게시글에 첨부된 파일 목록
     * @return 삽입된 행의 수
     */
    public int insertBoard(BoardsDTO dto, List<BoardFileDTO> fList) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            BoardsMapper mapper = session.getMapper(BoardsMapper.class);
            int postNumber = mapper.selectPostNumber();  // 새로운 게시글 번호 조회
            dto.setPostNumber(postNumber);  // 게시글 DTO에 번호 설정
            int count = mapper.insertBoard(dto);  // 게시글 삽입
            fList.forEach(item -> {
                item.setPostNumber(postNumber);  // 각 파일의 게시글 번호 설정
                mapper.insertBoardFile(item);  // 파일 삽입
            });
            return count;  // 삽입된 게시글 수 반환
        }
    }

    /**
     * 특정 게시글 정보를 업데이트합니다.
     * 
     * @param dto 수정할 게시글 정보를 담은 DTO 객체
     * @param fList 수정된 게시글에 첨부된 파일 목록
     * @return 업데이트된 행의 수
     */
    public int updateBoard(BoardsDTO dto, List<BoardFileDTO> fList) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            BoardsMapper mapper = session.getMapper(BoardsMapper.class);
            int count = mapper.updateBoard(dto);  // 게시글 업데이트
            fList.forEach(item -> {
                item.setPostNumber(dto.getPostNumber());  // 파일의 게시글 번호 설정
                mapper.insertBoardFile(item);  // 새 파일 삽입
            });
            session.commit();  // 트랜잭션 커밋
            return count;  // 업데이트된 게시글 수 반환
        }
    }

    /**
     * 특정 태그에 해당하는 게시글 목록을 조회합니다.
     * 
     * @param tag 태그
     * @return 태그에 해당하는 게시글 목록
     */
    public List<BoardsDTO> getBoardsByTag(String tag) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            BoardsMapper mapper = session.getMapper(BoardsMapper.class);
            return mapper.getBoardsByTag(tag);  // 태그로 게시글 목록 조회
        }
    }

    /**
     * 게시글 조회수를 업데이트합니다.
     * 
     * @param postNumber 게시글 번호
     * @return 업데이트된 행의 수
     */
    public int updateBoardsCount(int postNumber) {
        return mapper.updateBoardsCount(postNumber);  // 조회수 증가
    }

    /**
     * 댓글을 삽입합니다.
     * 
     * @param comment 댓글 정보
     * @return 삽입된 행의 수
     */
    public int insertComment(CommentsDTO comment) {
        return mapper.insertComment(comment);  // 댓글 삽입
    }

    /**
     * 특정 게시글에 대한 댓글 목록을 조회합니다.
     * 
     * @param postNumber 게시글 번호
     * @return 댓글 목록
     */
    public List<CommentsDTO> getCommentList(int postNumber) {
        return mapper.getCommentList(postNumber);  // 댓글 목록 조회
    }

    /**
     * 특정 댓글을 삭제합니다.
     * 
     * @param commentNumber 댓글 번호
     * @return 삭제된 행의 수
     */
    public int deleteComment(int commentNumber) {
        return mapper.deleteComment(commentNumber);  // 댓글 삭제
    }

    /**
     * 게시글 목록의 총 개수를 조회합니다.
     * 
     * @param params 검색 조건을 담은 맵
     * @return 게시글 개수
     */
    public int getBoardCount(Map<String, Object> params) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            BoardsMapper mapper = session.getMapper(BoardsMapper.class);
            return mapper.getBoardCount(params);  // 게시글 수 조회
        }
    }

    /**
     * 페이징 처리가 된 게시글 목록을 조회합니다.
     * 
     * @param offset 페이지 시작 지점
     * @param pageSize 한 페이지에 표시될 게시글 수
     * @return 페이징 처리된 게시글 목록
     */
    public List<BoardsDTO> selectBoards(int offset, int pageSize) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            BoardsMapper mapper = session.getMapper(BoardsMapper.class);
            return mapper.selectBoards(offset, pageSize);  // 페이징된 게시글 목록 조회
        }
    }

    /**
     * 게시글을 검색 조건에 맞게 조회합니다.
     * 
     * @param params 검색 조건을 담은 맵
     * @return 검색된 게시글 목록
     */
    public List<BoardsDTO> searchBoards(Map<String, Object> params) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            BoardsMapper mapper = session.getMapper(BoardsMapper.class);
            return mapper.searchBoards(params);  // 조건에 맞는 게시글 검색
        }
    }

    /**
     * 전체 게시글 수를 조회합니다.
     * 
     * @return 전체 게시글 수
     */
    public int getTotalRecords() {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            BoardsMapper mapper = session.getMapper(BoardsMapper.class);
            return mapper.getBoardCount(null);  // 전체 게시글 수 조회
        }
    }

    /**
     * 특정 태그에 해당하는 게시글의 수를 조회합니다.
     * 
     * @param tag 태그
     * @return 태그에 해당하는 게시글 수
     */
    public int getBoardCountByTag(String tag) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            BoardsMapper mapper = session.getMapper(BoardsMapper.class);
            return mapper.getBoardCountByTag(tag);  // 태그에 해당하는 게시글 수 조회
        }
    }

    /**
     * 특정 태그에 해당하는 게시글을 페이징 처리하여 조회합니다.
     * 
     * @param tag 태그
     * @param offset 페이지 시작 지점
     * @param pageSize 한 페이지에 표시될 게시글 수
     * @return 페이징된 게시글 목록
     */
    public List<BoardsDTO> getBoardsByTagWithPaging(String tag, int offset, int pageSize) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            BoardsMapper mapper = session.getMapper(BoardsMapper.class);
            return mapper.getBoardsByTagWithPaging(tag, offset, pageSize);  // 페이징된 태그별 게시글 목록 조회
        }
    }

    /**
     * 특정 게시글에 첨부된 파일 목록을 조회합니다.
     * 
     * @param postNumber 게시글 번호
     * @return 게시글에 첨부된 파일 목록
     */
    public List<BoardFileDTO> selectFileList(int postNumber) {
        return mapper.selectFileList(postNumber);  // 게시글에 첨부된 파일 목록 조회
    }

    /**
     * 특정 파일의 경로를 조회합니다.
     * 
     * @param fileNumber 파일 번호
     * @return 파일 경로
     */
    public BoardFileDTO selectFilePath(int fileNumber) {
        return mapper.selectFilePath(fileNumber);  // 파일 경로 조회
    }

    /**
     * 게시글에 좋아요를 추가합니다.
     * 
     * @param postNumber 게시글 번호
     * @param userNumber 사용자 번호
     * @return 삽입된 행의 수
     */
    public int insertBoardLike(int postNumber, int userNumber) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            BoardsMapper mapper = session.getMapper(BoardsMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("postNumber", postNumber);
            map.put("userNumber", userNumber);
            return mapper.insertBoardLike(map);  // 게시글 좋아요 추가
        }
    }

    /**
     * 게시글의 좋아요를 취소합니다.
     * 
     * @param postNumber 게시글 번호
     * @param userNumber 사용자 번호
     * @return 삭제된 행의 수
     */
    public int deleteBoardLike(int postNumber, int userNumber) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            BoardsMapper mapper = session.getMapper(BoardsMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("postNumber", postNumber);
            map.put("userNumber", userNumber);
            return mapper.deleteBoardLike(map);  // 게시글 좋아요 취소
        }
    }

    /**
     * 게시글에 대한 좋아요 수를 조회합니다.
     * 
     * @param postNumber 게시글 번호
     * @return 좋아요 수
     */
    public int getBoardLike(int postNumber) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            BoardsMapper mapper = session.getMapper(BoardsMapper.class);
            return mapper.getBoardLike(postNumber);  // 게시글 좋아요 수 조회
        }
    }

    /**
     * 댓글에 좋아요를 추가합니다.
     * 
     * @param commentNumber 댓글 번호
     * @param userNumber 사용자 번호
     * @return 삽입된 행의 수
     */
    public int insertCommentLike(int commentNumber, int userNumber) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            BoardsMapper mapper = session.getMapper(BoardsMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("commentNumber", commentNumber);
            map.put("userNumber", userNumber);
            return mapper.insertCommentLike(map);  // 댓글 좋아요 추가
        }
    }

    /**
     * 댓글의 좋아요를 취소합니다.
     * 
     * @param commentNumber 댓글 번호
     * @param userNumber 사용자 번호
     * @return 삭제된 행의 수
     */
    public int deleteCommentLike(int commentNumber, int userNumber) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            BoardsMapper mapper = session.getMapper(BoardsMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("commentNumber", commentNumber);
            map.put("userNumber", userNumber);
            return mapper.deleteCommentLike(map);  // 댓글 좋아요 취소
        }
    }

    /**
     * 댓글에 대한 좋아요 수를 조회합니다.
     * 
     * @param commentNumber 댓글 번호
     * @return 좋아요 수
     */
    public int getCommentLike(int commentNumber) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            BoardsMapper mapper = session.getMapper(BoardsMapper.class);
            return mapper.getCommentLike(commentNumber);  // 댓글 좋아요 수 조회
        }
    }

    /**
     * 특정 댓글의 작성자 번호를 조회합니다.
     * 
     * @param commentNumber 댓글 번호
     * @return 작성자 번호
     */
    public int getCommentUserNumber(int commentNumber) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            BoardsMapper mapper = session.getMapper(BoardsMapper.class);
            return mapper.getCommentUserNumber(commentNumber);  // 댓글 작성자 번호 조회
        }
    }

    /**
     * 특정 파일의 정보를 조회합니다.
     * 
     * @param fileNumber 파일 번호
     * @return 파일 정보
     */
    public BoardFileDTO getFileByNumber(int fileNumber) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            BoardsMapper mapper = session.getMapper(BoardsMapper.class);
            return mapper.getFileByNumber(fileNumber);  // 파일 정보 조회
        }
    }

    /**
     * 특정 파일을 삭제합니다.
     * 
     * @param fileNumber 파일 번호
     * @return 삭제된 행의 수
     */
    public int deleteFile(int fileNumber) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            BoardsMapper mapper = session.getMapper(BoardsMapper.class);
            return mapper.deleteFile(fileNumber);  // 파일 삭제
        }
    }

    /**
     * 게시글에 첨부된 파일을 삭제합니다.
     * 
     * @param postNumber 게시글 번호
     * @return 삭제된 행의 수
     */
    public int deleteBoardFile(int postNumber) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            BoardsMapper mapper = session.getMapper(BoardsMapper.class);
            return mapper.deleteBoardFile(postNumber);  // 게시글 첨부 파일 삭제
        }
    }
}
