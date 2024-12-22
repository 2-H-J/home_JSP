package dto;

public class CommentReportDTO {
	private int reportNumber; // 신고 번호
	private int commentNumber; // 댓글 번호
	private int userNumber; // 사용자 번호
	private String reportStatus; // 신고 상태
	private String createTime; // 생성 시간
	private int handledByAdminId; // 처리한 관리자 ID
	private String commentReportReason; // 신고 사유
	private String commentWriterNickname; // 게시글 작성자 닉네임
	private String userNickname; // 신고자 닉네임 (추가된 필드)

	public CommentReportDTO() {
		super();
	}

	public int getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(int reportNumber) {
		this.reportNumber = reportNumber;
	}

	public int getCommentNumber() {
		return commentNumber;
	}

	public void setCommentNumber(int commentNumber) {
		this.commentNumber = commentNumber;
	}

	public int getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
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

	public String getCommentReportReason() {
		return commentReportReason;
	}

	public void setCommentReportReason(String commentReportReason) {
		this.commentReportReason = commentReportReason;
	}

	public String getCommentWriterNickname() {
		return commentWriterNickname;
	}

	public void setCommentWriterNickname(String commentWriterNickname) {
		this.commentWriterNickname = commentWriterNickname;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

}