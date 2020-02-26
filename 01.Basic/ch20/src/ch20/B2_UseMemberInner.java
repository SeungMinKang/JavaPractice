package ch20;

interface Printable1 {
	void print();
}

class Papers1 {
	private String con;
	
	public Papers1(String s) {
		con = s;
	}
	
	public Printable1 getPrinter() {
		return new Printer1();
	}
	
	private class Printer1 implements Printable1 {
		public void print() {
			System.out.println(con);
		}
	}
}

class B2_UseMemberInner {
	public static void main(String[] args) {
		Papers1 p = new Papers1("서류 내용: 행복합니다.");
		Printable1 prn = p.getPrinter();
		prn.print();
	}
}

// 멤버 클래스는 클래스의 정의를 감추어야 할 때 유용하게 사용이 된다.
// 클래스 사용자 입장에서 Printable 인터페이슨느 알지만 Printer 클래스는 모른다!
// 알 필요도 없다!