package dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * BoardsDTO 클래스는 게시판 데이터베이스의 게시글 정보를 캡슐화하는 데이터 전송 객체(Data Transfer Object)입니다.
 */
public class BoardsDTO {

	// 게시글 번호 (Primary Key)
	private int postNumber;

	// 작성자 번호 (Foreign Key)
	private int userNumber;

	// 게시글 제목
	private String title;

	// 게시글 내용
	private String description;

	// 게시글 생성 시간
	private Timestamp createTime;

	// 게시글 수정 시간
	private Timestamp updateTime;
	
	//카테고리
	private String tag;

	// UsersDTO 연동
	private String nickName;

	//조회수
	private int bcount;
	
	//좋아요
	private int blike;
	
	//댓글 수
	private int ccount;
	/**
	 * 기본 생성자
	 */
	public BoardsDTO() {
	}

	/**
	 * 모든 필드를 초기화하는 생성자
	 * 
	 * @param postNumber  게시글 번호
	 * @param userNumber  작성자 번호
	 * @param title       게시글 제목
	 * @param description 게시글 내용
	 * @param createTime  게시글 생성 시간
	 * @param updateTime  게시글 수정 시간
	 */
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

	/**
	 * 게시글 번호, 작성자 번호, 제목, 내용을 초기화하는 생성자
	 * 
	 * @param postNumber  게시글 번호
	 * @param userNumber  작성자 번호
	 * @param title       게시글 제목
	 * @param description 게시글 내용
	 */
	public BoardsDTO(int postNumber, int userNumber, String title, String description) {
		super();
		this.postNumber = postNumber;
		this.userNumber = userNumber;
		this.title = title;
		this.description = description;
	}

	/**
	 * 작성자 번호, 제목, 내용을 초기화하는 생성자
	 * 
	 * @param userNumber  작성자 번호
	 * @param title       게시글 제목
	 * @param description 게시글 내용
	 */
	public BoardsDTO(int userNumber, String title, String description, String tag) {
		super();
		this.userNumber = userNumber;
		this.title = title;
		this.description = description;
		this.tag = tag;
	}
	
	public BoardsDTO(int postNumber, int userNumber, String title, String description, Timestamp createTime,
			Timestamp updateTime, String tag, String nickName, int bcount, int blike, int ccount) {
		super();
		this.postNumber = postNumber;
		this.userNumber = userNumber;
		this.title = title;
		this.description = description;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.tag = tag;
		this.nickName = nickName;
		this.bcount = bcount;
		this.blike = blike;
		this.ccount = ccount;
	}

	// Getter와 Setter 메서드
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
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public String getFormattedCreateTime() {
		if (createTime != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(createTime);
		}
		return null;
	}

	public String getFormattedUpdateTime() {
		if (updateTime != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(updateTime);
		}
		return null;
	}
	
	public int getCcount() {
		return ccount;
	}

	public void setCcount(int ccount) {
		this.ccount = ccount;
	}

	/**
	 * 객체의 정보를 문자열로 반환
	 * 
	 * @return 게시글 객체를 문자열로 표현
	 */
	@Override
	public String toString() {
		return "BoardsDTO{" + "postNumber=" + postNumber + ", userNumber=" + userNumber + ", title='" + title + '\''
				+ ", description='" + description + '\'' + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ '}';
	}
}