package Game;

import java.util.Random;
import java.util.Scanner;

public class QuizHighLow {
	public static void main(String[] args) {		
		GuessIt guessit = new GuessIt();
		
		guessit.gameSet();
	}
}

class GuessIt {
	Random rnd = new Random();
	Scanner sc = new Scanner(System.in);
	
	int[] you = new int[6];
	int com = rnd.nextInt(101);
	int counter = 0;
	
	void gameSet() {
		while(true) {
			int com = rnd.nextInt(101);
			System.out.println("0과 100 사이의 값 중 하나를 생각하겠습니다.");
			System.out.println("그 숫자를 6회 안에 맞추십시오.");
			for(int i = 0; i < 6; i++) {
				counter = i;
				System.out.print("어떤 숫자일 것 같나요?: ");
				you[i] = sc.nextInt();
				if(you[i] == com) {
					System.out.println(com + "은(는) 정답입니다! 축하합니다!");
					break;
				}
				else if(you[i] < com) {
					System.out.println(you[i] + "은(는) 정답보다 작은 숫자입니다.");
				}else {
					System.out.println(you[i] + "은(는) 정답보다 큰 숫자입니다.");
				}
			}
			if(you[counter] != com && counter == 5) {
				System.out.println("정답은 " + com + "였습니다!");
				System.out.println("기회를 모두 소진하셨습니다.");
			}
			System.out.print("게임을 재시작 하시겠습니까? (y/n)");
			String str = sc.next();
			if(!str.equalsIgnoreCase("y")) {
				System.out.println("게임을 종료합니다.");
				break;
			}
			System.out.println();			
		}
	}
}