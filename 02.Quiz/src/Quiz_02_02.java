import java.util.Scanner;

public class Quiz_02_02 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("숫자를 입력하세요");
		
		int num1 = sc.nextInt();
		int sqr = num1 * num1;
		
		System.out.println(num1 + "의 제곱은 " + sqr + "입니다.");
	}
}
