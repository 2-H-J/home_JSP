package dto;

import java.sql.Timestamp;

/**
 * BoardsDTO
 * - 게시판 정보를 담는 데이터 전송 객체(Data Transfer Object)
 * - 게시물 번호, 사용자 번호, 제목, 내용, 생성 및 수정 시간을 포함
 */
public class BoardsDTO {
    // 게시물 번호 (Primary Key)
    private int postNumber;
    // 사용자 번호 (게시물을 작성한 사용자 ID)
    private int userNumber;
    // 게시물 제목
    private String title;
    // 게시물 내용
    private String description;
    // 게시물 생성 시간
    private Timestamp createTime;
    // 게시물 수정 시간
    private Timestamp updateTime;

    /**
     * 기본 생성자
     * - 객체 생성 시 초기화 없이 생성 가능
     */
    public BoardsDTO() {
    }

    /**
     * 매개변수를 포함한 생성자
     * - 모든 필드를 초기화할 때 사용
     * 
     * @param postNumber 게시물 번호
     * @param userNumber 사용자 번호
     * @param title      제목
     * @param description 내용
     * @param createTime  생성 시간
     * @param updateTime  수정 시간
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

    // Getter 및 Setter 메서드
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

    /**
     * toString 메서드
     * - 객체 정보를 문자열로 반환
     * 
     * @return 객체의 필드 값들을 문자열 형태로 반환
     */
    @Override
    public String toString() {
        return "BoardsDTO{" +
                "postNumber=" + postNumber +
                ", userNumber=" + userNumber +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
