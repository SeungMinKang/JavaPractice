package Game;

import java.util.Random;
import java.util.Scanner;

class Com {
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
		
		System.out.println("세자리 숫자를 입력하세요. ");	// 수를 하나씩 입력해야 정상적으로 작동하는 문제가 있음	// 추후 수정 예정
		ply[0] = sc.nextInt();
		for (int i = 1; i < 3; i++) {
			ply[i] = sc.nextInt();
			while (ply[i] == ply[0] || ply[i] == ply[i - 1]) {
				System.out.println("세 숫자는 서로 달라야합니다!");
				System.out.print((i + 1) + "번째 숫자: ");
				ply[i] = sc.nextInt();
			}
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
		if(stk == 3) {
			System.out.println("YOU WIN!!!");
		}
	}
}

public class QuizBaseball {

	public static void main(String[] args) {
		Com com = new Com();

		com.comNum();
		
		while(true) {
			com.myNum();
			com.showNum();
			com.judgeNum();
			if(com.checker == 3)
				break;
		}
	}
}