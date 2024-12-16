package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dto.RegionDTO;

public interface RegionMapper {

	List<RegionDTO> selectAllRegion();

	RegionDTO selectRegionByRegionNumber(int regionNumber); // 매개변수 소문자로 수정

	int deleteRegionByRegionNumber(int regionNumber); // 매개변수 소문자로 수정

	int insertRegion(RegionDTO dto);

	int updateRegion(RegionDTO region); // 매개변수 소문자로 수정

	List<RegionDTO> selectPagedRegions(@Param("startRow") int startRow, @Param("endRow") int endRow);

	int getTotalRegionCount();

}
