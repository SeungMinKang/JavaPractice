public class Exam04 {
	public static void main(String[] args) {
		System.out.println("Hello World");
		
		int num1 = 10;
		int num2 = 20;
		
		System.out.println(num1 + num2);
		System.out.println(num1 - num2);
		System.out.println(num1 * num2);
		System.out.println(num1 / num2);
		
		int kor = 75;
		int math = 80;
		int eng = 90;
		
		int sum = kor + math + eng;
		int average = sum / 3;
		
		System.out.println("총점 : " + sum);
		System.out.println("평균 : " + average);
		
		for(int i=2; i<10; i++) {
			for(int j=1 ; j<10; j++) {
				System.out.print(i + " X " + j + " = " + i*j + "\n");
				if(j == 9) {
					System.out.print("-----------\n");
				}
			}
		}
		
		calc(3,6);
	}
	
	public static void calc(int num1, int num2) {
		int plus = num1 + num2;
		int minus = num1 - num2;
		int multiply = num1 * num2;
		int divide = num1 / num2;
		
		System.out.println(num1 + " + " + num2 + " = " + plus);
		System.out.println(num1 + " - " + num2 + " = " + minus);
		System.out.println(num1 + " * " + num2 + " = " + multiply);
		System.out.println(num1 + " / " + num2 + " = " + divide);
	}
}
