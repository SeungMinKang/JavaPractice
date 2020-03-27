import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Checker {

	Scanner sc = new Scanner(System.in);
	
	// 해당 ID를 가지고 있는 사람이 이미 서버에 있는지 확인하는 메서드
	public boolean CheckIdInServer(String id, PreparedStatement pstmt1, Connection con) {
		boolean exist = false;
		try {
			String sql = "select ID from scott.CHATSERVER"
					   + " where id = ?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, id);
			ResultSet rs = pstmt1.executeQuery();
			if (rs.next()) {
				exist = true;
			} else {
				exist = false;
			}
			rs.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("알 수 없는 에러가 발생했습니다.");
		}
		return exist;
	}
	
}