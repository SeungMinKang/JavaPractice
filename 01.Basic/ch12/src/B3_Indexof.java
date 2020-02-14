//
// 문자열의 위치 찾기
//

class B3_Indexof {
	public static void main(String[] args) {
		String st1 = "AppleBananaOrange";
		int num1 = st1.indexOf("B");
		int num2 = st1.indexOf("O");
		System.out.println(num1);
		System.out.println(num2);
		
		String st2 = st1.substring(num1,num2);
		System.out.println(st2);
	}
}
