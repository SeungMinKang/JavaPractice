package chapter13_2;

public class Quiz_13_2_06 {
	public static void main(String[] args) {
		// 가로가 9, 세로가 3인 int형 2차원 배열을 선언
		// 구구단 중 2, 3, 4 단을 저장
		// 저장을 확인하기 위해 출력
		
		int[][] arr = new int[3][9];
		int num;
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				num = ((i+2)*(j+1));
				arr[i][j] = num;
			}
		}
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				System.out.print((i+2) + " X " + (j+1) + " = " + arr[i][j] + "\t");
			}
			System.out.println();
		}
	}
}