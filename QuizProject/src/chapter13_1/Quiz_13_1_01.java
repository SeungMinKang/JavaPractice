package chapter13_1;

import java.util.Scanner;

public class Quiz_13_1_01 {
	
	public static int Min(int[] num) {
		int min = num[0];
		
		for(int i  = 0; i < num.length; i++) {
			if(min > num[i]) {
				min = num[i];
			}
		}
		return min;
	}
	
	public static int Max(int[] num) {
		int max = num[0];
		
		for(int i  = 0; i < num.length; i++) {
			if(max < num[i]) {
				max = num[i];
			}
		}
		return max;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] num = new int[5];
		int sum = 0;
		
		for(int i = 0; i < 5; i++) {
			System.out.print("정수를 입력하세요: ");
			num[i] = sc.nextInt();
			sum = sum + num[i];
		}
		
		System.out.println("합: "+ sum);
		System.out.println("최대값: " + Max(num));
		System.out.println("최소값: " + Min(num));
		
		sc.close();
	}
}
