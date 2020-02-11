package chapter06;
import java.util.Scanner;

public class Quiz_06_02 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("평균 점수 계산 프로그램입니다.");
		System.out.print("국어 점수를 입력하세요 : ");
		int kor = sc.nextInt();
		
		System.out.print("영어 점수를 입력하세요 : ");
		int eng = sc.nextInt();
		
		System.out.print("수학 점수를 입력하세요 : ");
		int mat = sc.nextInt();
		
		int avg = (kor + eng + mat) / 3;
		
		System.out.println("평균 점수는 " + avg + " 입니다.");
		if(avg >= 90) {
			System.out.println("당신의 성적은 A 입니다.");
		}
		else if(avg >= 80 && avg < 90) {
			System.out.println("당신의 성적은 B 입니다.");
		}
		else if(avg >= 70 && avg < 80) {
			System.out.println("당신의 성적은 C 입니다.");
		}
		else if(avg >= 50 && avg < 70) {
			System.out.println("당신의 성적은 D 입니다.");
		}
		else {
			System.out.println("당신의 성적은 F 입니다.");
		}
	}
}
