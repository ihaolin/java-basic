package me.hao0.basic.concurrent.avoidactiveness_danger;

/**
 * 通过对输入参数的hash值进行比较
 * 来顺序访问锁
 */
public class SolveDeadLockByHash {
	/**
	 * 用于当输入参数的hash值一样时使用
	 */
	private static final Object tieLock = new Object();
	
	public static void transferMoney(final Account fromAccount, 
			final Account toAccount, final int money){
		class Helper{
			public void transfer(){
				if (fromAccount.getBalance() < money){
					//余额不足
				} else{
					fromAccount.debit(money);
					toAccount.credit(money);
				}
			}
		}
		
		int fromHash = System.identityHashCode(fromAccount);
		int toHash = System.identityHashCode(toAccount);
		
		//无论客户端怎么传入参数，我们都以先锁定hash值小的，再锁定hash大的
		//也可以利用业务中排序关系，如Account的编号等来比较
		if (fromHash < toHash){
			synchronized (fromAccount){
				synchronized (toAccount) {
					new Helper().transfer();
				}
			}
		} else if (fromHash >  toHash){
			synchronized (toAccount){
				synchronized (fromAccount) {
					new Helper().transfer();
				}
			}
		} else { //hash值相等, 情况很小
			synchronized (tieLock) {
				synchronized (fromAccount) {
					synchronized (toAccount) {
						new Helper().transfer();
					}
				}
			}
		}
	}
}
