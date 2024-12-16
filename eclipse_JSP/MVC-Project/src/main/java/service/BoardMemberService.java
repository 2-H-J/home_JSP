package service;

import java.util.List;

import config.DBManager;
import dto.BoardMemberDTO;
import mapper.BoardMemberMapper;

public class BoardMemberService {
    // 싱글톤 인스턴스 생성
    private static BoardMemberService instance = new BoardMemberService();
    // BoardMemberMapper 객체
    private BoardMemberMapper mapper;

    // private 생성자 - 외부에서 인스턴스를 생성하지 못하도록 막음
    private BoardMemberService() {
        // DBManager를 통해 MyBatis 세션을 얻고, BoardMemberMapper 객체를 초기화
        mapper = DBManager.getInstance().getSession().getMapper(BoardMemberMapper.class);
    }

    // 싱글톤 인스턴스를 반환하는 메소드
    public static BoardMemberService getInstance() {
        // 기존 인스턴스가 없을 경우에만 새로 생성
        if (instance == null) {
            instance = new BoardMemberService();
        }
        return instance;
    }

    // 특정 이름을 포함한 멤버들을 조회하는 메소드
    // BoardMemberMapper의 selectNameMember 메소드를 호출하여 이름 검색 결과를 반환함
    public List<BoardMemberDTO> selectNameMember(String name) {
        return mapper.selectNameMember(name);
    }

    // 특정 ID를 가진 멤버를 삭제하는 메소드
    // BoardMemberMapper의 deleteMember 메소드를 호출하여 멤버를 삭제
    public int deleteMember(String id) {
        return mapper.deleteMember(id);
    }

    // 모든 멤버 정보를 조회하는 메소드
    // BoardMemberMapper의 selectAllMember 메소드를 호출하여 멤버 목록을 반환
    public List<BoardMemberDTO> selectAllMember() {
        return mapper.selectAllMember();
    }

    // 특정 ID를 가진 멤버가 존재하는지 확인하는 메소드
    // BoardMemberMapper의 selectIdMember 메소드를 호출하여 멤버의 개수를 반환
    public int selectIdMember(String id) {
        return mapper.selectIdMember(id);
    }
}
