import java.util.Scanner;

public class Quiz_06_05 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num;
		int sum = 0;
		
		while(true) {
			num = sc.nextInt();
			if(num == 0) {
				System.out.println("입력한 모든 수의 합은 " + sum + " 입니다.");
				break;
			}
			sum = sum + num;
		}
	}
}
