class Book{
	int price;
}

public class MyTest {

	public static void main(String[] args) {
		int num1 = 10;
		int num2 = 20;
		int num3 = adder(num1, num2);
		
		Book bk1 = new Book();
		bk1.price = 1000;
		
		Book bk2 = new Book();
		bk2.price = 2000;
		
		System.out.println("end of program");
	}

	public static int adder(int n1, int n2) {
		int result = n1 + n2;
		return result;
	}
}
