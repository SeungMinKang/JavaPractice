package chapter07;
import java.util.Scanner;

public class Quiz_07_02 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수를 입력하세요: ");
		int num1 = sc.nextInt();
		System.out.print("정수를 입력하세요: ");
		int num2 = sc.nextInt();
		System.out.print("정수를 입력하세요: ");
		int num3 = sc.nextInt();

		int bnb = bignumber(num1,num2,num3);
		System.out.println("가장 큰 수는 " + bnb);
	}
	
	public static int bignumber(int i, int j, int k) {
		int big = 0;
		if(i >= j && i >= k) {
			big = i;
		}
		else if(j >= i && j >= k) {
			big = j;
		}
		else if(k >= i && k >= j) {
			big = k;
		}
		return big;

	}
}
