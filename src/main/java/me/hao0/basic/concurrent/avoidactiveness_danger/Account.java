package me.hao0.basic.concurrent.avoidactiveness_danger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

	public Lock lock = new ReentrantLock();

	public int getBalance() {
		return 0;
	}

	public void debit(int money) {
		
	}

	public void credit(int money) {
		
	}

	public void setBalance(int i) {
		// TODO Auto-generated method stub
		
	}

}
