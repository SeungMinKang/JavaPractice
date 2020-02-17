public class C1_ArrayIndexOutOfBounds {
	public static void main(String[] args) {
		int[] arr = {1, 2, 3};
		for(int i = 0; i < 4; i++) {
			System.out.println(arr[i]);
		}
	}
}

// 인덱스 값 3은 예외를 발생시킴