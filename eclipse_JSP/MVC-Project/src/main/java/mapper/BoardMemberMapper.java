package mapper;

import java.util.List;

import dto.BoardMemberDTO;

public interface BoardMemberMapper {
    // Mapper에서 SQL 쿼리를 실행하기 위해 사용되는 메서드들을 정의

    // 모든 멤버 정보를 조회하는 메서드
    // 데이터베이스에서 board_member 테이블의 모든 멤버를 조회하고 그 결과를 BoardMemberDTO 객체들의 리스트로 반환함
    List<BoardMemberDTO> selectAllMember();

    // 새로운 멤버를 추가하는 메서드
    // BoardMemberDTO 객체를 인자로 받아서 해당 멤버 정보를 데이터베이스에 삽입
    // 삽입 성공 시 삽입된 행의 수를 반환 (일반적으로 1)
    int insertMember(BoardMemberDTO dto);

    // 특정 이름을 포함한 멤버 정보를 조회하는 메서드
    // name을 인자로 받아 해당 이름이 포함된 멤버 정보를 조회하고 결과를 BoardMemberDTO 리스트로 반환
    List<BoardMemberDTO> selectNameMember(String name);

    // 특정 ID를 가진 멤버를 삭제하는 메서드
    // id를 인자로 받아 해당 ID의 멤버를 삭제하고 삭제된 행의 수를 반환
    int deleteMember(String id);

    // 특정 ID를 가진 멤버의 개수를 조회하는 메서드
    // 중복 확인 등을 위해 id를 받아 해당 멤버의 개수를 반환함 (0 또는 1이 주로 반환됨)
    int selectIdMember(String id);
}
