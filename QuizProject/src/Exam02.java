public class Exam02 {
	public static void main(String[] args) {
		int num1 = 10;
		double num2 = 10.0;
		String str1 = "홍길동";
		String str2 = new String("전우치");
		System.out.println(str1 + num1 + num2);			// 연산자의 우선순서로 str1 과 num1이 계산된 후 그 결과가 num2와 계산된다.
		
		myPrint(str1 + (num1 + num2));
		double num3 = add(num1, num2);					// int 자료형인 num1이 add method로 들어가면서 double형으로 자동 형변환 된다.
		
		for(int i=0; i<=num1; i++) {
			if((i % 2) == 0) {
				System.out.println("짝수" + i);
//			} else {
//				System.out.println("홀수" + i);
			}
		}
		
		int i = 1;
		while(i <= 10) {
			if((i % 2) == 0) {
				System.out.println("짝수" + i);
			}
			i++;
		}
	}

	public static void myPrint(String str) {
		// 다음을 한 50줄 정도로 생각
		System.out.println(str);
	}
	
	public static double add(double n1, double n2) {
		return n1 + n2;
	}
	
	public static double add(int n1, int n2) {
		return n1 + n2;
	}
}
