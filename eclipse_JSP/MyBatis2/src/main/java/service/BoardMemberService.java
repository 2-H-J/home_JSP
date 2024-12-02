package service;


import java.util.List;

import config.DBManager;
import dto.BoardMemberDTO;
import mapper.BoardMemberMapper;

public class BoardMemberService {
    // Singleton 인스턴스를 저장하기 위한 정적 변수 선언
    // 클래스가 로드될 때 이른 초기화 방식으로 BoardMemberService 인스턴스를 생성
    private static BoardMemberService instance = new BoardMemberService();
    
    // 데이터베이스 매핑 객체를 저장할 변수 선언
    private BoardMemberMapper mapper;

    // 생성자 - 외부에서의 직접적인 호출을 방지하기 위해 private으로 선언해야 하지만 현재 public으로 선언되어 있음
    // DBManager를 통해 데이터베이스 세션을 얻고, BoardMemberMapper 객체를 초기화함
    public BoardMemberService() {
        mapper = DBManager.getInstance().getSession().getMapper(BoardMemberMapper.class);
    }

    // 싱글턴(Singleton) 인스턴스를 반환하는 메서드
    // 이미 생성된 인스턴스가 있으면 그대로 반환하고, 없으면 새로 생성함
    // 하지만 이 코드는 instance 변수가 초기화되어 있으므로 instance == null 조건은 불필요
    public static BoardMemberService getInstance() {
        if(instance == null) // 이 조건은 절대 true가 될 수 없음 (이른 초기화 방식으로 이미 인스턴스가 생성됨)
            instance = new BoardMemberService();
        return instance;
    }

	public List<BoardMemberDTO> selectNameMember(String name) {
		return mapper.selectNameMember(name);
	}

	public int deleteMember(String id) {
		// TODO Auto-generated method stub
		return mapper.deleteMember(id);
	}


} 
