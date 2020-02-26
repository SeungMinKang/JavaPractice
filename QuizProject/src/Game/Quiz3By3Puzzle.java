package Game;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Map {
	Scanner sc = new Scanner(System.in);
	Random rnd = new Random();
	String c;
	int x, y;
	String tmp;		// 이동 시 임시 저장용 변수

	// 숫자를 꺼내 쓸 리스트 생성
	List<String> numlist = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "X");
	
	// 엔딩용 배열
	String[][] arr2 = {{"1", "2", "3"},
					   {"4", "5", "6"},
					   {"7", "8", "X"}};
	
	// 숫자를 넣을 배열 생성
	String[][] arr1 = new String[3][3];
	
	// 지도 제작
	void makeMap() {
		// 리스트 내의 string을 무작위로 꺼내올 int 변수 생성
		int k = 0;
		numlist = new LinkedList<>(numlist);
		for(int i = 0; i < arr1.length; i++) {
			for(int j = 0; j < arr1[i].length; j++) {				
				int random = rnd.nextInt(9-k);
				arr1[i][j] = numlist.get(random);
				numlist.remove(random);
				k++;
			}
		}
	}
	
	// X의 위치
	void locationOfX() {
		for(int i = 0; i < arr1.length; i++) {
			for(int j = 0; j < arr1[i].length; j++) {
				if(arr1[i][j].equals("X")) {
					x = i;
					y = j;
				}
			}
		}
		
	}
	
	// 지도 보이기
	void showMap() {
		for(int i = 0; i < arr1.length; i++) {
			for(int j = 0; j < arr1.length; j++) {
				System.out.print(arr1[i][j]);
			}
			System.out.println();
		}
	}
	
	// 말 움직이기
	void move() {
		System.out.println("[ Move ] w: 위, a: 왼쪽 s: 아래 d: 오른쪽");
		System.out.println("[ Exit ] k: Exit");
		System.out.print("입력: ");
		c = sc.next();
		if(c.equals("w")) {
			if(x-1 == -1) {
				System.out.println("벽은 넘을 수 없습니다!");
				return;
			}
			x = x - 1;
			arr1[x+1][y] = arr1[x][y];
			arr1[x][y] = "X";
		} else if(c.equals("a")) {
			if(y-1 == -1) {
				System.out.println("벽은 넘을 수 없습니다!");
				return;
			}
			y = y - 1;
			arr1[x][y+1] = arr1[x][y];
			arr1[x][y] = "X";
		} else if(c.equals("s")) {
			if(x+1 == 3) {
				System.out.println("벽은 넘을 수 없습니다!");
				return;
			}
			x = x + 1;
			arr1[x-1][y] = arr1[x][y];
			arr1[x][y] = "X";
		} else if(c.equals("d")) {
			if(y+1 == 3) {
				System.out.println("벽은 넘을 수 없습니다!");
				return;
			}
			y = y + 1;
			arr1[x][y-1] = arr1[x][y];
			arr1[x][y] = "X";
		} else if(c.equals("k")) {
			System.out.println("게임 종료");
			return;
		}
		else {
			System.out.println("제대로 된 입력을 해주세요.");
			return;
		}
	}
}

class Quiz3By3Puzzle {
	public static void main(String[] args) {
		Map map = new Map();
		
		map.makeMap();
		while(true) {
			map.locationOfX();
			map.showMap();
			map.move();
			if(map.c.equals("k")) {
				break;
			}
			for(int i = 0; i < 20; i++) {
				System.out.println();
			}
			if(map.arr1[0][0].equals(map.arr2[0][0]) && 	// Arrays.equals를 이용하여 비교했으나
			   map.arr1[0][1].equals(map.arr2[0][1]) && 	// 두 배열이 다르다고 인식하는 문제가 발생
			   map.arr1[0][2].equals(map.arr2[0][2]) && 	// 문제 해결 전까지 현재 방법 임시 사용
			   map.arr1[1][0].equals(map.arr2[1][0]) && 
			   map.arr1[1][1].equals(map.arr2[1][1]) && 
			   map.arr1[1][2].equals(map.arr2[1][2]) && 
			   map.arr1[2][0].equals(map.arr2[2][0]) && 
			   map.arr1[2][1].equals(map.arr2[2][1]) && 
			   map.arr1[2][2].equals(map.arr2[2][2])) {
				map.showMap();
				System.out.println("정답입니다.");
				System.out.println("Good Bye~");
				break;
			}
		}
	}
}