package chapter06;
import java.util.Scanner;

public class Quiz_06_03 {

	public static void main(String[] args) {
		//정수 하나 입력
		//그 수의 팩토리얼 함수의 결과를 출력
		// <-- while 문 사용
		// 5 : 5 * 4 * 3 * 2 * 1
		
		//사용자한테 한 개의 정수를 입력받아라
		Scanner sc = new Scanner(System.in);
		
		System.out.print("숫자를 입력하세요. ");
		int num = sc.nextInt();
		int ini = num;
		int fac = 1;
		
		while(num > 0) {
			if(num == 1) {
				System.out.println(num + " = " + fac);
			}
			System.out.print(num + " * ");
			fac = fac * num;
			num--;
		}
	}
}
