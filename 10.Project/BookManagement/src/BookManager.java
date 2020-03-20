
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookManager {

	// 실행전 jdbc 드라이버를 로드
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	Connection con;
	PreparedStatement pstmt0;
	PreparedStatement pstmt1;
	PreparedStatement pstmt2;
	PreparedStatement pstmt3;
	PreparedStatement pstmt4;
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		BookManager bm = new BookManager();
		bm.doRun();
	}

	public void doRun() {
		connectDatabase(); // 실행과 동시에 데이터 베이스에 연결
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
				delBook();
				break;
			case 5:
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
		System.out.println("1. 책 등록");
		System.out.println("2. 조회");
		System.out.println("3. 전체 리스트 조회");
		System.out.println("4. 낡은 책 버리기");
		System.out.println("5. 종료");
		System.out.print("선택 : ");
	}

	// 입력한 책의 번호가 중복된 것인지 확인하는 메서드
	public void checkDuplicate(int bno, String bname) {
		try {
			String sql = "select BNO, BNAME from BOOKLIST";
			pstmt0 = con.prepareStatement(sql);
			ResultSet rs = pstmt0.executeQuery();
			while(rs.next()) {
				if(rs.getInt("BNO") == bno && !rs.getString("BNAME").equals(bname)) {
					System.out.println("같은 번호에 다른 이름의 책이 있습니다!");
				}
			}
		} catch (SQLException sqle) {
			System.out.println("데이터베이스 입력 에러입니다.");
		}
	}
	
	public void addBook() {
		System.out.print("번호 : ");
		int bno = sc.nextInt();
		sc.nextLine();
		System.out.print("제목 : ");
		String bname = sc.nextLine();
		System.out.print("권수 : ");
		int bstock = sc.nextInt();
		sc.nextLine();
		checkDuplicate(bno, bname);
		try {
			String sql = "select BNO, BNAME, BSTOCK from BOOKLIST";
			pstmt0 = con.prepareStatement(sql);
			ResultSet rs = pstmt0.executeQuery();
			while(rs.next()) {
				if(rs.getInt("BNO") == bno && rs.getString("BNAME").equals(bname)) {
					System.out.println(rs.getInt("BNO") + 1);
					System.out.println(bstock + rs.getInt("BSTOCK"));
				}
			}
			
			// 책을 추가하는 질의문
			sql = "insert into BOOKLIST" + "    values (?, ?, ?)";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setInt(1, bno);
			pstmt1.setString(2, bname);
			pstmt1.setInt(3, bstock);
			pstmt1.executeQuery();
			System.out.println("데이터베이스에 추가되었습니다.");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("데이터베이스 입력 에러입니다.");
		}
	}

	public void selBook() {
		System.out.print("조회할 제목 : ");
		String name = sc.nextLine();
		try {
			String sql = "select * from BOOKLIST" + "    where BNAME = ?";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setString(1, name);
			ResultSet rs = pstmt2.executeQuery();
			if (rs.next()) {
				System.out.println("번호 : " + rs.getString("BNO"));
				System.out.println("제목 : " + rs.getString("BNAME"));
				System.out.println("권수 : " + rs.getString("BSTOCK"));
			} else {
				System.out.println("해당 책이 존재하지 않습니다.");
			}
			rs.close();
		} catch (SQLException sqle) {
			System.out.println("알 수 없는 에러가 발생했습니다.");
		}
	}

	public void selAllBook() {
		System.out.println("전체 책 리스트 : ");
		try {
			String sql = "select * from BOOKLIST";
			pstmt3 = con.prepareStatement(sql);
			ResultSet rs = pstmt3.executeQuery();
			while (rs.next()) {
				System.out.print("번호 : " + rs.getString("BNO") + "\t|\t");
				System.out.print("제목 : " + rs.getString("BNAME") + "\t|\t");
				System.out.print("권수 : " + rs.getString("BSTOCK") + "\n");
			}
			rs.close();
		} catch (SQLException sqle) {
			System.out.println("알 수 없는 에러가 발생했습니다.");
		}
	}

	public void delBook() {
		System.out.print("삭제할 제목 : ");
		String bname = sc.nextLine();
		try {
			String sql = "delete BOOKLIST" + " where BNAME = ?";
			pstmt4 = con.prepareStatement(sql);
			pstmt4.setString(1, bname);
			pstmt4.executeQuery();
			System.out.println("삭제가 완료되었습니다.");
		} catch (SQLException sqle) {
			System.out.println("삭제 에러가 발생했습니다.");
		}
	}

	// 데이터베이스에 연결하는 메서드
	public void connectDatabase() {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}