import java.util.Scanner;

public class Quiz_06_10 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 사용자로부터 5개의 정수를 입력 받기
		
		int num;
		int count = 0;
		int sum = 0;
		
		// 1 미만의 숫자를 입력하면 count가 올라가지 않게 처리하기
		while(true) {
			System.out.print("숫자를 입력하세요: ");
			num = sc.nextInt();
			while(num < 1) {
				System.out.print("1 이상의 숫자를 입력하세요: ");
				num = sc.nextInt();
			}
			sum = sum + num;
			count++;
			if(count == 5) {
				break;
			}
		}
		System.out.printf("5개 정수의 합은 %d 입니다.", sum);
	}
}
