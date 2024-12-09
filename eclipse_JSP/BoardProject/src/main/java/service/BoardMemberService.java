package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import config.DBManager;
import dto.BoardMemberDTO;
import mapper.BoardMemberMapper;

public class BoardMemberService {
    private static BoardMemberService instance = new BoardMemberService();
    private BoardMemberMapper mapper;

    private BoardMemberService() {
        mapper = DBManager.getInstance().getSession().getMapper(BoardMemberMapper.class);
        System.out.println("BoardMemberService: Mapper 초기화 완료");
    }

    public static BoardMemberService getInstance() {
        if (instance == null) {
            instance = new BoardMemberService();
        }
        return instance;
    }

    public List<BoardMemberDTO> selectNameMember(String name) {
        System.out.println("BoardMemberService: 이름으로 멤버 조회 요청. 입력된 이름: " + name);
        List<BoardMemberDTO> members = mapper.selectNameMember(name);
        System.out.println("조회된 멤버 수: " + (members != null ? members.size() : 0));
        return members;
    }

    public int deleteMember(String id) {
        System.out.println("BoardMemberService: 멤버 삭제 요청. 입력된 ID: " + id);
        int result = mapper.deleteMember(id);
        System.out.println("삭제 결과: " + (result > 0 ? "성공" : "실패"));
        return result;
    }

    public List<BoardMemberDTO> selectAllMember() {
        System.out.println("BoardMemberService: 모든 멤버 조회 요청");
        List<BoardMemberDTO> members = mapper.selectAllMember();
        System.out.println("조회된 멤버 수: " + (members != null ? members.size() : 0));
        return members;
    }

    public int selectIdMember(String id) {
        System.out.println("BoardMemberService: ID 중복 확인 요청. 입력된 ID: " + id);
        int count = mapper.selectIdMember(id);
        System.out.println("ID 중복 확인 결과: " + (count > 0 ? "중복 있음" : "중복 없음"));
        return count;
    }

    public BoardMemberDTO login(String id, String password) {
        System.out.println("BoardMemberService: 로그인 요청. 입력된 ID: " + id);
        try {
            Map<String, String> map = new HashMap<>();
            map.put("id", id);
            map.put("password", password);
            BoardMemberDTO dto = mapper.findMemberByIdAndPassword(map);
            System.out.println("로그인 결과: " + (dto != null ? "성공: " + dto : "실패: 사용자 정보 없음"));
            return dto;
        } catch (Exception e) {
            System.out.println("로그인 처리 중 예외 발생: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
