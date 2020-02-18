public class Exam99Dan {
	public static void main(String[] args) {
//		for(int i =9; i>1; i--) {					// 구구단을 역순으로 출력
//			for(int j = 9; j > 0; j--) {
//				System.out.print(i + " X " + j + " = " + (i*j) + "\t");
//			}
//			System.out.println();		
//		}
		
		
		for(int i =2; i<10; i++) {					// 기본 구구단
			for(int j = 1; j < 10; j++) {
				System.out.printf("%d X %d = %d\t", i, j, (i*j));
			}
			System.out.println();		
		}
	}
}
