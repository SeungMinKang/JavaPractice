package chapter06;

public class Quiz_06_11 {

	public static void main(String[] args) {
		// 1부터 100까지의 정수 중 짝수의 합을 구하기
		int num = 1;
		int sum = 0;
		
		do {
			if(num % 2 == 0) {
				sum = sum + num;
			}
			num++;
		} while(num < 101);
		System.out.print("1부터 100까지의 정수 중 짝수의 합은 " + sum + " 입니다.");
	}
}
