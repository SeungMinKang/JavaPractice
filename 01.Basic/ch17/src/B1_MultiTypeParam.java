class Box7<L, R> {
	private L left;		// 왼쪽 수납 공간
	private R right;	// 오른쪽 수납 공간
	
	public void set(L o, R r) {
		left = o;
		right = r;
	}
	
//	public void set(L l, R r) {
//		left = l;
//		right = r;
//		if(r.getClass() == Integer.class) {	// 이런식으로 들어오는 형태에 따른
//			System.out.println("Hi");		// if 문도 가능
//		}
//	}
	
	@Override
	public String toString() {
		return left + " & " + right;
	}
}

public class B1_MultiTypeParam {
	public static void main(String[] args) {
		Box7<String, Integer> box = new Box7<String, Integer>();
		box.set("Apple" , 25);
		System.out.println(box);
	}
}