
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Membership {
	
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
	PreparedStatement pstmt4;
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Membership ms = new Membership();
		ms.doRun();
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
				signUp();
				break;
			case 2:
				selMember();
				break;
			case 3:
				selAllMember();
				break;
			case 4:
				changeMemberStatus();
				break;
			case 5:
				delMember();
				break;
			case 6:
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
		System.out.println("1. 회원 정보 등록");
		System.out.println("2. 회원 조회");
		System.out.println("3. 전체 회원 리스트");
		System.out.println("4. 회원 상태 수정");
		System.out.println("5. 회원 삭제");
		System.out.println("6. 종료");
		System.out.print("선택 : ");
	}

	public void signUp() {
		System.out.print("ID : ");
		String mid = sc.nextLine();
		System.out.print("이름 : ");
		String mname = sc.nextLine();
		try {
			String sql = "insert into MEMBER (mno, mid, mname)"
					   + "    values (mno_seq.nextval, ?, ?)";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, mid);
			pstmt1.setString(2, mname);
			pstmt1.executeUpdate();
			System.out.println("데이터베이스에 추가되었습니다.");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("데이터베이스 입력 에러입니다.");
		}
	}

	public void selMember() {
		System.out.print("조회할 이름 : ");
		String mid = sc.nextLine();
		boolean blk = false;
		try {
			String sql = "select * from MEMBER" + "    where mid = ?";
			pstmt3 = con.prepareStatement(sql);
			pstmt3.setString(1, mid);
			ResultSet rs = pstmt3.executeQuery();
			if (rs.next()) {
				System.out.println("회원 번호 : " + rs.getString("MNO"));
				System.out.println("회원 ID : " + rs.getString("MID"));
				System.out.println("회원 이름 : " + rs.getString("MNAME"));
				if (rs.getInt("MBLK") == 0) {
					blk = false;
					System.out.println("회원 상태 : 정상");
				} else {
					blk = true;
					System.out.println("회원 상태 : 차단");
				}
			} else {
				System.out.println("해당 값이 없습니다.");
			}
			rs.close();
		} catch (SQLException sqle) {
			System.out.println("알 수 없는 에러가 발생했습니다.");
		} 
	}

	public void selAllMember() {
		try {
			String sql = "select * from MEMBER";
			pstmt3 = con.prepareStatement(sql);
			ResultSet rs = pstmt3.executeQuery();
			while (rs.next()) {
				System.out.print("회원 번호 : " + rs.getString("MNO") + "\t|");
				System.out.print("회원 ID : " + rs.getString("MID") + "\t|");
				System.out.print("회원 이름 : " + rs.getString("MNAME") + "\t|");
				if (rs.getInt("MBLK") == 0) {
					System.out.println("회원 상태 : 정상");
				} else {
					System.out.println("회원 상태 : 차단");
				}
			}
			rs.close();
		} catch (SQLException sqle) {
			System.out.println("알 수 없는 에러가 발생했습니다.");
		} 
	}
	
	public void changeMemberStatus() {
		System.out.print("상태 변경할 ID : ");
		String mid = sc.nextLine();
		System.out.print("활성: 0 \t 비활성: 1\n선택 : " );
		String mblk = sc.nextLine();
		try {
			String sql = "update MEMBER"
					   + "   set mblk = ?"
					   + " where mid = ?";
			pstmt4 = con.prepareStatement(sql);
			pstmt4.setString(1, mblk);
			pstmt4.setString(2, mid);
			pstmt4.executeUpdate();
			System.out.println("상태 변경이 완료되었습니다.");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("상태 변경 중 오류가 발생했습니다.");
		}
	}
	
	public void delMember() {
		System.out.print("삭제할 ID : ");
		String mid = sc.nextLine();
		try {
			String sql = "delete MEMBER"
					   + " where MID = ?";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setString(1, mid);
			pstmt2.executeUpdate();
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