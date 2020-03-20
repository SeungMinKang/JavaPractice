import java.util.Scanner;

public class BankAccountMain {
	
	public static void main(String[] args) {
		// 예금주, 계좌번호, 잔액 변수 생성
		String depositor = "홍길동";
		String accNum = "123-456";
		int leftBal = 10000;
		int cash;
		int sel;	// 선택용 메서드
		
		Scanner sc = new Scanner(System.in);

		System.out.print("예금주 성명: ");
		depositor = sc.nextLine();
		System.out.print("계좌 번호: ");
		accNum = sc.nextLine();
		System.out.print("잔액을 입력하세요: ");
		leftBal = sc.nextInt();
		
		System.out.println("어떤 업무를 수행하시겠습니까? ");
		System.out.println("1. 인출		2. 입금		3. 조회");
		sel = sc.nextInt();
		if(sel == 1) {
			System.out.print("인출 금액을 입력하세요: ");
			cash = sc.nextInt();
			BankAccount.doWithdrawal(leftBal, cash);
			if(leftBal > cash) {
				leftBal = leftBal - cash;
			}
		}
		if(sel == 2) {
			System.out.print("입금 금액을 입력하세요: ");
			cash = sc.nextInt();
			BankAccount.doDeposit(leftBal, cash);
			leftBal = leftBal + cash;
		}
		if(sel == 3) {
			System.out.println(BankAccount.toString(depositor, accNum, leftBal));
		}
	}

}
