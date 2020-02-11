package chapter02;
import java.util.Scanner;

public class Quiz_02_04 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("두 개의 정수를 입력하세요");
		
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		
		System.out.println(num1 + "와(과) " + num2 + " 중 큰 값은 " + (num1 > num2 ? num1 : num2) + " 입니다.");
	}
}
