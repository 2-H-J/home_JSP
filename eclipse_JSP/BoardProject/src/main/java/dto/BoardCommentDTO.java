package dto;

public class BoardCommentDTO {
	private int cno;
	private int bno;
	private String id;
	private String content;
	private String nickName;
	private String cdate;
	private String clike;
	private String chate;
	
	public BoardCommentDTO() {
		
	}

	public BoardCommentDTO(int bno, String id, String content) {
		super();
		this.bno = bno;
		this.id = id;
		this.content = content;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getClike() {
		return clike;
	}

	public void setClike(String clike) {
		this.clike = clike;
	}

	public String getChate() {
		return chate;
	}

	public void setChate(String chate) {
		this.chate = chate;
	}

	@Override
	public String toString() {
		return "BoardCommentDTO [cno=" + cno + ", bno=" + bno + ", id=" + id + ", content=" + content + ", nickName="
				+ nickName + ", cdate=" + cdate + ", clike=" + clike + ", chate=" + chate + "]";
	}

	
}
