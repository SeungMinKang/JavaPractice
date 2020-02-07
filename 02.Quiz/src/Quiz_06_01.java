import java.io.Closeable;
import java.util.Scanner;

public class Quiz_06_01 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("첫번째 숫자를 입력하세요");
		int num1 = sc.nextInt();
		
		System.out.print("두번째 숫자를 입력하세요");
		int num2 = sc.nextInt();
		
		if (num1 >= num2) {
			System.out.println("두 수의 차는 " + (num1 - num2) + " 입니다.");
		}
		else {
			System.out.println("두 수의 차는 " + (num2 - num1) + " 입니다.");
		}
	}
}
