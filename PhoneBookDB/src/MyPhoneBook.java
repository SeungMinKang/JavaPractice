
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MyPhoneBook {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	
	Connection con;
	PreparedStatement pstmt1;
	PreparedStatement pstmt2;
	PreparedStatement pstmt3;
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		MyPhoneBook mpb = new MyPhoneBook();
		mpb.doRun();
	}

	public void doRun() {
		connectDatabase();
		int choice;
		while (true) {
			showMenu();
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				addNumber();
				break;
			case 2:
				selNumber();
				break;
			case 3:
				delNumber();
				break;
			case 4:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		}
	}

	public static void showMenu() {
		System.out.println("[메뉴 선택]");
		System.out.println("1. 전화번호 입력");
		System.out.println("2. 전화번호 조회");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 종료");
		System.out.print("선택 : ");
	}

	public void addNumber() {
		System.out.print("이름 : ");
		String name = sc.nextLine();
		System.out.print("전화번호 : ");
		String pnum = sc.nextLine();
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		try {
			String sql = "insert into PhoneBook" + "    values (?, ?, ?)";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, name);
			pstmt1.setString(2, pnum);
			pstmt1.setString(3, email);
			pstmt1.executeQuery();
			System.out.println("데이터베이스에 추가되었습니다.");
		} catch (SQLException sqle) {
			System.out.println("데이터베이스 입력 에러입니다.");
		}
	}

	public void selNumber() {
		System.out.print("조회할 이름 : ");
		String name = sc.nextLine();
		try {
			String sql = "select * from PhoneBook" + "    where name = ?";
			pstmt3 = con.prepareStatement(sql);
			pstmt3.setString(1, name);
			ResultSet rs = pstmt3.executeQuery();
			if (rs.next()) {
				System.out.println("이름 : " + rs.getString("NAME"));
				System.out.println("전화번호 : " + rs.getString("PHONENUMBER"));
				if (rs.getString("EMAIL") == null || rs.getString("EMAIL").equals("")) {
					System.out.println("해당 값이 없습니다.");
				} else {
					System.out.println("이메일 : " + rs.getString("EMAIL"));
				}
			} else {
				System.out.println("해당 값이 없습니다.");
			}
			rs.close();
		} catch (SQLException sqle) {
			System.out.println("알 수 없는 에러가 발생했습니다.");
		} 
	}

	public void delNumber() {
		System.out.print("삭제할 이름 : ");
		String name = sc.nextLine();
		try {
			String sql = "delete PhoneBook"
					   + " where name = ?";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setString(1, name);
			pstmt2.executeQuery();
			System.out.println("삭제가 완료되었습니다.");
		} catch (SQLException sqle) {
			System.out.println("삭제 에러가 발생했습니다.");
		}
	}
	
	public void connectDatabase() {
		try {
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"scott",
					"tiger");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}