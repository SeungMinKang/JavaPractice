import java.util.Scanner;

public class Quiz_06_06 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int inum;
		int num = 0;
		int dec;
		int sum = 0;
		int avg;
		
		System.out.print("입력하실 정수의 갯수가 몇개입니까? ");
		for(inum = sc.nextInt(); num < inum; num++) {
			dec = sc.nextInt();
			sum = sum + dec;
		}
		avg = sum / inum;
		System.out.println("입력 받은 숫자들의 평균값은 " + avg + "입니다.");
	}
}
