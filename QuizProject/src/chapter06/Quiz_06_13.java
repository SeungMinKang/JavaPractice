package chapter06;

public class Quiz_06_13 {

	public static void main(String[] args) {
		// 중첩 for 문을 사용하여 구구단 전체를 출력하기
		
		// 구구단 세로형
		System.out.println("가로형");
		for(int i = 1; i < 10; i++) {
			for(int j = 2; j < 10; j++) {
				System.out.printf("%d X %d = %d \t", j, i, (j*i));
				if(j == 9) {
					System.out.print("\n");
				}
			}
		}
		System.out.println("\n세로형");
		
		// 구구단 가로형
		for(int i = 2; i < 10; i++) {
			for(int j = 1; j < 10; j++) {
				System.out.printf("%d X %d = %d \t", i, j, (j*i));
				if(j == 9) {
					System.out.print("\n \n");
				}
			}
		}
	}
}
