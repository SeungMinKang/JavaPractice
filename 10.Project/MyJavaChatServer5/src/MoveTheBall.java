import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Scanner;

class Map {
	Scanner sc = new Scanner(System.in);

	String[][] arr1 = new String[10][10];
	String[][] arr2 = new String[10][10];
	int x = 1, y = 1;

	// 지도 제작
	void makeMap() {
		for (int i = 0; i < arr1.length; i++) {
			for (int j = 0; j < arr1[i].length; j++) {
				if (i == 0 || i == 9 || j == 0 || j == 9) {
					arr1[i][j] = "+";
				} else {
					arr1[i][j] = "X";
				}
				arr1[x][y] = "O";
				arr1[8][8] = "G";
				if (x == 8 && y == 8) {
					arr1[8][8] = "O";
				}
			}
		}
	}

	// 지도 보이기
	void showMap(PrintWriter out) {
		for (int i = 0; i < arr1.length; i++) {
			for (int j = 0; j < arr1.length; j++) {
				out.print(arr1[i][j]);
			}
			out.println();
		}
	}

	// 말 움직이기
	void move(PrintWriter out, BufferedReader in) {
		out.println("방향을 정하세요.");
		out.println("w: 위, a: 왼쪽 s: 아래 d: 오른쪽");
		out.print("입력: ");

		try {
			// 입력스트림이 null이 아니면 반복.
			String c = "";
			c = in.readLine();
			System.out.println(c);
			if (c.equals("w")) {
				if (x - 1 == 0) {
					out.println("벽은 넘을 수 없습니다!");
					return;
				}
				x = x - 1;
				arr1[x][y] = "O";
			} else if (c.equals("a")) {
				if (y - 1 == 0) {
					out.println("벽은 넘을 수 없습니다!");
					return;
				}
				y = y - 1;
				arr1[x][y] = "O";
			} else if (c.equals("s")) {
				if (x + 1 == 9) {
					out.println("벽은 넘을 수 없습니다!");
					return;
				}
				x = x + 1;
				arr1[x][y] = "O";
			} else if (c.equals("d")) {
				if (y + 1 == 9) {
					System.out.println("벽은 넘을 수 없습니다!");
					return;
				}
				y = y + 1;
				arr1[x][y] = "O";
			} else {
				System.out.println("제대로 된 입력을 해주세요.");
				return;
			}

		} catch (Exception e) {
//			System.out.println("예외:"+e);
		}
	}
}

class MoveTheBall {
	public void start(PrintWriter out, BufferedReader in) {
		Map map = new Map();

		try {
			while (true) {
				map.makeMap();
				map.showMap(out);
				map.move(out, in);
				for (int i = 0; i < 20; i++) {
					out.println();
				}
				if (map.arr1[8][8].equals("O")) {
					map.makeMap();
					map.showMap(out);
					out.println("목표에 도달하였습니다!");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}