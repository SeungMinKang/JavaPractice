package ch20;

interface Calculate4 {
	int cal(int a, int b);
}

public class E3_TwoParamAndReturn {
	public static void main(String[] args) {
		Calculate4 c;
		c = (a, b) -> {return a + b;};
		// int num = c.cal(4, 3);	// 이와 같이 연산 결과가 남는다.
		System.out.println(c.cal(4, 3));
		
		c = (a, b) -> a + b;
		System.out.println(c.cal(4, 3));
	}
}

// 메서드 몸체에 해당하는 내용이 return 문이면
// 그 문장이 하나이더라도 중괄호의 생략이 불가능하다.
// 구현부가 반환문 하나라면 return 자체는 생략이 가능하다.
// return을 생략하고 나면... 중괄호 생략이 가능하다.
