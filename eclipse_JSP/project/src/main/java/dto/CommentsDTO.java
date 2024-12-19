package dto;

public class CommentsDTO {
	private int commentNumber;
	private int postNumber;
	private int userNumber;
	private String cDescription;
	private String cmtCreateTime;
	private String cmtUpdateTime;
	private String nickName;
	private int clike;

	public CommentsDTO() {
		super();
	}

	
	public CommentsDTO(int commentNumber, int postNumber, int userNumber, String cDescription, String cmtCreateTime,
			String cmtUpdateTime, String nickName, int clike) {
		super();
		this.commentNumber = commentNumber;
		this.postNumber = postNumber;
		this.userNumber = userNumber;
		this.cDescription = cDescription;
		this.cmtCreateTime = cmtCreateTime;
		this.cmtUpdateTime = cmtUpdateTime;
		this.nickName = nickName;
		this.clike = clike;
	}


	public CommentsDTO(int postNumber, int userNumber, String cDescription) {
		this.postNumber = postNumber;
		this.userNumber = userNumber;
		this.cDescription = cDescription;
	}
	
	public int getCommentNumber() {
		return commentNumber;
	}

	public void setCommentNumber(int commentNumber) {
		this.commentNumber = commentNumber;
	}

	public int getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}

	public int getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	public String getcDescription() {
		return cDescription;
	}

	public void setcDescription(String cDescription) {
		this.cDescription = cDescription;
	}

	public String getCmtCreateTime() {
		return cmtCreateTime;
	}

	public void setCmtCreateTime(String cmtCreateTime) {
		this.cmtCreateTime = cmtCreateTime;
	}

	public String getCmtUpdateTime() {
		return cmtUpdateTime;
	}

	public void setCmtUpdateTime(String cmtUpdateTime) {
		this.cmtUpdateTime = cmtUpdateTime;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public int getClike() {
		return clike;
	}


	public void setClike(int clike) {
		this.clike = clike;
	}
	
	
}
