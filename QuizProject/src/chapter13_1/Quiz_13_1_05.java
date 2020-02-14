package chapter13_1;

import java.util.Scanner;

public class Quiz_13_1_05 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder st1 = new StringBuilder();
		
		System.out.print("문자열을 입력하세요: ");
		String str1 = sc.next();
		
		st1 = st1.append(str1);
		String str2 = (new StringBuilder(str1).reverse().toString());
		
		System.out.println(str1);
		System.out.println(str2);
		
		if(str1.equals(str2)) {
			System.out.println("회문입니다.");
		}else {
			System.out.println("회문이 아닙니다.");
		}
		sc.close();
	}

}
