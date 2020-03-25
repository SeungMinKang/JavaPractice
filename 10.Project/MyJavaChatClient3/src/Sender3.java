
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Sender3 extends Thread
{
	Socket socket;
	PrintWriter out = null;
	String id;
	
	// 생성자
	public Sender3(Socket socket, String id)
	{
		this.socket = socket;
		try {
			out = new PrintWriter(this.socket.getOutputStream(), true);
			this.id = id;
		} catch(Exception e) {
			System.out.println("예외S3:"+e);
		}
	}
	
	//run()메소드 재정의
	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		
		try {
			// 서버에 입력한 사용자 이름을 보내준다.
			out.println(id);
			
			while(out != null) {
				try {
					String s2 = sc.nextLine();
					if(s2.equals("q") || s2.equals("Q")) {
						//out.println(s2);
						break;
					} else {
						// 명령어|상대방ID|대화내용
						// /chat|all|s2
						// /to|홍길동|s2
						// /list||
						out.println(s2);
					}
					
				} catch(Exception e) {
					System.out.println("예외S1:"+e);
				}
			}
			
			out.close();
			
			socket.close();
			
		} catch(Exception e) {
			System.out.println("예외S2:"+e);
		}
	}
}