class BoxD<T> {
	private T ob;
	
	public void set(T o) {
		ob = o;
	}
	public T get() {
		return ob;
	}
}

class UnboxerD {
	public static <T> T openBox(BoxD<T> box) {
		return box.get();
	}
//	'<T>T openBox(BoxD<T>box)'는 사실 맨앞 <T>는 없는 것이나 마찬가지 문법적인 것.
//	문법적으로 '<T> BoxC<T>' 이고 사실 리턴 형태는
//	'T openBox'이며 즉, 첫번째 주석처럼 된다.
//	첫번째 주석을 풀어서 이야기하면 두번째 주석형태로 선언한 것이나 같다는 것이다.
	
//	public static String openBox(BoxD<String> box) {
//		return box.get();
//	}
//	
//	public static String openBox(BoxD box) {
//		return box.get();
//	}
}

class C2_GenericMethodBoxMaker2 {
	public static void main(String[] args) {
		BoxD<String> box = new BoxD<>();
		box.set("My Generic Method");
		
		String str = UnboxerD.<String>openBox(box);
		System.out.println(str);
	}
}