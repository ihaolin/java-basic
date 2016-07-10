package me.hao0.basic.concurrent.tests;

import me.hao0.concurrency.practice.avoidactiveness_danger.Account;

import java.util.Random;

public class MoreContextSwitch {
	private static int THRESHOLD = 500;
	private static Random random = new Random();
	
	public synchronized void transferCredits(Account from, Account to, int amount){
		from.setBalance(from.getBalance() - amount);
		if (random.nextInt(1000) > THRESHOLD){
			Thread.yield();
		}
		to.setBalance(to.getBalance() + amount);
	}
}
