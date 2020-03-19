import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CommandTest {

	public static void main(String[] args) {
		int n = 5;
		Scanner sc = new Scanner(System.in);

		Map<String, String> test = new HashMap<>();
		test.put("감자", "고구마");
		test.put("감자1", "고구마1");
		test.put("감자2", "고구마2");
		test.put("감자3", "고구마3");
		test.put("감자4", "고구마4");

		Set<String> ks = test.keySet();

		Iterator<String> it = test.keySet().iterator();

		System.out.print("입력: ");
		String s = sc.nextLine();
		s.substring(0);

		if (s.equals("/list")) {
			try {
				int i = 0;
				System.out.println(ks);
			} catch (Exception e) {
				System.out.println("예외:" + e);
			}
		}

	}

}
