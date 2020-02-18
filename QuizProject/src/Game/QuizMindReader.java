package Game;

import java.util.Scanner;

public class QuizMindReader {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		GuessMe guess = new GuessMe();
	
		System.out.println("0부터 100 사이의 값 중에 하나를 생각하세요.");
		System.out.println("제(컴)가 제시한 숫자가 생각한 숫자보다 크면 h를 입력하세요.");
		System.out.println("제(컴)가 제시한 숫자가 생각한 숫자보다 작으면 l를 입력하세요.");
		System.out.println("제(컴)가 숫자를 맞췄다면 y를 입력하세요.\n");
		
		guess.ask();
		
		while(true) {
			String p = sc.nextLine();		// 플레이어의 입력
			if((guess.num + guess.com) > 101 || (guess.num - guess.com) < -1) {
				System.out.println("이런!");
				System.out.println("저를 속이신 것 같군요.");
				break;
			}
			// 생각한 숫자가 더 클 때
			if(p.equals("l")) {
				guess.high();
			}
			// 생각한 숫자가 더 작을 때
			else if(p.equals("h")) {
				guess.low();
			}
			else if(p.equals("y")) {
				System.out.print("정답입니다.");
				System.out.println("[" + guess.count + "회차]");
				System.out.println("Good Bye~");
				break;
			}
		}
		sc.close();
	}
}

class GuessMe {
	Scanner sc = new Scanner(System.in);
	
	int count = 1;
	int num = 50;
	int com = 25;
	
	// 물어보기
	void ask() {
		System.out.println("당신이 선택한 숫자는 " + num + "입니까?");
	}
	
	// 생각한 숫자가 더 클 때
	void high() {
		if((num + com) > 100 || (num - com) < -1) {
			System.out.println("이런!");
			System.out.println("저를 속이신 것 같군요.");
			return;
		}
		System.out.println("당신이 선택한 숫자는 " + (num + com) + "입니까?");
		num = num + com;
		com = (com/2);
		if(com < 1) {
			com = 1;
		}		
		count++;
	}
	
	// 생각한 숫자가 더 작을 때
	void low() {
		if((num + com) > 100 || (num - com) < 0) {
			System.out.println("이런!");
			System.out.println("저를 속이신 것 같군요.");
			return;
		}
		System.out.println("당신이 선택한 숫자는 " + (num - com) + "입니까?");
		num = num - com;
		com = (com/2);
		if(com < 1) {
			com = 1;
		}
		count++;
	}
}