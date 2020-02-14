public class A4_Switch {
	public static void main(String[] args) {
		String str = "two";
		// switch는 객체를 비교하는 것이 아니다.
		// two.equals(two)
		// 두 문자열의 내용을 비교한다.
		
		switch (str) {
		case "one":
			System.out.println("one");
			break;
		case "two":
			System.out.println("two");
			break;
		default:
			System.out.println("default");
		}
	}
}