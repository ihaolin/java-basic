package me.hao0.basic.concurrent.avoidactiveness_danger;

public class TransferMoney {
	
	public static void transferMoney(Account fromAccount, 
							Account toAccount, int money){
		synchronized (fromAccount) {
			synchronized(toAccount){
				if (fromAccount.getBalance() > money){
					//余额不足
				} else{
					fromAccount.debit(money);
					toAccount.credit(money);
				}
			}
		}
	}
	
}
