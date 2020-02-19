class INum extends Object{
	private int num;
	
	public INum(int num) {
		this.num = num;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this.num == ((INum)obj).num)
			return true;
		else
			return false;
	}
}

public class B1_ObjectEquality {
	public static void main(String[] args) {
		INum num1 = new INum(10);
		INum num2 = new INum(12);
		INum num3 = new INum(10);
		
		if(num1.equals(num2)) 
			System.out.println("num1, num2 내용 동일하다.");
		else
			System.out.println("num1, num2 내용 다르다.");
		
		if(num1.equals(num3))
			System.out.println("num1, num3 내용 동일하다.");
		else
			System.out.println("num1, num3 내용 다르다.");
	}
}

//
// 인스턴스의 비교 : equlas 메서드
//
// 인스턴스의 내용 비교를 위한 기능을 equals 메서드에 담아 정의한다.
// equals는 Object 클래스의 메서드이다.