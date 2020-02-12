package Game;

import java.util.Random;

public class QuizRandom {

	public static void main(String[] args) {
		
		Random rnd1 = new Random();
		
		while(true) {
			int num1 = rnd1.nextInt(9) + 1;
			int num2 = rnd1.nextInt(10);
			int num3 = rnd1.nextInt(10);
			if(num1 != num2 && num1 != num3 && num2 != num3) {
				System.out.printf("%d%d%d", num1,num2,num3);
				break;
			}
//			if(num1 == num2) {
//				if(num1 == num3) {
//					System.out.printf("%d%d%d 등 안됨 : %d이(가) 세개임", num1, num2, num3, num1);
//					break;
//				}else {
//					System.out.printf("%d%d%d 등 안됨 : %d이(가) 두개임", num1, num2, num3, num1);
//					break;
//				}
//			}
//			else if(num1 == num3) {
//				if(num1 == num2) {
//					System.out.printf("%d%d%d 등 안됨 : %d이(가) 세개임", num1, num2, num3, num1);
//					break;
//				}else {
//					System.out.printf("%d%d%d 등 안됨 : %d이(가) 두개임", num1, num2, num3, num1);
//					break;
//				}
//			}
//			else if(num2 == num3) {
//				if(num2 == num1) {
//					System.out.printf("%d%d%d 등 안됨 : %d이(가) 세개임", num1, num2, num3, num2);
//					break;
//				}else {
//					System.out.printf("%d%d%d 등 안됨 : %d이(가) 두개임", num1, num2, num3, num2);
//					break;
//				}
//			}
		}
	}
}