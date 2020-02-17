package chapter13_1;

import java.util.Scanner;

public class Quiz_13_1_04 {

	public static void main(String[] args) {
		// 길이가 10인 배열 선언
		// 10개의 정수 입력 받기
		// 홀수는 배열의 앞부터 채워나가고
		// 짝수는 배열의 뒤부터 채워나간다.
		// 그리고 출력
		// 1 1 3 7 9 6 4 2		
		
		Scanner sc = new Scanner(System.in);
		
		int[] num1 = new int[10];
		int[] num2 = new int[10];
		
		int j = 0;		// 홀수의 인덱스 값
		int k = 9;		// 짝수의 인덱스 값

		for(int i = 0; i < num1.length; i++) {
			System.out.print("정수를 입력하세요: ");
			num1[i] = sc.nextInt();

			if(num1[i] % 2 != 0) {
				// 홀수
				num2[j] = num1[i];
				j++;
			}else {
				// 짝수
				num2[k] = num1[i];
				k--;
			}
		}
		for(int i = 0; i < num1.length; i++) {
			System.out.print(String.valueOf(num2[i] + " "));
		}
		sc.close();
	}
}
