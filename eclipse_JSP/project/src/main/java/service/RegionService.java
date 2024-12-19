package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import config.DBManager;
import dto.RegionDTO;
import mapper.RegionMapper;

public class RegionService {
	private static RegionService instance = new RegionService();
	private RegionMapper mapper;

	private RegionService() {
		mapper = DBManager.getInstance().getSession().getMapper(RegionMapper.class);
	}

	public static RegionService getInstance() {
		return instance;
	}

	public List<RegionDTO> selectAllRegion(int page, int pageSize, int offset) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("page", page);
	    params.put("pageSize", pageSize);
	    params.put("offset", offset);

	    return mapper.selectAllRegion(params); 
	}


	public RegionDTO selectRegionByRegionNumber(int regionNumber) {
		return mapper.selectRegionByRegionNumber(regionNumber);
	}

	public int deleteRegionByRegionNumber(int regionNumber) {
		return mapper.deleteRegionByRegionNumber(regionNumber);
	}

	public int insertRegion(RegionDTO dto) {
		return mapper.insertRegion(dto);
	}

	public int updateRegion(RegionDTO region) {
		return mapper.updateRegion(region);
	}

	public int totalRegionCount() {
		return mapper.totalRegionCount();
	}

}
