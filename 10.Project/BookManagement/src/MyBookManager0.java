// 책 등록 추가

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MyBookManager0 {
	
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
		MyBookManager0 mbm = new MyBookManager0();
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
				addNumber();
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
		System.out.println("4. 종료");
		System.out.print("선택 : ");
	}

	public void addNumber() {
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