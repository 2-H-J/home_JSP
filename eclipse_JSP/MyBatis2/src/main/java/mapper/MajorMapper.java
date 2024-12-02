package mapper;

import java.util.List;

import dto.MajorDTO;

public interface MajorMapper {

    // Mapper에서 id 속성 값과 추상 메서드 이름으로 실행할 SQL문을 찾아 실행합니다.
    // 이 메서드는 major 테이블의 모든 데이터를 조회하고, 결과를 MajorDTO 객체의 리스트로 반환합니다.
    List<MajorDTO> selectAllMajor();

	List<MajorDTO> selectMajorList(String[] arr);

}
