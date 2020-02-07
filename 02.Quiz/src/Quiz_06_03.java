import java.util.Scanner;

public class Quiz_06_03 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("숫자를 입력하세요. ");
		int num = sc.nextInt();
		int ini = num;
		int fac = 1;
		
		while(num > 0) {
			fac = fac * num;
			num--;
		}
		System.out.println(ini + "!의 값은 " + fac + " 입니다.");
	}
}
