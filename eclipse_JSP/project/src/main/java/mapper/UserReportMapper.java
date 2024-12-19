package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dto.UserReportDTO;

public interface UserReportMapper {

	 List<UserReportDTO> selectAllReports();
	    void insertReport(UserReportDTO dto);
	    void updateReportStatus(@Param("reportNumber") int reportNumber,
                @Param("status") String status,
                @Param("adminId") int adminId);
}
	
