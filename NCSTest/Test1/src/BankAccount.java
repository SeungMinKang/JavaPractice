import java.util.Scanner;

public class BankAccount {
	
	// 인출 메서드
	public static void doWithdrawal(int leftBal, int cash) {
		int leftCash = leftBal;
		if(leftCash >= cash) {
			leftCash = leftCash - cash;
			System.out.println("인출 금액 : " + cash);
		} else {
			System.out.println("잔액이 부족합니다.");
		}
	}
	
	// 입금 메서드
	public static void doDeposit(int leftBal, int cash) {
		int leftCash = leftBal;
		System.out.println("입금 금액 : " + cash);
		leftCash = leftCash + cash;
	}
	
	// 계좌 잔액을 보여주는 메서드
	public static String toString(String depositor, String accNum, int leftBal){
		return "예금주: " + depositor + ", 계좌번호: " + accNum + ", 잔액: " + leftBal;		
	}
}