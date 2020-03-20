// 책 전체 리스트 조회 추가

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MyBookManager2 {

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
		MyBookManager2 mbm = new MyBookManager2();
		mbm.doRun();
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
				addBook();
				break;
			case 2:
				selBook();
				break;
			case 3:
				selAllBook();
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
		System.out.println("1. 도서 등록");
		System.out.println("2. 도서 조회");
		System.out.println("3. 전체 도서 리스트 조회");
		System.out.println("4. 종료");
		System.out.print("선택 : ");
	}

	public void addBook() {
		System.out.print("번호 : ");
		int bno = sc.nextInt();
		sc.nextLine();
		System.out.print("제목 : ");
		String bname = sc.nextLine();
		System.out.print("권수 : ");
		int bstock = sc.nextInt();
		try {
			String sql = "insert into BOOKLIST" + "    values (?, ?, ?)";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setInt(1, bno);
			pstmt1.setString(2, bname);
			pstmt1.setInt(3, bstock);
			pstmt1.executeQuery();
			System.out.println("데이터베이스에 추가되었습니다.");
		} catch (SQLException sqle) {
			System.out.println("데이터베이스 입력 에러입니다.");
		}
	}

	public void selBook() {
		System.out.print("찾는 제목 : ");
		String name = sc.nextLine();
		try {
			String sql = "select * from BOOKLIST" + "    where bname = ?";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setString(1, name);
			ResultSet rs = pstmt2.executeQuery();
			if (rs.next()) {
				System.out.println("번호 : " + rs.getInt("BNO"));
				System.out.println("제목 : " + rs.getString("BNAME"));
				System.out.println("권수 : " + rs.getInt("BSTOCK"));
			} else {
				System.out.println("해당 값이 없습니다.");
			}
			rs.close();
		} catch (SQLException sqle) {
			System.out.println("알 수 없는 에러가 발생했습니다.");
		}
	}
	
	public void selAllBook() {
		try {
			String sql = "select * from BOOKLIST";
			pstmt3 = con.prepareStatement(sql);
			ResultSet rs = pstmt3.executeQuery();
			while (rs.next()) {
				System.out.print("번호: [" + rs.getInt("BNO") + "]\t");
				System.out.print("제목: [" + rs.getString("BNAME") + "]\t");
				System.out.println("권수: [" + rs.getInt("BSTOCK") + "권]");
			}
			rs.close();
		} catch (SQLException sqle) {
			System.out.println("알 수 없는 에러가 발생했습니다.");
		}
	}

	public void connectDatabase() {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}