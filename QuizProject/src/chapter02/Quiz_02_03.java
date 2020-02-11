package chapter02;
import java.util.Scanner;

public class Quiz_02_03 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("두 개의 정수를 입력하세요");
		
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		int quo = num1 / num2;
		int rem = num1 % num2;
		
		System.out.println(num1 + " 나누기 " + num2 + " 의 몫은 " + quo + " 입니다.");
		System.out.println(num1 + " 나누기 " + num2 + " 의 나머지는 " + rem + " 입니다.");
	}
}
