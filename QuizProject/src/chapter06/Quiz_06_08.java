package chapter06;

public class Quiz_06_08 {

	public static void main(String[] args) {
		for(int i = 2; i < 10; i = i + 2) {
			System.out.println("-----------------------");
			for(int j = 1; j <= i; j++) {
				System.out.printf("%d X %d = %d \n", i, j, (i*j));
			}
		}
		System.out.println("-----------------------");
	}
}
