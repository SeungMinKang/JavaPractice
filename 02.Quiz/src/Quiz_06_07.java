import java.util.Scanner;

public class Quiz_06_07 {

	public static void main(String[] args) {
		// 숫자의 범위 : 1 이상 100 미만의 정수
		// 7의 배수와 9의 배수를 출력
		// 7의 배수이면서 9의 배수인 경우는 한 번만 출력
		
		int num = 1;
		
		while(num++ < 100) {
			if(((num % 7 == 0) && (num % 9 == 0))) {
				System.out.println("7과 9의 공배수: " + num);
			}
			else if(num % 7 == 0) {
				System.out.println("7의 배수: " + num);
			}
			else if(num % 9 == 0) {
				System.out.println("9의 배수: " + num);
			}
		}
	
	
	 /* 	for (int i = 1; i < 100; i++){
	  		if(i % 7 == 0 || i % 9 == 0){
	  			System.out.println("7 또는 9의 배수 : " + i);
	  		}
	  	}
	 */
	 // for 문으로 했을 경우의 로직
	 
	}
}
