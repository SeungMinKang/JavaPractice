
public class E3_ByTimesWhile {

	public static void main(String[] args) {
		int num1 = 2;
		
		while(num1 < 10) {
			System.out.println("-------------------------");
			
			int num2 = 1;
			while(num2 < 10) {
				System.out.printf("%d X %d = %d \n",num1, num2, (num1 * num2));
				num2++;
			}
			num1++;
		}
	}
}
