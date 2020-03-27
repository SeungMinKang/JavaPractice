
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MultiClient4 {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	Connection con;
	PreparedStatement pstmt1;
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws UnknownHostException, IOException {
		MultiClient4 mc2 = new MultiClient4();
		Checker chk = new Checker();
		String id = "";
		String pwd = "";
		mc2.connectDatabase();
		id = mc2.signUp(id, pwd);
		if(chk.CheckIdInServer(id, mc2.pstmt1, mc2.con) == true) {
			System.out.println("이미 접속한 사용자입니다.");
		} else if (chk.CheckIdInServer(id, mc2.pstmt1, mc2.con) == false) {
			System.out.println("접속을 진행합니다.");
			mc2.doConnect(args, id);
		}
	}
	
	public void doConnect(String[] args, String id) {
		try {
			String ServerIP = "localhost";
			if (args.length > 0)
				ServerIP = args[0];
			Socket socket = new Socket(ServerIP, 9999); // 소켓 객체 생성
			System.out.println("서버와 연결이 되었습니다......");

			// 서버에서 보내는 메시지를 사용자의 콘솔에 출력하는 쓰레드.
			Thread receiver = new Receiver4(socket);
			receiver.start();

			// 사용자로부터 얻은 문자열을 서버로 전송해주는 역할을 하는 쓰레드.
			Thread sender = new Sender4(socket, id);
			sender.start();

		} catch (Exception e) {
			System.out.println("예외[MultiClient class]:" + e);
		}
	}

	// 로그인 또는 가입을 물어보는 메서드
	public String signUp(String id, String pwd) {
		try {
			// 로그인 or 회원가입 선택지
			System.out.println("자바챗에 오신 것을 환영합니다.");
			System.out.println("1.로그인\t2.회원가입");
			System.out.print("선택: ");
			String choice = sc.nextLine();
			if (choice.equals("1")) {
				System.out.println("ID를 입력해 주세요.");
				id = sc.nextLine();
				System.out.println("비밀번호를 입력해 주세요.");
				pwd = sc.nextLine();
				while (idPwdMatch(id, pwd) == false) {
					System.out.println("ID 또는 비밀번호가 존재하지 않습니다.");
					System.out.println("ID를 입력해 주세요.");
					id = sc.nextLine();
					System.out.println("비밀번호를 입력해 주세요.");
					pwd = sc.nextLine();
				}
				// 서버로 로그인 정보 보내는 메서드 넣기
			} else if (choice.equals("2")) {
				makeUserIDPWD(id);
			}
		} catch (Exception e) {
			System.out.println("클라이언트에서 오류가 발생했습니다.");
		}
		return id;
	}

	// id와 비밀번호가 일치하는지 확인하는 메서드
	public boolean idPwdMatch(String id, String pwd) {
		boolean check = false;
		try {
			String sql = "select * from scott.CTOC" + " where id = ?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, id);
			ResultSet rs = pstmt1.executeQuery();
			if (rs.next()) {
				if (rs.getString("PWD").equals(pwd))
					check = true;
			} else {
				System.out.println("ID 또는 비밀번호가 존재하지 않습니다.");
			}
			rs.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("알 수 없는 에러가 발생했습니다.");
		}
		return check;
	}

	// id가 ChattersDB에 있는지 확인하는 메서드(idTrueFalse 오버로딩)
	public boolean idTrueFalse(String id) {
		boolean exist = false;
		try {
			String sql = "select ID from scott.CTOC" + " where id = ?";
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

	// 가입 시 ID와 비밀번호를 입력 받는 메서드
	public void makeUserIDPWD(String id) {
		System.out.println("생성할 ID를 입력해 주세요.");
		id = sc.nextLine();

		while (idTrueFalse(id) == true) {
			System.out.println("이미 존재하는 ID입니다.");
			System.out.println("다른 ID를 입력해주세요.");
			id = sc.nextLine();
		}
		System.out.println("생성할 수 있는 ID입니다.");

		System.out.println("비밀번호를 입력해주세요.");
		String pwd1 = sc.nextLine();
		System.out.println("확인을 위해 비밀번호를 재입력해주세요.");
		String pwd2 = sc.nextLine();

		while (!(pwd1.equals(pwd2))) {
			System.out.println("처음 입력한 비밀번호와 다릅니다.");
			System.out.println("재입력해주세요.");
			pwd2 = sc.nextLine();
		}
		saveUserInfo(id, pwd1);
	}

	// 가입 시 입력한 ID와 비밀번호를 Chatters에 저장하는 메서드
	public void saveUserInfo(String id, String pwd) {
		try {
			String sql = "insert into scott.CHATTERS (cno, id, pwd)" + " values (scott.cno.nextval, ?, ?)";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, id);
			pstmt1.setString(2, pwd);
			pstmt1.executeUpdate();
			System.out.println("가입이 완료되었습니다.");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("알 수 없는 에러가 발생했습니다.");
		}
	}

	// 데이터베이스에 연결하기 위한 메소드
	public void connectDatabase() {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "client", "0000");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}