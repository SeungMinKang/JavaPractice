
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class RentalSheet {
	
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
		RentalSheet rs = new RentalSheet();
		rs.doRun();
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
				getMemberInfo();
				break;
			case 2:
				renewRentDate();
				break;
			case 3:
				returnBook();
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
		System.out.println("1. 도서 대여");
		System.out.println("2. 대여 기간 연장");
		System.out.println("3. 대출 도서 반납");
		System.out.println("4. 종료");
		System.out.print("선택 : ");
	}

	// 회원 정보를 불러와 회원의 블랙리스트 상태를 체크하는 메소드
	public void getMemberInfo() {
		System.out.print("회원 이름 : ");
		String mname = sc.nextLine();
		System.out.print("회원 ID : ");
		String mid = sc.nextLine();
		try {
			String sql = "select * from MEMBER"
					   + " where mname = ?"
					   + "   and mid = ?";
			pstmt3 = con.prepareStatement(sql);
			pstmt3.setString(1, mname);
			pstmt3.setString(2, mid);
			ResultSet rs = pstmt3.executeQuery();
			if (rs.next()) {
				System.out.println("회원 번호 : " + rs.getString("MNO"));
				System.out.println("회원 ID : " + rs.getString("MID"));
				System.out.println("회원 이름 : " + rs.getString("MNAME"));
				if(rs.getInt("MBLK") == 0) {
					System.out.println("회원 상태 : 정상");
					System.out.println("도서를 대출할 수 있습니다.");
					getBookInfo(mid);
				} else {
					System.out.println("회원 상태 : 차단");
					System.out.println("현재 도서를 대출할 수 없는 상태입니다.");
				}
			} else {
				System.out.println("해당 값이 없습니다.");
			}
			rs.close();
		} catch (SQLException sqle) {
			System.out.println("알 수 없는 에러가 발생했습니다.");
		} 
	}
	
	// 빌리려는 책 정보를 불러와 대출할 수량을 물어보는 메소드
	public void getBookInfo(String mid) {
		System.out.print("대출할 도서 번호 : ");
		String bno = sc.nextLine();
		System.out.print("대출할 도서 제목 : ");
		String bname = sc.nextLine();
		try {
			String sql = "select * from BOOKLIST"
					   + " where bno = ?"
					   + "   and bname = ?";
			pstmt3 = con.prepareStatement(sql);
			pstmt3.setString(1, bno);
			pstmt3.setString(2, bname);
			ResultSet rs = pstmt3.executeQuery();
			if (rs.next()) {
				System.out.println("도서 번호 : " + rs.getString("BNO"));
				System.out.println("도서 제목 : " + rs.getString("BNAME"));
				System.out.println("재고 수량 : " + rs.getString("BSTOCK"));
				if(rs.getInt("BSTOCK") <= 0) {
					System.out.println("도서 재고 수량이 없습니다!");
				} else {
					System.out.print("몇 권을 대출하시겠습니까? ");
					int want = sc.nextInt();
					int stock = rs.getInt("BSTOCK");
					while(rs.getInt("BSTOCK") - want < 0) {
						System.out.println("재고 수량보다 많은 책을 빌릴 수 없습니다!");
						System.out.print("몇 권을 대출하시겠습니까? ");
						want = sc.nextInt();
					}
					rentBook(bno, bname, mid, want, stock);
				}
			} else {
				System.out.println("해당 값이 없습니다.");
			}
			rs.close();
		} catch (SQLException sqle) {
			System.out.println("알 수 없는 에러가 발생했습니다.");
		} 
	}
	
	// getMemberInfo, getBookInfo에서 받은 값들을 RentalDB에 등록하는 메소드
	public void rentBook(String bno, String bname, String mid, int want, int stock) {
		// RentalDB에 해당 bno와 bname을 등록하고
		// getMemberInfo에서 찾아온 mid를 가진 사람이 want만큼 대출하였음을 알린다. 
		
		try {
			String sql = "insert into RENTALDB"
					   + " (rno, bno, bname, amount, mid, returndate)"
					   + " values (rno_seq.nextval, ?, ?, ?, ?, null)";
			pstmt4 = con.prepareStatement(sql);
			pstmt4.setString(1, bno);
			pstmt4.setString(2, bname);
			pstmt4.setInt(3, want);
			pstmt4.setString(4, mid);
			pstmt4.executeUpdate();
			
			////////////////////////////////////////////////////////////////////
			// want의 수량만큼 해당 bno의 bstock에서 뺀 후 결과값을 넣어준다.
			int afterstock = stock - want;
			
			sql = "update BOOKLIST"
				+ "   set bstock = ?"
				+ " where bno = ?";
			pstmt4 = con.prepareStatement(sql);
			pstmt4.setInt(1, afterstock);
			pstmt4.setString(2, bno);
			pstmt4.executeUpdate();
			System.out.println("대출이 완료되었습니다.");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("삭제 에러가 발생했습니다.");
		}
	}
	
	public void renewRentDate() {
		System.out.print("회원 ID : ");
		String mid = sc.nextLine();
		int chk = 0;
		try {
			String sql = "select * from RentalDB"
					   + " where mid = ?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, mid);
			ResultSet rs = pstmt1.executeQuery();
			if (rs.next()) {
				System.out.println("도서 번호 : " + rs.getInt("BNO"));
				System.out.println("도서 제목 : " + rs.getString("BNAME"));
				System.out.println("대여 수량 : " + rs.getInt("AMOUNT"));
				System.out.println("회원 ID : " + rs.getString("MID"));
				System.out.println("대출일 : " + rs.getDate("RENTALDATE"));
				System.out.println("대출 기한 : " + rs.getDate("DUEDATE"));
				chk = rs.getInt("RENEWCHECK");
			} else {
				System.out.println("해당 값이 없습니다.");
			}
			rs.close();
			
			System.out.print("대출을 연장하시겠습니까? (y/n)");
			String ans = sc.nextLine();
			if(ans.equals("y")) {
				if(chk == 1) {
					System.out.println("연장 가능 횟수를 초과하였습니다.");
				} else {
					delayAWeek(mid);
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("오류가 발생하였습니다.");
		}
	}
	
	public void delayAWeek(String mid) {
		try {
			String sql = "update RENTALDB"
					   + "   set duedate = (duedate + 7), renewcheck = 1"
					   + " where mid = ?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, mid);
			pstmt1.executeUpdate();

			System.out.println("연장 신청이 완료되었습니다.");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("작업 중 오류가 발생했습니다.");
		}
	}

	public void returnBook() {
		int bno = 0;
		int amount = 0;
		String due = null;
		String ret = null;
		System.out.print("회원 ID : ");
		String mid = sc.nextLine();
		System.out.print("도서 제목 : ");
		String bname = sc.nextLine();
		try {
			//RentalDB의 returndate을 업데이트하기.
			String sql = "update RentalDB"
					   + "   set returndate = sysdate"
					   + " where mid = ?"
					   + "   and bname = ?";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setString(1, mid);
			pstmt2.setString(2, bname);
			pstmt2.executeUpdate();
			
			////////////////////////////////////////////////////////////////////
			// 반납한 도서 수량을 BOOKLIST 해당 bno, bname에 해당하는 BSTOCK에 채워넣기
			// 그 전에 정보를 얻어오기
			sql = "select bno, amount, duedate, returndate from rentalDB"
				+ " where mid = ?"
				+ "   and bname = ?";
			pstmt2 =con.prepareStatement(sql);
			pstmt2.setString(1, mid);
			pstmt2.setString(2, bname);
			ResultSet rs = pstmt2.executeQuery();
			if(rs.next()) {
				bno = rs.getInt("BNO");
				amount = rs.getInt("AMOUNT");
				due = rs.getString("DUEDATE");
				ret = rs.getString("RETURNDATE");
				backToShelf(bno, bname, amount);
				checkBlacklist(mid);
			}
			System.out.println("반납이 완료되었습니다.");
		} catch (SQLException sqle) {
			System.out.println("반납 중 오류가 발생했습니다.");
		}
	}
	
	public void backToShelf(int bno, String bname, int amount) {
		try {
			String sql = "update BOOKLIST"
					   + "   set bstock = bstock + ?"
					   + " where bno = ?"
					   + "   and bname = ?";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, amount);
			pstmt2.setInt(2, bno);
			pstmt2.setString(3, bname);
			pstmt2.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("반납 중 오류가 발생했습니다.");
		}
	}
	
	// 대출 만기일을 어긴 사람을 blacklist로 만드는 메소드
	public void checkBlacklist(String mid) {
		try {
			String sql = "update RentalDB"
					   + "   set blkcheck = 1"
					   + " where duedate - returndate < 0";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.executeUpdate();
			
			sql = "select blkcheck from RentalDB"
				+ " where mid = ?";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setString(1, mid);
			ResultSet rs = pstmt2.executeQuery();
			if(rs.next()) {
				if(rs.getInt("BLKCHECK") == 1) {
					chkBL(mid);
					BlkDay(mid);
				}
			}			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("블랙리스트 체크 중 오류가 발생했습니다.");
		}
	}
	
	public void chkBL(String mid) {
		try{
			String sql = "update MEMBER"
					   + "   set MBLK = 1"
					   + " where mid = ?";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setString(1, mid);
			pstmt2.executeUpdate();
		} catch (SQLException sqle) {
			System.out.println("블랙리스트 처리 중 오류가 발생했습니다.");
		}
	}
	
	// 블랙리스트 기간을 계산하는 메소드
	public void BlkDay(String mid) {
		int blkday = 0;
		try{
			String sql = "select round(((returndate) - duedate),0) from rentalDB"
					   + " where mid = ?";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setString(1, mid);
			ResultSet rs = pstmt2.executeQuery();
			if(rs.next()) {
				blkday = (rs.getInt(1) * 2);
				System.out.println(blkday);
			}
			
			sql = "update MEMBER"
				+ "   set BLKPERIOD = sysdate + ?"
				+ " where MID = ?";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, blkday);
			pstmt2.setString(2, mid);
			pstmt2.executeUpdate();
		} catch (SQLException sqle) {
			System.out.println("블랙리스트 처리 중 오류가 발생했습니다.");
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