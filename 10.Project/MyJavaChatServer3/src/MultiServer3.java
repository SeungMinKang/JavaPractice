
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MultiServer3 {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	Connection con;
	PreparedStatement pstmt3;

	// 데이터베이스에 연결하기 위한 메소드
	public void connectDatabase() {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	ServerSocket serverSocket = null;
	Socket socket = null;
	Map<String, PrintWriter> clientMap;

	// 생성자
	public MultiServer3() {
		// 클라이언트의 출력스트림을 저장할 해쉬맵 생성.
		clientMap = new HashMap<String, PrintWriter>();
		// 해쉬맵 동기화 설정.
		Collections.synchronizedMap(clientMap);
	}

	public void init() {
		try {
			serverSocket = new ServerSocket(9999); // 9999포트로 서버소켓 객체생성.
			System.out.println("서버가 시작되었습니다.");

			while (true) {
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress() + ":" + socket.getPort());

				Thread mst = new MultiServerT(socket); // 쓰레드 생성.
				mst.start(); // 쓰레드 시동.
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 접속자 리스트 보내기
	public void list(PrintWriter out) {
		// 출력스트림을 순차적으로 얻어와서 해당 메시지를 출력한다.
		Iterator<String> it = clientMap.keySet().iterator();
		String msg = "사용자 리스트 [";
		while (it.hasNext()) {
			msg += (String) it.next() + ",";
		}
		msg = msg.substring(0, msg.length() - 1) + "]";
		try {
			out.println(msg);
		} catch (Exception e) {
		}
	}

	// 접속된 모든 클라이언트들에게 메시지를 전달.
	public void sendAllMsg(String user, String msg) {
		// 출력스트림을 순차적으로 얻어와서 해당 메시지를 출력한다.
		Iterator<String> it = clientMap.keySet().iterator();

		while (it.hasNext()) {
			try {
				PrintWriter it_out = (PrintWriter) clientMap.get(it.next());
				if (user.equals(""))
					it_out.println(msg);
				else
					it_out.println("[" + user + "] " + msg);
			} catch (Exception e) {
				System.out.println("예외" + e);
			}
		}
	}

	public static void main(String[] args) {
		// 서버 객체 생성
		MultiServer3 ms = new MultiServer3();
		ms.init();
	}

	///////////////////////////////////////////////////////////////
	// 내부 클래스
	// 클라이언트로부터 읽어온 메시지를 다른 클라이언트(socket)에 보내는 역할을 하는 메서드

	class MultiServerT extends Thread {
		Socket socket;
		PrintWriter out = null;
		BufferedReader in = null;

		// 생성자
		public MultiServerT(Socket socket) {
			this.socket = socket;
			try {
				out = new PrintWriter(this.socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			} catch (Exception e) {
				System.out.println("예외2:" + e);
			}
		}

		// 쓰레드를 사용하기 위해서 run() 메서드 재정의
		@Override
		public void run() {
			// String s = "";
			String id = ""; // 클라이언트로부터 받은 id를 저장할 변수.
			try {

				id = in.readLine(); // 클라이언트에서 처음으로 보내는 메시지는
									// 클라이언트가 사용할 이름이다.
				saveUserOnServer(id);

				sendAllMsg("", id + "님이 입장하셨습니다.");

				// 현재 객체가 가지고 있는 소켓을 제외하고 다른 소켓(클라이언트)들에게 접속을 알림
				clientMap.put(id, out); // 해쉬맵에 키를 name으로 출력스트림 객체를 저장.
				System.out.println("현재 접속자 수는 " + clientMap.size() + "명 입니다.");

				// 입력스트림이 null이 아니면 반복.
				String s = "";
				Command comm = new Command();
				while (in != null) {
					s = in.readLine();
					System.out.println(s);
					if (s.substring(0, 1).equals("/")) {
						if (s.equals("/list"))
							comm.list(out, clientMap);
					} else
						sendAllMsg(id, s);
				}
			} catch (Exception e) {
//				System.out.println("예외:"+e);
			} finally {
				// 예외가 발생할 때 퇴장. 해쉬맵에서 해당 데이터 제거.
				// 보통 종료하거나 나가면 java.net.SocketException: 예외 발생
				removeUserOnServer(id);
				clientMap.remove(id);
				sendAllMsg("", id + "님이 퇴장하셨습니다.");
				System.out.println("현재 접속자 수는 " + clientMap.size() + "명 입니다.");

				try {
					in.close();
					out.close();

					socket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	////////////////////////////////////////////////////////////////
	// 접속한 사용자 정보를 chatServerDB에 저장하는 메소드
	public void saveUserOnServer(String id) {
		connectDatabase();
		try {
			String sql = "insert into CHATSERVER (jno, id)" + " values (jno_seq.nextval, ?)";
			pstmt3 = con.prepareStatement(sql);
			pstmt3.setString(1, id);
			pstmt3.executeUpdate();
			System.out.println("서버 : [" + id + "] 접속");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("알 수 없는 에러가 발생했습니다.");
		}
	}

	public void removeUserOnServer(String id) {
		connectDatabase();
		try {
			String sql = "delete CHATSERVER" + " where id = ?";
			pstmt3 = con.prepareStatement(sql);
			pstmt3.setString(1, id);
			pstmt3.executeUpdate();
			System.out.println("서버 : [" + id + "] 종료");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("알 수 없는 에러가 발생했습니다.");
		}
	}
}