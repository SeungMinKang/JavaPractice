import java.util.Scanner;

public class Quiz_07_05 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int result = 0;
		
		while(true) {			
			System.out.print("정수를 입력하세요: ");
			int num1 = sc.nextInt();
			System.out.print("정수를 입력하세요: ");
			int num2 = sc.nextInt();
			System.out.print("정수를 입력하세요: ");
			int num3 = sc.nextInt();
			if(num3 == 1) {
				result = plus(num1, num2);
				System.out.println();
				break;
			}else if(num3 == 2) {
				result = minus(num1, num2);
				break;
			}else if(num3 == 3) {
				result = multiply(num1, num2);
				break;
			}else if(num3 == 4) {
				result = divide(num1, num2);
				break;
			}else{
				System.out.println("마지막 숫자는 1과 4 사이의 숫자를 눌러주세요.");
			}
		}
		System.out.println("결과: " + result);
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
