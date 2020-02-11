import java.util.Scanner;

public class Quiz_07_01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수를 입력하세요: ");
		int num1 = sc.nextInt();
		
		System.out.print("정수를 입력하세요: ");
		int num2 = sc.nextInt();
		
		System.out.print("정수를 입력하세요: ");
		int num3 = sc.nextInt();

		int avg = average(num1, num2 ,num3);
		System.out.println("평균은 " + avg);
	}

	public static int average(int i, int j, int k) {
		int avg = (i + j + k)/3;
		return avg;
	}
}
