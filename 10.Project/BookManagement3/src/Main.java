import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	
	Connection con;
	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		Main mn = new Main();
		mn.doRun();
	}
	
	public static void showMenu() {
		System.out.println("[메뉴 선택]");
		System.out.println("1. 도서 관리 업무");
		System.out.println("2. 회원 관리 업무");
		System.out.println("3. 대여 관리 업무");
		System.out.println("4. 종료");
		System.out.print("선택 : ");
	}
	
	public void doRun() {
		int choice;
		while(true) {
			showMenu();
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				BookList.menu();
				break;
			case 2:
				MemberList.menu();
				break;
			case 3:
				RentalList.menu();
				break;
			case 4:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		}
	}
}
