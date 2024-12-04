package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.BoardMemberDTO;
import oracle.jdbc.pool.OracleDataSource;

public class BoardMemberDAO {
	private static BoardMemberDAO instance = new BoardMemberDAO();
	private OracleDataSource ods;
	
	private BoardMemberDAO() {
		try {
			ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@nam3324.synology.me:32800/xe");
			ods.setUser("c##quest");
			ods.setPassword("tiger");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static BoardMemberDAO getInstance() {
		if(instance == null)
			instance = new BoardMemberDAO();
		return instance;
	}

	public ArrayList<BoardMemberDTO> selectAllMember() {
		ArrayList<BoardMemberDTO> list = new ArrayList<BoardMemberDTO>();
		//1. SQL 문작성
		String sql = "select * from board_member";
		//2. Connection 받아옴
		//3. PreparedStatement 생성
		try (Connection conn = ods.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);){
			//4. SQL문 실행
			try(ResultSet rs = pstmt.executeQuery();){
				//5. 결과처리
				while(rs.next()) {
					BoardMemberDTO dto = new BoardMemberDTO();
					dto.setId(rs.getString("id"));
					dto.setPassword(rs.getString("password"));
					dto.setNickName(rs.getString("nickname"));
					dto.setUserName(rs.getString("username"));
					
					list.add(dto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		//6. 결과값 리턴
		return list;
	}

	
}











