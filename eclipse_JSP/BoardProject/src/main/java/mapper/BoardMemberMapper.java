package mapper;

import java.util.List;
import java.util.Map;

import dto.BoardMemberDTO;

/**
 * BoardMemberMapper 인터페이스는 데이터베이스의 board_member 테이블에 대한
 * SQL 쿼리를 실행하기 위해 MyBatis와 연결되는 메서드들을 정의합니다.
 * 각 메서드는 데이터베이스 작업에 필요한 매개변수와 반환 타입을 제공합니다.
 */
public interface BoardMemberMapper {

    /**
     * 모든 멤버 정보를 조회하는 메서드.
     * 데이터베이스에서 board_member 테이블의 모든 멤버 정보를 가져와 
     * BoardMemberDTO 객체들의 리스트로 반환합니다.
     *
     * @return BoardMemberDTO 객체들의 리스트 (모든 멤버 정보)
     */
    List<BoardMemberDTO> selectAllMember();

    /**
     * 새로운 멤버를 추가하는 메서드.
     * BoardMemberDTO 객체를 데이터베이스에 삽입하여 새로운 멤버를 추가합니다.
     *
     * @param dto 추가할 멤버의 정보를 담은 BoardMemberDTO 객체
     * @return 삽입된 행의 개수 (1이면 성공, 0이면 실패)
     */
    int insertMember(BoardMemberDTO dto);

    /**
     * 특정 이름을 포함한 멤버 정보를 조회하는 메서드.
     * 데이터베이스에서 name 값이 포함된 멤버를 검색하고, 결과를 BoardMemberDTO 리스트로 반환합니다.
     *
     * @param name 검색할 멤버의 이름
     * @return BoardMemberDTO 객체들의 리스트 (name이 포함된 멤버 정보)
     */
    List<BoardMemberDTO> selectNameMember(String name);

    /**
     * 특정 ID를 가진 멤버를 삭제하는 메서드.
     * 데이터베이스에서 해당 ID의 멤버를 삭제합니다.
     *
     * @param id 삭제할 멤버의 ID
     * @return 삭제된 행의 개수 (1이면 성공, 0이면 실패)
     */
    int deleteMember(String id);

    /**
     * 특정 ID를 가진 멤버의 개수를 조회하는 메서드.
     * ID 중복 확인 또는 특정 ID가 존재하는지 확인하기 위해 사용됩니다.
     *
     * @param id 검색할 멤버의 ID
     * @return 해당 ID를 가진 멤버의 개수 (0 또는 1이 주로 반환됨)
     */
    int selectIdMember(String id);

    /**
     * ID와 비밀번호를 기반으로 멤버를 조회하는 메서드.
     * 로그인 요청 시 사용되며, ID와 비밀번호가 일치하는 멤버 정보를 반환합니다.
     *
     * @param map ID와 비밀번호를 담은 Map 객체 ("id"와 "password" 키를 사용)
     * @return ID와 비밀번호가 일치하는 멤버의 정보 (BoardMemberDTO 객체)
     */
    BoardMemberDTO findMemberByIdAndPassword(Map<String, String> map);
}
