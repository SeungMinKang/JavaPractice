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

	MultiServer3 ms3 = new MultiServer3();
	
	
	
	// 플레이어 초대	-- 방장만 사용가능하도록 기능 변경 요구
	// 기능 구현 안됨 - 기능 구현 필요!!!!
	public void inviteUser(String id, String msg) {
		connectDatabase();
		try {
			String sql = "update Chatserver"
					   + "   set RNO = 0"
					   + " where id = ?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, id);
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
			String sql = "select RNO from Chatserver"
					   + " group by RNO";
			pstmt1 = con.prepareStatement(sql);
			ResultSet rs = pstmt1.executeQuery();
			String msg = "방 번호 ";
			int i = 0;
			while(rs.next()) {
				msg += "[" + i + "] ";
				if(rs.getInt("RNO") == 0) {
					msg += "대기실\n";
				} else {
					msg += (String) rs.getString("RNO") + "번방\n";	
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
	public void makeRoom(String id, String msg) {
		connectDatabase();
		try {
			String sql = "update Chatserver"
					   + "   set RNO = rno_seq.nextval"
					   + " where id = ?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, id);
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
	
	// 방으로 이동
	public void moveToRoom(String id, String msg) {
		connectDatabase();
		int rno = Integer.parseInt(msg);
		try {
			String sql = "update Chatserver"
					   + "   set RNO = ?"
					   + " where id = ?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setInt(1, rno);
			pstmt1.setString(2, id);
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
			String sql = "update Chatserver"
					   + "   set RNO = 0"
					   + " where id = ?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, id);
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

	public void menu(PrintWriter out, Map<String, PrintWriter> clientMap, String s, String id) {
		if (s.equals("/list")) {
			list(out, clientMap);
		} else if (s.indexOf("/blockall") != -1) {
			blockall(out, s.substring(s.indexOf(" ")+1));
		} else if (s.indexOf("/allowWord") != -1) {
			allowWord(out, s.substring(s.indexOf(" ")+1));
		} else if (s.equals("/create")) {
			makeRoom(id, s.substring(s.indexOf(" ")+1));
		} else if (s.indexOf("/moveto") != -1) {
			moveToRoom(id, s.substring(s.indexOf(" ")+1));
		} else if (s.equals("/exit")) {
			exitRoom(id);
		} else if (s.indexOf("/kick") != -1) {
			kickUser(s.substring(s.indexOf(" ")+1));
		}
		else if (s.equals("/roomlist")) {
			showRoomList(out);
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