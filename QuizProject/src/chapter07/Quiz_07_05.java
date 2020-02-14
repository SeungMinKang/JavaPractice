package chapter07;
import java.util.Scanner;

public class Quiz_07_05 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] ask = new String[3];
		ask[0] = new String("정수를 입력하세요: ");
		ask[1] = new String("정수를 입력하세요: ");
		ask[2] = new String("정수를 입력하세요: ");

		int[] num = new int[3];
		int zero = 0;
		
		int result = 0;
		
		while(true) {
			for(int i = zero; i < 3; i++) {
				System.out.print(ask[i]);
				num[i] = sc.nextInt();			
			}
			if(num[2] == 1) {
				result = plus(num[0], num[1]);
				break;
			}else if(num[2] == 2) {
				result = minus(num[0], num[1]);
				break;
			}else if(num[2] == 3) {
				result = multiply(num[0], num[1]);
				break;
			}else if(num[2] == 4) {
				result = divide(num[0], num[1]);
				break;
			}else{
				System.out.println("마지막 숫자는 1과 4 사이의 숫자를 눌러주세요.");
			}
		}
		System.out.println("결과: " + result);
		
		sc.close();
	}

	public static int plus(int num1, int num2) {
		int result = 0;
		result = num1 + num2;
		return result;
	}
	
	public static int minus(int num1, int num2) {
		int result = 0;
		result = num1 - num2;
		return result;
	}
	
	public static int multiply(int num1, int num2) {
		int result = 0;
		result = num1 * num2;
		return result;
	}
	
	public static int divide(int num1, int num2) {
		int result = 0;
		result = num1 / num2;
		return result;
	}
}
