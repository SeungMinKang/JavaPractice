package chapter13_1;

import java.util.Scanner;

public class Quiz_13_1_04 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] num1 = new int[10];
		int[] num2 = new int[10];
		
		int j = 0;
		int k = 9;		

		for(int i = 0; i < num1.length; i++) {
			System.out.print("정수를 입력하세요: ");
			num1[i] = sc.nextInt();

			if(num1[i] % 2 != 0) {
				num2[j] = num1[i];
				j++;
			}else {
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
