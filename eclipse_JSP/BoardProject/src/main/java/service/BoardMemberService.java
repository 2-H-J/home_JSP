package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import config.DBManager;
import dto.BoardMemberDTO;
import mapper.BoardMemberMapper;

/**
 * BoardMemberService 클래스는 BoardMember와 관련된 비즈니스 로직을 처리하는 클래스입니다.
 * 싱글톤 패턴으로 설계되어 애플리케이션 전역에서 하나의 인스턴스만 사용됩니다.
 */
public class BoardMemberService {

    // BoardMemberService의 단일 인스턴스
    private static BoardMemberService instance = new BoardMemberService();
    private BoardMemberMapper mapper; // BoardMemberMapper 인터페이스를 사용하여 데이터베이스 작업 수행

    /**
     * private 생성자: 외부에서의 객체 생성을 방지하고,
     * DBManager를 통해 BoardMemberMapper를 초기화합니다.
     */
    private BoardMemberService() {
        mapper = DBManager.getInstance().getSession().getMapper(BoardMemberMapper.class);
        System.out.println("BoardMemberService: Mapper 초기화 완료");
    }

    /**
     * BoardMemberService의 단일 인스턴스를 반환합니다.
     * 
     * @return BoardMemberService 인스턴스
     */
    public static BoardMemberService getInstance() {
        if (instance == null) {
            instance = new BoardMemberService();
        }
        return instance;
    }

    /**
     * 특정 이름을 가진 멤버를 조회합니다.
     * 
     * @param name 조회할 이름
     * @return 이름에 해당하는 멤버 목록
     */
    public List<BoardMemberDTO> selectNameMember(String name) {
        System.out.println("BoardMemberService: 이름으로 멤버 조회 요청. 입력된 이름: " + name);
        List<BoardMemberDTO> members = mapper.selectNameMember(name);
        System.out.println("조회된 멤버 수: " + (members != null ? members.size() : 0));
        return members;
    }

    /**
     * 특정 ID를 가진 멤버를 삭제합니다.
     * 
     * @param id 삭제할 멤버의 ID
     * @return 삭제 성공 여부 (성공: 1, 실패: 0)
     */
    public int deleteMember(String id) {
        System.out.println("BoardMemberService: 멤버 삭제 요청. 입력된 ID: " + id);
        int result = mapper.deleteMember(id);
        System.out.println("삭제 결과: " + (result > 0 ? "성공" : "실패"));
        return result;
    }

    /**
     * 모든 멤버를 조회합니다.
     * 
     * @return 모든 멤버의 목록
     */
    public List<BoardMemberDTO> selectAllMember() {
        System.out.println("BoardMemberService: 모든 멤버 조회 요청");
        List<BoardMemberDTO> members = mapper.selectAllMember();
        System.out.println("조회된 멤버 수: " + (members != null ? members.size() : 0));
        return members;
    }

    /**
     * 특정 ID의 중복 여부를 확인합니다.
     * 
     * @param id 중복 확인할 ID
     * @return 중복 여부 (중복: 1 이상, 중복 아님: 0)
     */
    public int selectIdMember(String id) {
        System.out.println("BoardMemberService: ID 중복 확인 요청. 입력된 ID: " + id);
        int count = mapper.selectIdMember(id);
        System.out.println("ID 중복 확인 결과: " + (count > 0 ? "중복 있음" : "중복 없음"));
        return count;
    }

    /**
     * 사용자 ID와 비밀번호를 사용하여 로그인합니다.
     * 
     * @param id       사용자 ID
     * @param password 사용자 비밀번호
     * @return 로그인 성공 시 사용자 정보(BoardMemberDTO), 실패 시 null
     */
    public BoardMemberDTO login(String id, String password) {
        System.out.println("BoardMemberService: 로그인 요청. 입력된 ID: " + id);
        try {
            // ID와 비밀번호를 매퍼에 전달하기 위한 Map 생성
            Map<String, String> map = new HashMap<>();
            map.put("id", id);
            map.put("password", password);

            // Mapper를 통해 사용자 정보 조회
            BoardMemberDTO dto = mapper.findMemberByIdAndPassword(map);
            System.out.println("로그인 결과: " + (dto != null ? "성공: " + dto : "실패: 사용자 정보 없음"));
            return dto;
        } catch (Exception e) {
            // 예외 발생 시 로그 출력
            System.out.println("로그인 처리 중 예외 발생: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
