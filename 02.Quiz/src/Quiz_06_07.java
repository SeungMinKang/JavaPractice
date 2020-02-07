import java.util.Scanner;

public class Quiz_06_07 {

	public static void main(String[] args) {
		
		int num = 1;
		
		while(num++ < 100) {
			if(((num % 7 == 0) && (num % 9 == 0))) {
				System.out.println(num);
			}
			else if(num % 7 == 0) {
				System.out.println(num);
			}
			else if(num % 9 == 0) {
				System.out.println(num);
			}
		}
	}
}
