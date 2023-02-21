package ch20.oracle.sec05;

import java.sql.Connection;
import java.sql.DriverManager;

//연결 예시
public class ConnectionExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			//jdbc driver register
			Class.forName("oracle.jdbc.OracleDriver");
					
			//connect
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "owen", "12345");
			
			System.out.println("connection successful");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				//disconnect
				conn.close();
				System.out.println("disconnected..");
			} catch (Exception e2) {
				
			}
		}

	}

}
