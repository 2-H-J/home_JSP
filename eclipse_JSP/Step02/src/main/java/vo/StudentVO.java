package vo;


public class StudentVO {
	// 학생정보 저장할 필드 선언, 기본 생성자, 전체 필드 초기화하는 생성자, set/get, toString
	
//	필드
	private String studentNo;
	private String studentName;
	private String majorName;
	private double studentScore;
	
	
//	기본 생성자 Alt+Shift+s+c
	public StudentVO() {
		super();
		// TODO Auto-generated constructor stub
	}

//	초기화
	public StudentVO(String studentNo, String studentName, String majorName, Double studentScore) {
		super();
		this.studentNo = studentNo;
		this.studentName = studentName;
		this.majorName = majorName;
		this.studentScore = studentScore;
	}
	
//	get/set
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public double getStudentScore() {
		return studentScore;
	}
	public void setStudentScore(Double studentScore) {
		this.studentScore = studentScore;
	}

//	toString
	@Override
	public String toString() {
		return "등록한 학생정보 - 학번 : " + studentNo + ", 학생이름 : " + studentName + ", 학과 : " + majorName
				+ ", 평점 : " + studentScore;
	}
	
	
	
}
