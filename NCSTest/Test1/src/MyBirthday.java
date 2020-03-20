import java.time.LocalDate;
import java.util.Scanner;

public class MyBirthday {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LocalDate currentDate = LocalDate.now();
		
		// 태어난 년도를 저장할 변수 생성
		System.out.print("태어난 해를 입력하세요: ");
		int birthYear = sc.nextInt();
		
		// 나이를 저장할 변수 생성
		int age;
		age = currentDate.getYear() - birthYear;
		
		System.out.printf("나이: %d, 태어난 해: %d", age, birthYear);
	}
}