package chapter07;
import java.util.Scanner;

public class Quiz_07_03 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수를 입력하세요: ");
		int num1 = sc.nextInt();
		System.out.print("정수를 입력하세요: ");
		int num2 = sc.nextInt();
		System.out.print("정수를 입력하세요: ");
		int num3 = sc.nextInt();
		
		int snb = smallnumber(num1,num2,num3);
		System.out.println("가장 작은 수는 " + snb);
	}

	public static int smallnumber(int i, int j, int k) {
		int sml = 0;
		if(i <= j && i <= k) {
			sml = i;
		}
		else if(j <= i && j <= k) {
			sml = j;
		}
		else if(k <= i && k <= j) {
			sml = k;
		}
		return sml;
	}
}
