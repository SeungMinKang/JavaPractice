package chapter13_1;

import java.util.Scanner;

public class Quiz_13_1_03 {
	private static int oddcount = 0;
	private static int evencount = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] num = new int[10];
		
		for(int i = 0; i < 10; i++) {
			System.out.print("정수를 입력하세요: ");
			num[i] = sc.nextInt();
		}
		
		for(int i = 0; i < num.length; i++) {
			if(num[i] % 2 != 0) {
				oddcount = oddcount + 1;			
			}else {
				evencount = evencount + 1;
			}
		}
		
		odd(num);
		
		System.out.println();
		
		even(num);
		
		sc.close();
	}
	
	public static void odd(int[] num) {
		int j = 0;
		int[] odd = new int[oddcount];
		for(int i = 0; i < num.length; i++) {
			if(num[i] % 2 != 0) {
				odd[j] = num[i];
				j++;
			}
		}
		System.out.print("홀수: ");
		for(int i = 0; i < oddcount; i++) {
			System.out.print(odd[i] + " ");
		}
	}
	
	public static void even(int[] num) {
		int j = 0;
		int[] even = new int[evencount];
		for(int i = 0; i < num.length; i++) {
			if(num[i] % 2 == 0) {
				even[j] = num[i];
				j++;
			}
		}
		System.out.print("짝수: ");
		for(int i = 0; i < evencount; i++) {
			System.out.print(even[i] + " ");
		}
	}

}
