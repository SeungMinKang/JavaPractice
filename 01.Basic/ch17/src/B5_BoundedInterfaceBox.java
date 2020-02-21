interface Eatable {
	public String eat();
}

class AppleB implements Eatable {
	public String toString() {
		return "I am an apple.";
	}
	
	@Override
	public String eat() {
		return "It tastes so good!";
	}
}

class BoxB<T extends Eatable> {	// 상속이나 구현 모두 extends이므로
								// implements가 들어갈 수 없다.
								// 다중 상속은 클래스 & 인터페이스 순서
	private T ob;
	
	public void set(T o) {
		ob = o;
	}
	
	// 한 입 먹고 반환하는 행위의 메소드로 수정
	public T get() {
		System.out.println(ob.eat());
		return ob;
	}
}

class B5_BoundedInterfaceBox {
	public static void main(String[] args) {
		BoxB<AppleB> box = new BoxB<>();
		box.set(new AppleB());
		
		AppleB ap = box.get();
		System.out.println(ap);
	}
}