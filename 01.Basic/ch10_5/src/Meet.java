// 인터페이스는 상속 관계가 아닌 클래스에
// 기능을 제공하는 구조.
// 클래스와 비슷한 구조이지만,
// 정의와 추상 메서드만이 멤버가 될 수 있다.

interface Greet{
	void greet();
}

interface Talk{
	void talk();
}

class Morning implements Greet, Talk
{
	public void greet()
	{
		System.out.println("안녕하세요!");
	}
	
	public void talk()
	{
		System.out.println("날씨 좋네요.");
	}
}

class Meet
{
	public static void main(String[] args)
	{
		Morning morning = new Morning();
		morning.greet();
		morning.talk();
	}
}
