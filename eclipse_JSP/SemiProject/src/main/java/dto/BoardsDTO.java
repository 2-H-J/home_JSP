package dto;

import java.sql.Timestamp;

public class BoardsDTO {
    private int postNumber;       // 게시물 번호 (POST_NUMBER)
    private int userNumber;       // 사용자 번호 (USER_NUMBER)
    private String title;         // 게시물 제목 (TITLE)
    private String description;   // 게시물 내용 (DESCRIPTION)
    private Timestamp createTime; // 생성 시간 (CREATE_TIME)
    private Timestamp updateTime; // 수정 시간 (UPDATE_TIME)
    private String tag;           // 태그 (TAG)
    private int bcount;           // 조회 수 (BCOUNT)
    private int blike;            // 좋아요 수 (BLIKE)
    
	public BoardsDTO() {
	}

	public BoardsDTO(int postNumber, int userNumber, String title, String description, Timestamp createTime,
			Timestamp updateTime, String tag, int bcount, int blike) {
		super();
		this.postNumber = postNumber;
		this.userNumber = userNumber;
		this.title = title;
		this.description = description;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.tag = tag;
		this.bcount = bcount;
		this.blike = blike;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getBcount() {
		return bcount;
	}

	public void setBcount(int bcount) {
		this.bcount = bcount;
	}

	public int getBlike() {
		return blike;
	}

	public void setBlike(int blike) {
		this.blike = blike;
	}
    
    
}
