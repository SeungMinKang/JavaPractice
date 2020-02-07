import java.util.Scanner;

public class Quiz_02_01 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("두 개의 정수를 입력하세요");

		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		
		int sum = num1 + num2;
		int sub = num1 - num2;
		int mul = num1 * num2;
		int quo = num1 / num2;
		int rem = num1 % num2;
		
		System.out.println(num1 + "와(과) " + num2 + "의 합은 " + sum + "입니다.");
		System.out.println(num1 + "에서 " + num2 + "를 빼면 " + sub + "입니다.");
		System.out.println(num1 + "와(과) " + num2 + "의 곱은 " + mul + "입니다.");
		System.out.println(num1 + "를(을) " + num2 + "로(으로) 나눈 몫은 " + quo + "이고, 나머지는 " + rem + "입니다.");
	}
}
