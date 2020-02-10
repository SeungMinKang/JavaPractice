import java.util.Scanner;

public class Quiz_06_09 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 사용자로부터 정수 하나를 입력 받기
		System.out.print("정수를 입력하세요: ");
		int i = sc.nextInt();
		int j;
		// 입력 받은 수에 해당하는 구구단을 역순으로 출력하기
		j = 9;
		System.out.println("-----------------------");
		while(j > 0) {
			System.out.printf("%d X %d = %d \n", i, j, (i*j));
			j--;
		}
		System.out.println("-----------------------");
	}
}
