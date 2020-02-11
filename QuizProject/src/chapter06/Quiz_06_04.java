package chapter06;

public class Quiz_06_04 {

	public static void main(String[] args) {
		int num = 1;
		int sum = 0;
		
		do {
			sum = sum + num;
			if(num < 1000) {
				System.out.print(num + " + ");
			}
			if(num == 1000) {
				System.out.println(num + " = " + sum);
			}
			num++;
		} while(num < 1001);
	}
}
