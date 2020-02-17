package chapter13_2;

public class Quiz_13_2_07 {
	public static void main(String[] args) {
		int[][] ar1 = new int[2][4];
		int[][] ar2 = new int[4][2];
		int num = 1;
		
		for(int i = 0; i < ar1.length; i++) {
			for(int j = 0; j < ar1[i].length; j++) {
				ar1[i][j] = num;
				num++;
			}
		}
		
		for(int i = 0; i < ar2.length; i++) {
			for(int j = 0; j < ar2[i].length; j++) {
				ar2[i][j] = ar1[j][i];
			}
		}
		
		for(int i = 0; i < ar2.length; i++) {
			for(int j = 0; j < ar2[i].length; j++) {
				System.out.print(ar2[i][j] + " ");
			}
			System.out.println();
		}
	}
}