package dto;

import java.sql.Timestamp;

public class BoardsDTO {
	private int postNumber;
	private int userNumber;
	private String title;
	private String description;
	private Timestamp createTime;
	private Timestamp updateTime;

	public BoardsDTO() {
	}

	public BoardsDTO(int postNumber, int userNumber, String title, String description, Timestamp createTime,
			Timestamp updateTime) {
		super();
		this.postNumber = postNumber;
		this.userNumber = userNumber;
		this.title = title;
		this.description = description;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public BoardsDTO(int postNumber, int userNumber, String title, String description) {
		super();
		this.postNumber = postNumber;
		this.userNumber = userNumber;
		this.title = title;
		this.description = description;
	}

	public BoardsDTO(int userNumber, String title, String description) {
		super();
		this.userNumber = userNumber;
		this.title = title;
		this.description = description;
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

	@Override
	public String toString() {
		return "BoardsDTO{" + "postNumber=" + postNumber + ", userNumber=" + userNumber + ", title='" + title + '\''
				+ ", description='" + description + '\'' + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ '}';
	}
}
