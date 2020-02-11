package chapter07;
import java.util.Scanner;

public class Quiz_07_04 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while(true) {	
			System.out.print("1. 섭씨 -> 화씨 변환 \n2. 화씨 -> 섭씨 변환\n");
			int con = sc.nextInt();
			if(con == 1) {
				System.out.print("섭씨 온도를 입력하세요: ");
				double num1 = sc.nextDouble();
				double num2 = celtofah(num1);
				System.out.println("화씨 온도 = " + num2);
			}else if(con == 2) {
				System.out.print("화씨 온도를 입력하세요: ");
				double num1 = sc.nextDouble();
				double num2 = fahtocel(num1);
				System.out.println("섭씨 온도 = " + num2);
			}else {
				System.out.println("1과 2 중 골라주세요.");
			}
		}
	}
	
	// 섭씨 -> 화씨
	public static double celtofah(double cel) {
		double fah;
		fah = (1.8 * cel) + 32;
		return fah;
	}
	
	// 화씨 <- 섭씨
	public static double fahtocel(double fah) {
		double cel;
		cel = (fah - 32)/1.8;
		return cel;
	}
}
