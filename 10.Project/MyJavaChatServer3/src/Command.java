import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

public class Command {
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
	
	public void ignore(PrintWriter out, Map<String, PrintWriter> clientMap) {
		
	}
}