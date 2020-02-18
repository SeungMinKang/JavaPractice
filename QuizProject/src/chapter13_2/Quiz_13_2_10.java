package chapter13_2;

public class Quiz_13_2_10 {
	public static void main(String[] args) {
		Arrays arrays = new Arrays();
		int[][] arr = new int[4][4];
		
		arrays.inputNum();
		arrays.showArray();	
		
		for(int i = 0; i < 4; i++) {
			arrays.moveNum();
			arrays.showArray();	
		}
	}
}

class Arrays {
	int[][] arr1 = new int[4][4];
	
	int num = 1;
	
	void inputNum() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				arr1[i][j] = num;
				num++;
			}
		}
	}
	
	void moveNum() {
		int[][] arr2 = new int[4][4];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				arr2[j][3-i] = arr1[i][j];
			}
		}
		arr1 = arr2;
	}
	
	void showArray() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				System.out.print(arr1[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}