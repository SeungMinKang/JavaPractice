package chapter06;
import java.util.Scanner;

public class Quiz_06_12 {

	public static void main(String[] args) {
		// 사용자로부터 2 개의 정수를 입력 받기
		Scanner sc = new Scanner(System.in);
		
		System.out.print("숫자를 입력하세요: ");
		int num1 = sc.nextInt();
		System.out.print("숫자를 입력하세요: ");
		int num2 = sc.nextInt();
		int sum = 0;
		
		if(num2 > num1) {
			while(num1 < num2) {
				System.out.print(num1 + " + ");
				sum = sum + num1;
				num1++;
			}
			sum = sum + num2;
			System.out.print(num2 + " = " + sum);
		}
		else if(num1 > num2) {
			while(num2 < num1) {
				System.out.print(num1 + " + ");
				sum = sum + num1;
				num1--;
			}
			sum = sum + num1;
			System.out.print(num1 + " = " + sum);
		}
		else {
			sum = num1 + num2;
			System.out.printf("%d + %d = %d",num1, num2, sum);
		}
	}
}
