package dto;

import java.sql.Timestamp;

public class UsersDTO {
    private int userNumber;       // USER_NUMBER 컬럼
    private String loginId;       // LOGIN_ID 컬럼
    private String nickName;      // NICK_NAME 컬럼
    private String password;      // PASSWORD 컬럼 (CLOB는 String으로 매핑)
    private Timestamp createTime;  // CREATE_TIME 컬럼
    private Timestamp updateTime;  // UPDATE_TIME 컬럼
    private Timestamp pwUpdateTime; // PW_UPDATE_TIME 컬럼
    private String userName;      // USER_NAME 컬럼
    private String userEmail;     // USER_EMAIL 컬럼
    
	public UsersDTO() {
		
	}

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
