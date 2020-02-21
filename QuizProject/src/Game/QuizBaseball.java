package Game;

import java.util.Random;
import java.util.Scanner;

class Com {
	int player;
	int[] com = new int[3];
	int[] ply = new int[3];
	int checker = 0;

	// 컴퓨터가 수를 무작위로 생성하는 메서드
	void comNum() {

		Random rnd1 = new Random();

		while (true) {
			com[0] = rnd1.nextInt(9) + 1;
			for (int i = 1; i < 3; i++) {
				com[i] = rnd1.nextInt(9) + 1;
				while (com[i] == com[0] || com[i] == com[i - 1]) {
					com[i] = rnd1.nextInt(9) + 1;
				}
			}
			break;
		}
	}

	// 플레이어가 숫자를 입력하는 메서드
	void myNum() {
		Scanner sc = new Scanner(System.in);
		boolean rightnum = false;

		// 숫자를 중복으로 입력하는 경우는 플레이어의 잘못이기 때문에 처리하지 않음
		while (rightnum != true) {
			System.out.println("세자리 숫자를 입력하세요. ");
			player = sc.nextInt();		// 숫자 이외의 문자를 입력하면 오류 발생
			if (player > 100 && player < 1000) {
				rightnum = true;
				break;
			}
			while (player < 100 || player > 999) {
				System.out.println("세자리 숫자를 입력해야합니다!");
				player = sc.nextInt();
				if (player > 100 && player < 1000) {
					rightnum = true;
					break;
				}
			}
		}
		makeArray();
	}
	
	// 입력 받은 수를 배열에 집어넣는 메서드
	void makeArray() {
		for (int i = 2; i >= 0; i--) {
			ply[i] = (player % 10);
			player = (player / 10);
		}
	}

	// 플레이어가 입력한 숫자를 보여주는 메서드
	void showNum() {
		for (int i = 0; i < 3; i++) {
			System.out.print(ply[i]);
		}
		System.out.println();
	}

	// 숫자를 판별하는 메서드
	void judgeNum() {
		int stk = 0;
		int bal = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (com[i] == ply[j]) {
					if (i == j) {
						stk++;
						checker = stk;
					} else {
						bal++;
					}
				}
			}
		}
		System.out.print(stk + " Strike\t" + bal + " Ball\n");
		if (stk == 3) {
			System.out.println("YOU WIN!!!");
		}
	}
}

public class QuizBaseball {

	public static void main(String[] args) {
		Com com = new Com();

		com.comNum();

		while (true) {
			com.myNum();
			com.showNum();
			com.judgeNum();
			if (com.checker == 3)
				break;
		}
	}
}