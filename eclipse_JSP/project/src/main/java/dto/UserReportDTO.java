package dto;

public class UserReportDTO {
    private int reportNumber; // 신고 번호
    private int userNumber;   // 사용자 번호
    private int postNumber;   // 게시물 번호
    private String userReportReason; // 신고 사유
    private String reportStatus;     // 신고 상태
    private String createTime;       // 생성 시간
    private int handledByAdminId; // 처리한 관리자 ID
    private String postWriterNickname; // 게시글 작성자 닉네임
    private String userNickname;      // 신고자 닉네임 (추가된 필드)

    // Getters and Setters
    public int getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(int reportNumber) {
        this.reportNumber = reportNumber;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public int getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(int postNumber) {
        this.postNumber = postNumber;
    }

    public String getUserReportReason() {
        return userReportReason;
    }

    public void setUserReportReason(String userReportReason) {
        this.userReportReason = userReportReason;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getHandledByAdminId() {
        return handledByAdminId;
    }

    public void setHandledByAdminId(int handledByAdminId) {
        this.handledByAdminId = handledByAdminId;
    }

    public String getPostWriterNickname() {
        return postWriterNickname;
    }

    public void setPostWriterNickname(String postWriterNickname) {
        this.postWriterNickname = postWriterNickname;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    // Constructor for convenience
    public UserReportDTO(int reportNumber, int userNumber, int postNumber, String userReportReason,
                         String reportStatus, String createTime, int handledByAdminId, String postWriterNickname, String userNickname) {
        this.reportNumber = reportNumber;
        this.userNumber = userNumber;
        this.postNumber = postNumber;
        this.userReportReason = userReportReason;
        this.reportStatus = reportStatus;
        this.createTime = createTime;
        this.handledByAdminId = handledByAdminId;
        this.postWriterNickname = postWriterNickname;
        this.userNickname = userNickname; // 추가된 필드 초기화
    }

    // Default constructor
    public UserReportDTO() {
    }

    @Override
    public String toString() {
        return "UserReportDTO{" +
                "reportNumber=" + reportNumber +
                ", userNumber=" + userNumber +
                ", postNumber=" + postNumber +
                ", userReportReason='" + userReportReason + '\'' +
                ", reportStatus='" + reportStatus + '\'' +
                ", createTime='" + createTime + '\'' +
                ", handledByAdminId=" + handledByAdminId +
                ", postWriterNickname='" + postWriterNickname + '\'' +
                ", userNickname='" + userNickname + '\'' +  // 추가된 필드 출력
                '}';
    }
}