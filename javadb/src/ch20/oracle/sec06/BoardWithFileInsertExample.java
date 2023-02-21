package ch20.oracle.sec06;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//board table에 data를 저장하는 예시
public class BoardWithFileInsertExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection conn = null;
		try {
			//jdbc driver register
			Class.forName("oracle.jdbc.OracleDriver");
			
			//connect
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "owen", "12345");
			
			System.out.println("connection successful");
		
			//매개변수화된 sql문 작성
		String sql = "" + 
				"insert into boards (bno, btitle, bcontent, bwriter, bdate, bfilename, bfiledata) " +
				"values (seq_bno.nextval, ?, ?, ?, sysdate, ?, ?)";
		
		//PreparedStatement 얻기 및 값 지정
		PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"bno"});
		pstmt.setString(1, "눈 오는 날");
		pstmt.setString(2, "함박눈이 내려요");
		pstmt.setString(3, "winter");
		pstmt.setString(4, "snow.jpg");
		pstmt.setBlob(5, new FileInputStream("src/ch20/oracle/sec06/snow.jpg"));
		
		//sql문 실행
		int rows = pstmt.executeUpdate();
		System.out.println("저장된 행 수: " + rows);
		
		//bno 값 얻기
		if(rows == 1) {
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				int bno = rs.getInt(1);
				System.out.println("저장된 bno: " + bno);
			}
			rs.close();
		}
		//close PreparedStatement
			pstmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					//disconnect
					conn.close();
				} catch(SQLException e) {
					
				}
			}
		}
		
		
		
	}

}
