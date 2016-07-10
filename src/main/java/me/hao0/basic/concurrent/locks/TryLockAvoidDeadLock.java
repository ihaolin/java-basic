package me.hao0.basic.concurrent.locks;

import me.hao0.concurrency.practice.avoidactiveness_danger.Account;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 使用tryLock来避免死锁
 * 上午12:05:38
 */
public class TryLockAvoidDeadLock {
	private static Random rand = new Random();
	
	public boolean transferMoney(Account fromAcct,
								Account toAcct,
								int amount,
								long timeout,
								TimeUnit unit){
		long fixedDelay = getFixedDelayComponentNanos(timeout, unit);
		long randMod = getRandomDelayModulusNanos(timeout, unit);
		long stopTime = System.nanoTime() + unit.toNanos(timeout);
		
		while (true){
			if (fromAcct.lock.tryLock()){ //若获取到源账户锁
				try{
					if (toAcct.lock.tryLock()){ //若获取到目的账户锁
						try{
							if (fromAcct.getBalance() < amount){
								throw new RuntimeException("money.not.enough");
							} else{
								fromAcct.debit(amount);
								toAcct.credit(amount);
								return true;
							}
						} finally{
							toAcct.lock.unlock();
						}
					}
				} finally{
					fromAcct.lock.unlock();
				}
			}
			if (System.nanoTime() < stopTime){
				return false;
			}
			try {
				Thread.sleep(fixedDelay + rand.nextLong()%randMod);
			} catch (InterruptedException e) {
				
			}
		}
	}

	private long getRandomDelayModulusNanos(long timeout, TimeUnit unit) {
		return 0;
	}

	private long getFixedDelayComponentNanos(long timeout, TimeUnit unit) {
		return 0;
	}
	
	public boolean transfeMessage(String message, long timeout, TimeUnit unit){
		long nanosToLock = unit.toNanos(timeout);
		//if ()
		return true;
	}
}
