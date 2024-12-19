package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import config.DBManager;
import dto.UserReportDTO;
import dto.UsersDTO;
import mapper.UserReportMapper;
import mapper.UsersMapper;

public class reportService {

    
	 /**
     * 모든 신고 목록을 조회합니다.
     * 
     * @return 신고 목록 (List<UserReportDTO>)
     */
    public List<UserReportDTO> getAllReports() {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            System.out.println("[reportService] getAllReports: 세션 생성 완료");
            UserReportMapper mapper = session.getMapper(UserReportMapper.class);
            System.out.println("[reportService] getAllReports: Mapper 연결 완료");

            List<UserReportDTO> reports = mapper.selectAllReports();
            System.out.println("[reportService] getAllReports: 쿼리 실행 완료, 결과 크기=" + (reports != null ? reports.size() : "null"));

            if (reports != null) {
                for (UserReportDTO report : reports) {
                    System.out.println("[reportService] 신고 번호=" + report.getReportNumber() + ", 사용자 번호=" + report.getUserNumber());
                }
            }

            return reports;
        } catch (Exception e) {
            System.out.println("[reportService] getAllReports: 예외 발생 - " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void createReport(int userNumber, int postNumber, String reason) {
        UserReportDTO dto = new UserReportDTO();
        dto.setUserNumber(userNumber);
        dto.setPostNumber(postNumber);
        dto.setUserReportReason(reason);

        try (SqlSession session = DBManager.getInstance().getSession()) {
            System.out.println("[reportService] createReport: 세션 생성 완료");
            UserReportMapper mapper = session.getMapper(UserReportMapper.class);
            System.out.println("[reportService] createReport: Mapper 연결 완료");

            mapper.insertReport(dto);
            session.commit(); // 변경 사항을 커밋
            System.out.println("[reportService] createReport: 신고 생성 성공 - 사용자 번호=" + userNumber + ", 게시글 번호=" + postNumber);
        } catch (Exception e) {
            System.out.println("[reportService] createReport: 예외 발생 - " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<UsersDTO> selectAllUsers() {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            System.out.println("[reportService] selectAllUsers: 세션 생성 완료");
            UsersMapper mapper = session.getMapper(UsersMapper.class);
            System.out.println("[reportService] selectAllUsers: Mapper 연결 완료");

            List<UsersDTO> users = mapper.selectAllUsersExcludeAdmin();
            System.out.println("[reportService] selectAllUsers: 쿼리 실행 완료, 결과 크기=" + (users != null ? users.size() : "null"));

            return users;
        } catch (Exception e) {
            System.out.println("[reportService] selectAllUsers: 예외 발생 - " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void approveReport(int reportNumber, int adminId) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            System.out.println("[reportService] approveReport: 세션 생성 완료");
            UserReportMapper mapper = session.getMapper(UserReportMapper.class);
            System.out.println("[reportService] approveReport: Mapper 연결 완료");

            mapper.updateReportStatus(reportNumber, "APPROVED", adminId);
            session.commit();
            System.out.println("[reportService] approveReport: 신고 승인 성공 - 신고 번호=" + reportNumber + ", 관리자 ID=" + adminId);
        } catch (Exception e) {
            System.out.println("[reportService] approveReport: 예외 발생 - " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void rejectReport(int reportNumber, int adminId) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            System.out.println("[reportService] rejectReport: 세션 생성 완료");
            UserReportMapper mapper = session.getMapper(UserReportMapper.class);
            System.out.println("[reportService] rejectReport: Mapper 연결 완료");

            mapper.updateReportStatus(reportNumber, "REJECTED", adminId);
            session.commit();
            System.out.println("[reportService] rejectReport: 신고 거부 성공 - 신고 번호=" + reportNumber + ", 관리자 ID=" + adminId);
        } catch (Exception e) {
            System.out.println("[reportService] rejectReport: 예외 발생 - " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean updateReportStatus(int reportNumber, String status, int adminId) {
        try (SqlSession session = DBManager.getInstance().getSession()) {
            System.out.println("[reportService] updateReportStatus: 세션 생성 완료");
            UserReportMapper mapper = session.getMapper(UserReportMapper.class);
            System.out.println("[reportService] updateReportStatus: Mapper 연결 완료");

            // 파라미터 전달 및 상태 업데이트
            mapper.updateReportStatus(reportNumber, status, adminId);
            session.commit(); // 변경 사항을 커밋
            System.out.println("[reportService] updateReportStatus: 상태 업데이트 성공 - 신고 번호=" + reportNumber + ", 상태=" + status + ", 관리자 ID=" + adminId);

            return true; // 성공 시 true 반환
        } catch (Exception e) {
            System.out.println("[reportService] updateReportStatus: 예외 발생 - " + e.getMessage());
            e.printStackTrace();
            return false; // 실패 시 false 반환
        }
    }
}
