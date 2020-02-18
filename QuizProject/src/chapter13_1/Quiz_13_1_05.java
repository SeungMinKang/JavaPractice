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


		// 선생님 풀이방법
		// 사용자한테 문자열을 입력 받아라
		
//		System.out.print("입력하세요 : ");
//////	String str2 = sc.next();			// space, tab, enter 등으로 값을 구분
//		String str = sc.nextLine();			// enter로만 값을 구분
//		
//		boolean b1 = true;
//		
//		int len = str.length();
//		String[] arr = new String[len];
//
//		for(int i=0; i<len; i++) {
//			arr[i] = str.substring(i, i+1);
//		}
//		
//		String[] arr2 = new String[len];
//		for(int i =0; i < len; i++) {
//			arr2[len-1-i] = arr[i];
//		}
////		for(int i =0; i < len; i++) {
////			System.out.print(arr2[i]);
////		}
//		for (int i =0; i<len; i++) {
//			if (arr[i].compareTo(arr2[i]) != 0) // compareTo 대신 equals(arr2[i]) 를 이용하여 비교 가능
//			{	b1 = false;
//				break;
//			}
//		}
//		
//		// orange
//		// egnaro		
//		// n o
//		// n o
//		
//		if(b1) {
//			System.out.println(str + "은 회문입니다.");
//			return;
//		} else {
//			System.out.println(str + "은 회문이 아닙니다.");
//		}
		
	}
}
