package chapter13_2;

import java.util.Scanner;

class Quiz_13_2_09{
	public static void main(String[] args) {
		// 배열에 순서대로 데이터를 입력하기
		// 210, 19, 72, 129, 34
		// 데이터를 정렬하기
		
		// hungarian bubble sort를 참고해보자
		
//		int[] arr = {210, 19, 72, 129, 34};
//		for (int i = 0; i<arr.length; i++) {
//			if(arr[i] > arr[i+1]) {
//				int tmp = arr[i];
//				arr[i] = arr[i+1];
//				arr[i+1] = tmp;
//			}
//		}
		
		ArraySet arrayset = new ArraySet();
		
		for(int i = 0; i < 5; i++) {
			arrayset.inputNum();
			arrayset.judgeNum();
		}
		arrayset.showArray();
	}
}

class ArraySet {
	Scanner sc = new Scanner(System.in);
	int[] ar1 = new int[5];
	int temp = 0;		// temp 변수를 이용하여 두 값의 위치를 바꿀 때
						// 임시저장소로 활용
	
	int i = 0;
		
	void inputNum() {
		System.out.print("숫자를 입력하세요: ");
		ar1[i] = sc.nextInt();		
		i++;
	}
	
	void judgeNum() {
		for(int j = i-1; j > 0; j--) {
			if(ar1[j] < ar1[j-1]) {
				temp = ar1[j];
				ar1[j] = ar1[j-1];
				ar1[j-1] = temp;
			}
		}
	}
	
	void showArray() {
		for(int i = 0; i < 5; i++) {
			System.out.print(ar1[i] + " ");
		}
	}
}