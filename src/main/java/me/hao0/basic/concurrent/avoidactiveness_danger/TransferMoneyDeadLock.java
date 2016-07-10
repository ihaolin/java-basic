package me.hao0.basic.concurrent.avoidactiveness_danger;

public class TransferMoneyDeadLock {
	
	public static void main(String[] args) {
		Account a1 = new Account();
		Account a2 = new Account();
		int money = 0;
		TransferMoney.transferMoney(a1, a2, money);
		TransferMoney.transferMoney(a2, a1, money);
	}
}
