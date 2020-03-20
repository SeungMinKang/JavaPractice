import java.util.Scanner;

public class BankAccountHong {
	
	public static void main(String[] args) {
		// 예금주, 계좌번호, 잔액 변수 생성
		String depositor = "홍길동";
		String accNum = "123-456";
		int leftBal = 10000;
		int cash;
		int sel;	// 선택용 메서드
		
		Scanner sc = new Scanner(System.in);
		
		BankAccount.doDeposit(leftBal, 15000);
		BankAccount.doWithdrawal(leftBal, 30000);
	}
}