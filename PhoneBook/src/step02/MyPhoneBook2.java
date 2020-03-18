package step02;

import java.util.Scanner;

public class MyPhoneBook2 {

	static Scanner sc = new Scanner(System.in);
	
	public void showMenu() {
		System.out.println("[메뉴 선택]");
		System.out.println("1. 전화번호 입력");
		System.out.println("2. 전화번호 조회");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 종료");
		System.out.print("선택 : ");
	}
	
	public void addNumber() {
		
	}
	
	public void selNumber() {
		
	}
	
	public void delNumber() {
		
	}
	
	public static void main(String[] args) {		
		MyPhoneBook2 mp2 = new MyPhoneBook2();		// program이 시작되면 실행되는 부분.	MyPhoneBook2가 heap 영역에 생성된다.
		mp2.doRun();											
	}
	
	public void doRun() {
		int choice;
		while(true) {
			showMenu();
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				addNumber();
				break;
			case 2:
				selNumber();
				break;
			case 3:
				delNumber();
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
