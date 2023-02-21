package ch20.oracle.sec08;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//테이블의 데이터 삭제 예시
public class BoardDeleteExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			//jdbc driver register
			Class.forName("oracle.jdbc.OracleDriver");
			//driver 고정
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"owen",
					"12345"
					);
			
			//매개변수화된 sql문 작성
			String sql = "delete from boards where bwriter = ?"; //writer가 누구이냐에 따라 지우게 설정
			
			//PreparedStatement 얻기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "winter"); //writer가 winter인 데이터를 지우도록 설정
			
			//sql문 실행
			int rows = pstmt.executeUpdate();
			System.out.println("삭제된 행 수: "+rows);
			
			//PreparedStatement 닫기
			pstmt.close();
			
		} catch (Exception e) {
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
