package Game;

import java.util.Random;
import java.util.Scanner;

abstract class Gamer {
	int n;
	void doRPS()
	{
		
	}
}

class Player extends Gamer 
{
	Scanner sc = new Scanner(System.in);
	void doRPS() {
		System.out.println("번호를 입력하세요.");
		System.out.println("(가위: 1), (바위: 2), (보: 3), (종료: 0)");
		System.out.print("입력: ");
		n = sc.nextInt();
		while(n < 0 || n > 3) {
			System.out.println("잘못된 입력을 하셨습니다.");
			System.out.print("0 ~ 3 사이의 숫자를 입력하세요: ");
			n = sc.nextInt();
		}
		if(n == 1) {
			System.out.println("플레이어: 가위");
		}
		if(n == 2) {
			System.out.println("플레이어: 바위");
		}
		if(n == 3) {
			System.out.println("플레이어: 보");
		}
		else if(n == 0) {
			System.out.println("프로그램 종료.");
			return;
		}
	}
}

class Bot extends Gamer
{
	void doRPS() {
		Random rnd = new Random();
		n = rnd.nextInt(3)+1;
		if(n == 1) {
			System.out.println("컴퓨터: 가위");
		}
		if(n == 2) {
			System.out.println("컴퓨터: 바위");
		}
		if(n == 3) {
			System.out.println("컴퓨터: 보");
		}
		return;
	}
}

public class QuizRPSGame {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Gamer player = new Player();
		Gamer bot = new Bot();
		
		// 번호를 입력 받기
		// (가위: 1), (바위: 2), (보: 3), (종료: 0)

		while(true) {
			player.doRPS();
			if(player.n == 0) {
				break;
			}
			bot.doRPS();
			if(bot.n == player.n) {
				System.out.println("비겼습니다.");
			}
			if(player.n == 1) {
				if(bot.n == 2) {
					System.out.println("졌습니다.");
				}else if(bot.n == 3) {
					System.out.println("이겼습니다.");
				}
			}
			if(player.n == 2) {
				if(bot.n == 3) {
					System.out.println("졌습니다.");
				}else if(bot.n == 1) {
					System.out.println("이겼습니다.");
				}
			}
			if(player.n == 3) {
				if(bot.n == 1) {
					System.out.println("졌습니다.");
				}else if(bot.n == 2) {
					System.out.println("이겼습니다.");
				}
			}
		}
		sc.close();
	}
}