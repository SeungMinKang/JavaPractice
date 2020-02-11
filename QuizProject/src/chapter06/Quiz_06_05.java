package chapter06;
import java.util.Scanner;

public class Quiz_06_05 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num;
		int sum = 0;
		
		System.out.print("숫자를 입력하세요.\n0을 입력하시면 합을 보여드립니다. \n");
		while(true) {
			System.out.print("숫자: ");
			num = sc.nextInt();
			if(num == 0) {
				System.out.println("입력한 모든 수의 합은 " + sum + " 입니다.");
				break;
			}
			sum = sum + num;
		}
	}
}
