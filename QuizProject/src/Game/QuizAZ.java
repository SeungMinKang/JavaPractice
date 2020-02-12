package Game;

public class QuizAZ {

	public static void main(String[] args) {
		// AZ
		//+ZA
		//----
		// 99  를 만족하는 숫자, 단 A != Z

		int sum = 9;
		
		for(int i = 0; i <= 9; i++) {
			int j = sum - i;
			System.out.printf(" %d%d\n+%d%d\n---\n 99",i, j, j, i);
			System.out.printf("\tA: %d\tZ: %d\n\n", i, j);
		}
	}
}