package dto;

import java.sql.Timestamp;

/**
 * UsersDTO
 * - 사용자 정보를 담는 데이터 전송 객체(Data Transfer Object)
 * - 사용자 번호, 로그인 ID, 닉네임, 비밀번호, 생성/수정 시간 등 사용자 관련 정보를 포함
 */
public class UsersDTO {
    // 사용자 번호 (Primary Key)
    private int userNumber;
    // 로그인 ID
    private String loginId;
    // 닉네임
    private String nickName;
    // 비밀번호
    private String password;
    // 계정 생성 시간
    private Timestamp createTime;
    // 계정 수정 시간
    private Timestamp updateTime;
    // 비밀번호 마지막 수정 시간
    private Timestamp pwUpdateTime;
    // 사용자 이름
    private String userName;
    // 사용자 이메일
    private String userEmail;

    /**
     * 기본 생성자
     * - 객체 생성 시 초기화 없이 생성 가능
     */
    public UsersDTO() {
    }

    /**
     * 매개변수를 포함한 생성자
     * - 모든 필드를 초기화할 때 사용
     * 
     * @param userNumber   사용자 번호
     * @param loginId      로그인 ID
     * @param nickName     닉네임
     * @param password     비밀번호
     * @param createTime   생성 시간
     * @param updateTime   수정 시간
     * @param pwUpdateTime 비밀번호 수정 시간
     * @param userName     사용자 이름
     * @param userEmail    사용자 이메일
     */
    public UsersDTO(int userNumber, String loginId, String nickName, String password, Timestamp createTime,
                    Timestamp updateTime, Timestamp pwUpdateTime, String userName, String userEmail) {
        super();
        this.userNumber = userNumber;
        this.loginId = loginId;
        this.nickName = nickName;
        this.password = password;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.pwUpdateTime = pwUpdateTime;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    // Getter 및 Setter 메서드
    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Timestamp getPwUpdateTime() {
        return pwUpdateTime;
    }

    public void setPwUpdateTime(Timestamp pwUpdateTime) {
        this.pwUpdateTime = pwUpdateTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
