package service;

import java.util.List;

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

	public List<RegionDTO> selectAllRegion() {
		return mapper.selectAllRegion();
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

}
