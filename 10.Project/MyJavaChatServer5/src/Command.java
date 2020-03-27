import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Command {

	MultiServer5 ms4 = new MultiServer5();
	MoveTheBall mtb = new MoveTheBall();
	
	// MoveTheBall 게임 실행
	
	// 귓속말 메소드
	public void whisperTo(String user, String toID, String msg, Map<String, PrintWriter> clientMap) {
		// 출력스트림을 순차적으로 얻어와서 해당 메시지를 출력한다.
		Iterator<String> it = clientMap.keySet().iterator();

		while (it.hasNext()) {
			try {
				String next = it.next();
				String id = (String)next;
				if(id.equals(toID)) {
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
	
	// 특정 유저에게 초대 메세지를 보내는 메소드
	// 귓속말 메소드의 오버로딩
	public void whisperTo(String user, String toID, Map<String, PrintWriter> clientMap) {
		// 출력스트림을 순차적으로 얻어와서 해당 메시지를 출력한다.
		Iterator<String> it = clientMap.keySet().iterator();

		while (it.hasNext()) {
			try {
				String next = it.next();
				String id = (String)next;
				if(id.equals(toID)) {
					PrintWriter it_out = (PrintWriter) clientMap.get(next);
					if (user.equals("")) {
						it_out.println("[invitation] " + user + "invited you!");
					}
					else {}
				}
			} catch (Exception e) {
				System.out.println("예외" + e);
			}
		}
	}
	
	// 플레이어 강퇴	-- 방장만 사용가능하도록 기능 변경 요구
	public void kickUser(String msg) {
		connectDatabase();
		try {
			String sql = "update Chatserver"
					   + "   set RNO = 0"
					   + " where id = ?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, msg);
			pstmt1.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt1 != null) pstmt1.close();
				if(con != null) con.close();
			} catch (SQLException sqle) {}
		}
	}
	
	// 방 목록 보기
	public void showRoomList(PrintWriter out) {
		connectDatabase();
		try {
			String sql = "select RNO, RNAME from ChattingRoom";
			pstmt1 = con.prepareStatement(sql);
			ResultSet rs = pstmt1.executeQuery();
			String msg = "=================\n"
					   + "=====방 목록=====\n"
					   + "=================\n";
			while(rs.next()) {
				if(rs.getInt("RNO") == 0) {
					msg += "[0] 대기실\n";
				} else {
					msg += "[" + (String) rs.getString("RNO") + "] " + rs.getString("RNAME");	
				}
			}
			out.println(msg);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt1 != null) pstmt1.close();
				if(con != null) con.close();
			} catch (SQLException sqle) {}
		}
	}
	
	// 방 만들기
	public void createRoom(String id, String msg, PrintWriter out) {
		connectDatabase();
		int rno = 0;
		try {
			// 유저의 방정보 변경
			String sql = "update Chatserver"
					   + "   set RNO = rno_seq.nextval"
					   + " where id = ?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, id);
			pstmt1.executeUpdate();
			
			// 변경된 방번호 값을 불러옴
			sql = "select rno from chatserver"
				+ " where id = ?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, id);
			ResultSet rs = pstmt1.executeQuery();
			if(rs.next()) {
				rno = rs.getInt("RNO");	
			}			
			
			// 방번호와 방제목을 ChattingRoom에 저장
			sql = "insert into ChattingRoom (RNO, RNAME, MASTER)"
				+ "   values (?, ?, ?)";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setInt(1, rno);
			pstmt1.setString(2, msg);
			pstmt1.setString(3, id);
			pstmt1.executeUpdate();
			out.println("=========" + msg + "=========\n"
					  + "방을 생성하였습니다..");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt1 != null) pstmt1.close();
				if(con != null) con.close();
			} catch (SQLException sqle) {}
		}
	}
	
	// 방으로 이동
	public void moveToRoom(String id, String msg, PrintWriter out) {
		connectDatabase();
		int rno = Integer.parseInt(msg);
		try {
//			int p_rno = chkRoomNum(id);
			// 유저의 방정보를 변경
			String sql = "update Chatserver"
					   + "   set RNO = ?"
					   + " where id = ?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setInt(1, rno);
			pstmt1.setString(2, id);
			pstmt1.executeUpdate();
			
			// 이동한 방의 방 제목을 가져옴
			sql = "select RNAME from chattingroom"
				+ " where rno = ?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setInt(1, rno);
			ResultSet rs = pstmt1.executeQuery();
			if(rs.next()) {
				String rname = rs.getString("RNAME");
				out.println("=========" + rname + "========="
						  + "에 입장하셨습니다.");	
			}
			
			// 이전 방의 정보 체크
//			chkRoom(p_rno);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt1 != null) pstmt1.close();
				if(con != null) con.close();
			} catch (SQLException sqle) {}
		}
	}
	
	// 현재 인물의 방번호를 구하는 메소드
	public int chkRoomNum(String id) {
		connectDatabase();
		int p_rno = 0;
		try {
			String sql = "select rno from Chatserver"
					   + " where id = ?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, id);
			ResultSet rs = pstmt1.executeQuery();
			if(rs.next()) {
				p_rno = rs.getInt("RNO");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p_rno;
	}
	
	// 특정 방이 빈 방인지 구하는 메소드
	public void chkRoom(int p_rno) {
		connectDatabase();
		try {
			String sql = "select count(rno) from Chatserver"
					   + " where rno = ?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setInt(1, p_rno);
			ResultSet rs = pstmt1.executeQuery();
			if(rs.next()) {
				if(p_rno != 0 && rs.getInt(1) == 0) {
					// 카운트가 0이면 ChattingRoom방 삭제
					// 삭제 메소드 호출
					removeRoom(p_rno);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt1 != null) pstmt1.close();
				if(con != null) con.close();
			} catch (SQLException sqle) {}
		}
	}
	
	// 방 삭제
	public void removeRoom(int p_rno) {
		try {
			String sql = "delete ChattingRoom"
					   + " where rno = ?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setInt(1, p_rno);
			pstmt1.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt1 != null) pstmt1.close();
				if(con != null) con.close();
			} catch (SQLException sqle) {}
		}
	}
	
	
	// 대기실로 이동
	public void exitRoom(String id) {
		connectDatabase();
		try {
			int p_rno = chkRoomNum(id);
			
			String sql = "update Chatserver"
					   + "   set RNO = 0"
					   + " where id = ?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, id);
			pstmt1.executeUpdate();
			
			chkRoom(p_rno);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt1 != null) pstmt1.close();
				if(con != null) con.close();
			} catch (SQLException sqle) {}
		}
	}
	
	// 방 목록 나열하기

	////////////////////////////////////////////////////////////////
	// 접속자 리스트 보내기
	public void list(PrintWriter out, Map<String, PrintWriter> clientMap) {
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

	////////////////////////////////////////////////////////////////
	// 관리자 명령어 ///////////////////////////////////////////////
	////////////////////////////////////////////////////////////////

	// 특정 word를 찾아 차단하는 메서드
	public void blockall(PrintWriter out, String block) {
		connectDatabase();
		try {
			String sql = "insert into CensoredWords" + " values (?)";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, block);
			pstmt1.executeUpdate();
			out.println(block + "이(가) 정상적으로 금지어 처리되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt1 != null) pstmt1.close();
				if(con != null) con.close();
			} catch (SQLException sqle) {}
		}
	}
	
	// 특정 word를 찾아 차단을 풀어주는 메서드
	public void allowWord(PrintWriter out, String msg) {
		connectDatabase();
		try {
			String sql = "delete CensoredWords" + " where WORD = ?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, msg);
			pstmt1.executeUpdate();
			out.println(msg + "이(가) 금지어에서 삭제되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt1 != null) pstmt1.close();
				if(con != null) con.close();
			} catch (SQLException sqle) {}
		}
	}
	
	// msg에서 특정 word를 검사해서 검열해주는 메서드
	public String censorWords(String msg) {
		String block = "";
		connectDatabase();
		try {
			String sql = "select WORD from CensoredWords";
			pstmt1 = con.prepareStatement(sql);
			ResultSet rs = pstmt1.executeQuery();
			while (rs.next()) {
				block = rs.getString("WORD");
				String cens = "";
				for (int i = 0; i < block.length(); i++) {
					String star = "*";
					cens = cens + star;
				}
				if (msg.indexOf(block) != -1) {
					msg = msg.replace(block, cens);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt1 != null) pstmt1.close();
				if(con != null) con.close();
			} catch (SQLException sqle) {}
		}
		return msg;
	}
	
	// 현재 사용자가 서버어드민인지 판별하여 /notice를 사용할 수 있게 하는 메서드
	public boolean isAdmin( String id) {
		connectDatabase();
		boolean admin = false;
		try {
			String sql = "select id from Chatters"
					   + " where id = ?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, id);
			ResultSet rs = pstmt1.executeQuery();
			if(rs.next()) {
				if(rs.getString("id").equals("ServerAdmin")) {
					admin = true;
				} else {
					System.out.println("관리자만 사용 가능합니다.");
				}
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return admin;
	}
	
	// 접속된 모든 클라이언트들에게 메시지를 전달.
	public void notice(String user, String msg, Map<String, PrintWriter> clientMap) {
		// 해당 인원이 관리자인지 판별.
		if(isAdmin(user)) {			
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
				} finally {
					try {
						if(pstmt1 != null) pstmt1.close();
						if(con != null) con.close();
					} catch (SQLException sqle) {}
				}
			}
		}
	}

	/////////////////////////////////////////////////////////
	// 메뉴 /////////////////////////////////////////////////
	/////////////////////////////////////////////////////////
	public void menu(PrintWriter out, BufferedReader in, Map<String, PrintWriter> clientMap, String s, String id) {
		
		if (s.equals("/list")) {
			list(out, clientMap);
		} else if (s.indexOf("/blockall") != -1) {
			blockall(out, s.substring(s.indexOf(" ")+1));
		} else if (s.indexOf("/allowWord") != -1) {
			allowWord(out, s.substring(s.indexOf(" ")+1));
		} else if (s.indexOf("/create") != -1) {
			createRoom(id, s.substring(s.indexOf(" ")+1), out);
		} else if (s.indexOf("/moveto") != -1) {
			moveToRoom(id, s.substring(s.indexOf(" ")+1), out);
		} else if (s.equals("/exit")) {
			exitRoom(id);
		} else if (s.indexOf("/kick") != -1) {
			kickUser(s.substring(s.indexOf(" ")+1));
		} else if (s.equals("/roomlist")) {
			showRoomList(out);
		} else if (s.indexOf("/notice") != -1) {
			notice(id, s.substring(s.indexOf(" ")+1), clientMap);
		} else if (s.indexOf("/to") != -1) {
			whisperTo(id, s.substring(s.indexOf(" ")+1, s.indexOf("|")-1), s.substring(s.indexOf("|")+1), clientMap);
		} else if (s.indexOf("/start movetheball") != -1) {
			mtb.start(out, in);
		}
		else
			out.println("실행 가능한 명령어가 아닙니다.");
	}
	
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

	// 데이터베이스에 연결하기 위한 메소드
	public void connectDatabase() {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}