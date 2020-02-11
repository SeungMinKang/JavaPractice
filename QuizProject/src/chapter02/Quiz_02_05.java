package chapter02;
import java.util.Scanner;

public class Quiz_02_05 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("두 개의 정수를 입력하세요");
		
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		
		int mul = num1 * num2;
		
		System.out.println(num1 + "와(과) " + num2 + " 의 곱의 절대값은 " + (mul >= 0 ? mul : -mul) + " 입니다.");

	}

}
