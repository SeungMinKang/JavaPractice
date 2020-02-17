package chapter13_2;

import java.util.Scanner;

public class Quiz_13_2_08 {
	public static void main(String[] args) {
		// 4 by 4 배열을 선언하기
		// 사용자로부터 4사람의 4과목 점수를 입력 받아 값을 저장하기
		Scanner sc = new Scanner(System.in);
		int[][] grade = new int[4][4];
		int[][] paper = new int[5][5];
		
		for(int i = 0; i < grade.length; i++) {
			for(int j = 0; j < grade[i].length; j++) {
				say(i);
				grade[i][j] = sc.nextInt();
			}
		}
		paper = grade;
		sheet(paper);
		
		sc.close();		
	}
	
	static void say(int num) {
		if(num == 0)
			System.out.print("국어 점수를 입력하세요: ");
		if(num == 1)
			System.out.print("영어 점수를 입력하세요: ");
		if(num == 2)
			System.out.print("수학 점수를 입력하세요: ");
		if(num == 3)
			System.out.print("국사 점수를 입력하세요: ");
	}
	
	static void list(int num) {
		if(num == 0)
			System.out.print("국어\t");
		if(num == 1)
			System.out.print("영어\t");
		if(num == 2)
			System.out.print("수학\t");
		if(num == 3)
			System.out.print("국사\t");
	}
	
	static void sheet(int[][] num) {
		System.out.print("구분\t이순신\t강감찬\t을지문덕 권율\t총점\n");
		for(int i = 0; i < num.length; i++) {
			int sum = 0;
			list(i);
			for(int j = 0; j < num[i].length; j++) {
				System.out.print(num[i][j] + "\t");
				sum = sum + num[i][j];
			}
			System.out.print(sum + "\n");
			System.out.println();
		}
		System.out.print("총점\t");
		for(int j = 0; j < num.length; j++) {
			int sum = 0;
			for(int i = 0; i < num.length; i++) {
				sum = sum + num[i][j];
			}
			System.out.print(sum + "\t");
		}		
	}
}