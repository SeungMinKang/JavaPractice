
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MultiClient5 {

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
		MultiClient5 mc2 = new MultiClient5();
		mc2.doConnect(args);
	}
	
	public void doConnect(String[] args) {
		try {
			String ServerIP = "localhost";
			if (args.length > 0)
				ServerIP = args[0];
			Socket socket = new Socket(ServerIP, 9999); // 소켓 객체 생성
			System.out.println("서버와 연결이 되었습니다......");

			// 서버에서 보내는 메시지를 사용자의 콘솔에 출력하는 쓰레드.
			Thread receiver = new Receiver5(socket);
			receiver.start();

			// 사용자로부터 얻은 문자열을 서버로 전송해주는 역할을 하는 쓰레드.
			Thread sender = new Sender5(socket);
			sender.start();

		} catch (Exception e) {
			System.out.println("예외[MultiClient class]:" + e);
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