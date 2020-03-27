
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.text.Document;

public class MultiServer5 {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	Connection con;
	PreparedStatement pstmt1;
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
	public MultiServer5() {
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
	
	// 같은 방 번호의 클라이언트들에게 메시지를 전달.
	public void sendRoomMsg(String user, String msg) {
		// 방번호를 가져오는 메소드를 만들어 int 형에 저장.
		int rno = rnoMatch(user);
		int otherno = 0;
		// 출력스트림을 순차적으로 얻어와서 해당 메시지를 출력한다.
		Iterator<String> it = clientMap.keySet().iterator();

		while (it.hasNext()) {
			try {
				String next = it.next();
				String id = (String)next;
				otherno = rnoMatch(id);
				if(rno == otherno) {
					PrintWriter it_out = (PrintWriter) clientMap.get(next);
					if (user.equals(""))
						it_out.println(msg);
					else
						it_out.println("[" + user + "] " + msg);
				}
			} catch (Exception e) {
				System.out.println("예외" + e);
			}
		}
	}

	public static void main(String[] args) {
		// 서버 객체 생성
		MultiServer5 ms = new MultiServer5();
		ms.removeUserOnServer();
		ms.initSeq();
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
			String pwd = "";

			try {
				id = signUp(in, out);
				
				saveUserOnServer(id);

//				sendAllMsg("", id + "님이 입장하셨습니다.");
				sendRoomMsg("", "===============================\n"
					     + id + "님이 입장하셨습니다.\n"
					     + "===============================");

				// 현재 객체가 가지고 있는 소켓을 제외하고 다른 소켓(클라이언트)들에게 접속을 알림
				clientMap.put(id, out); // 해쉬맵에 키를 id로 출력스트림 객체를 저장.
				System.out.println("현재 접속자 수는 " + clientMap.size() + "명 입니다.");

				// 입력스트림이 null이 아니면 반복.
				String s = "";
				Command comm = new Command();
				while (in != null) {
					s = in.readLine();
					System.out.println(s);
					if (s.substring(0, 1).equals("/")) {
						comm.menu(out, in, clientMap, s, id);
					} else {
						s = comm.censorWords(s);
						sendRoomMsg(id, s);
					}
				}
			} catch (Exception e) {
//				System.out.println("예외:"+e);
			} finally {
				// 예외가 발생할 때 퇴장. 해쉬맵에서 해당 데이터 제거.
				// 보통 종료하거나 나가면 java.net.SocketException: 예외 발생
				removeUserOnServer(id);
				clientMap.remove(id);
				sendRoomMsg("", "===============================\n"
						     + id + "님이 퇴장하셨습니다.\n"
						     + "===============================");
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
		
		// 로그인 또는 가입을 물어보는 메서드
		public String signUp(BufferedReader in, PrintWriter out) {
			connectDatabase();
			String id = "";
			String pwd = "";
			try {
				// 로그인 or 회원가입 선택지
				out.println("자바챗에 오신 것을 환영합니다.");
				out.println("1.로그인\t2.회원가입");
				out.print("선택: ");
				String choice = in.readLine();
				if (choice.equals("1")) {
					out.println("ID를 입력해 주세요.");
					id = in.readLine();
					out.println("비밀번호를 입력해 주세요.");
					pwd = in.readLine();
					while (idPwdMatch(id, pwd) == false) {
						out.println("ID 또는 비밀번호가 존재하지 않습니다.");
						out.println("ID를 입력해 주세요.");
						id = in.readLine();
						out.println("비밀번호를 입력해 주세요.");
						pwd = in.readLine();
					}
					// 서버로 로그인 정보 보내는 메서드 넣기
				} else if (choice.equals("2")) {
					makeUserIDPWD(id, in);
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
					out.println("ID 또는 비밀번호가 존재하지 않습니다.");
				}
				rs.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				out.println("알 수 없는 에러가 발생했습니다.");
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
				out.println("알 수 없는 에러가 발생했습니다.");
			}
			return exist;
		}

		// 가입 시 ID와 비밀번호를 입력 받는 메서드
		public void makeUserIDPWD(String id, BufferedReader in) {
			try {
				out.println("생성할 ID를 입력해 주세요.");
				id = in.readLine();

				while (idTrueFalse(id) == true) {
					out.println("이미 존재하는 ID입니다.");
					out.println("다른 ID를 입력해주세요.");
					id = in.readLine();
				}
				out.println("생성할 수 있는 ID입니다.");

				out.println("비밀번호를 입력해주세요.");
				String pwd1 = in.readLine();
				out.println("확인을 위해 비밀번호를 재입력해주세요.");
				String pwd2 = in.readLine();

				while (!(pwd1.equals(pwd2))) {
					out.println("처음 입력한 비밀번호와 다릅니다.");
					out.println("재입력해주세요.");
					pwd2 = in.readLine();
				}
				saveUserInfo(id, pwd1);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}

		// 가입 시 입력한 ID와 비밀번호를 Chatters에 저장하는 메서드
		public void saveUserInfo(String id, String pwd) {
			try {
				String sql = "insert into scott.CHATTERS (cno, id, pwd)" + " values (scott.cno.nextval, ?, ?)";
				pstmt1 = con.prepareStatement(sql);
				pstmt1.setString(1, id);
				pstmt1.setString(2, pwd);
				pstmt1.executeUpdate();
				out.println("가입이 완료되었습니다.");
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				out.println("알 수 없는 에러가 발생했습니다.");
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
		} finally {
			try {
				if(pstmt3 != null) pstmt3.close();
				if(con != null) con.close();
			} catch (SQLException sqle) {}
		}
	}
	
	// 처음 시작 시 찌꺼기 데이터를 전부 지우는 메서드
	public void removeUserOnServer() {
		connectDatabase();
		try {
			String sql = "delete CHATSERVER";
			pstmt3 = con.prepareStatement(sql);
			pstmt3.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("알 수 없는 에러가 발생했습니다.");
		} finally {
			try {
				if(pstmt3 != null) pstmt3.close();
				if(con != null) con.close();
			} catch (SQLException sqle) {}
		}
	}

	// 접속을 종료한 사용자 정보를 chatServerDB에서 제거하는 메소드
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
		} finally {
			try {
				if(pstmt3 != null) pstmt3.close();
				if(con != null) con.close();
			} catch (SQLException sqle) {}
		}
	}
	
	// 사용자의 방번호를 가져오는 메서드
	public int rnoMatch(String id) {
		connectDatabase();
		int rno = 0;
		try {
			String sql = "select rno from chatserver"
					   + " where id = ?";
			pstmt3 = con.prepareStatement(sql);
			pstmt3.setString(1, id);
			ResultSet rs = pstmt3.executeQuery();
			if (rs.next()) {
				rno = rs.getInt("RNO");
			}
			rs.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("알 수 없는 에러가 발생했습니다.");
		} finally {
			try {
				if(pstmt3 != null) pstmt3.close();
				if(con != null) con.close();
			} catch (SQLException sqle) {}
		}
		return rno;
	}
	
	// 접속번호(jno)와 방 생성번호(rno) 시퀀스를 초기화하는 메소드
	public void initSeq() {
		connectDatabase();
		try {
			String sql = "drop sequence jno_seq";
			pstmt3 = con.prepareStatement(sql);
			pstmt3.executeUpdate();
			
			sql = "drop sequence rno_seq";
			pstmt3 = con.prepareStatement(sql);
			pstmt3.executeUpdate();
			
			sql = "create sequence jno_seq"
				+ " increment by 1"
				+ "	start with 1"
				+ " minvalue 1"
				+ " maxvalue 10000";
			pstmt3 = con.prepareStatement(sql);
			pstmt3.executeUpdate();
			
			sql = "create sequence rno_seq"
					+ " increment by 1"
					+ "	start with 1"
					+ " minvalue 1"
					+ " maxvalue 10000";
				pstmt3 = con.prepareStatement(sql);
				pstmt3.executeUpdate();		
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("초기화 중 오류가 발생했습니다.");
		} finally {
			try {
				if(pstmt3 != null) pstmt3.close();
				if(con != null) con.close();
			} catch (SQLException sqle) {}
		}
	}
}