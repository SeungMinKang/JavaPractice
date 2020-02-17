abstract class Human {
	// 사람답게 만들것임
	int height;
	int age;
	abstract void attackSword();
}

abstract class Elf {
	// 엘프답게 만들것임
//	private int age;			// 은닉화
	protected int age;			// 다른 클래스한테는 프라이빗, 자식 클래스한테는 퍼블릭
	int height;
	void move() {
		System.out.println("걷기");
	}
	abstract void attackBow();
	
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}
}

class HighElf extends Elf {
	void move() {
		System.out.println("소리 안나게 걷기");
	}
	void attackBow() {
		System.out.println("100미터 활쏘기");
	}
}

class NormalElf extends Elf {
	void printAge() {
		int age = getAge();
		System.out.println(age);
	}
	void attackBow() {
		System.out.println("80미터 활쏘기");
	}
}

interface Dig {
	void myDig();
}

class VeryDig implements Dig {
	public void myDig() {
		System.out.println("땅파기");
	}
}

class DarkElf extends Elf {
	void move() {
		super.move();
		System.out.println("살금 살금");
	}
	void attackBow() {
		System.out.println("90미터 활쏘기");
	}
}
public class Exam03 {

	public static void main(String[] args) {
		HighElf elf1 = new HighElf();
		attack(elf1);
		
		NormalElf elf2 = new NormalElf();
		attack(elf2);
		
		DarkElf elf3 = new DarkElf();
		attack(elf3);
	}
	
	public static void attack(Elf elf) {
		// 어택이 되는 상황인지 체크
		// ...
		elf.attackBow();
	}
}
