class Apple3 {
	public String toString() {
		return "I am an apple.";
	}
}

class Orange3 {
	public String toString() {
		return "I am an orange.";
	}
}

class Box3 {
	private Object ob;
	
	public void set(Object o) {
		ob = o;
	}
	public Object get() {
		return ob;
	}
}

class A3_FruitAndBoxFault {
	public static void main(String[] args) {
		Box3 aBox = new Box3();
		Box3 oBox = new Box3();
		
		// 과일을 박스에 담은 것일까?
		aBox.set("Apple");		//사과와 오렌지가 아닌
		oBox.set("Orange");		// '문자열'을 담았다.

		// 박스에서 과일을 제대로 꺼낼 수 있을까?
		Apple3 ap = (Apple3)aBox.get();		// String을 Appl3 클래스 형태로
		Orange3 og = (Orange3)oBox.get();	// 형변환을 시도하여 오류
		
		System.out.println(ap);
		System.out.println(og);
	}
}