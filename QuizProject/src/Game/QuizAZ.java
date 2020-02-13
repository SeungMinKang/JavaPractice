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
			System.out.printf("\tA = %d\tZ = %d\n\n", i, j);
		}
		
//		for(int i=0; i<100; i++) {
//			// i = 15
//			int A = i / 10;
//			int Z = i % 10;
//			
//			if(A + Z == 9){
//				System.out.printf("A = %d, Z = %d \n", A, Z);				
//			}
//		}
		
//		int A = 0, Z = 0;
//		
//		for(A = 0; A < 10; A++) {
//			for(Z = 0; Z < 10; Z++) {
//				int num1 = A * 10 + Z;
//				int num2 = Z * 10 + A;
//				
//				if(A + Z == 9) {
//					if(A != Z) {
//						System.out.println(num1 + " " + num2);
//					}
//				}
//			}
//		}
	}
}