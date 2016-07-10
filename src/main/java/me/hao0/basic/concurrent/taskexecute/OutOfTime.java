package me.hao0.basic.concurrent.taskexecute;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 错误的Timer行为
 */
public class OutOfTime {
	public static void main(String[] args) throws InterruptedException {
		Timer timer = new Timer();
		timer.schedule(new ThrowTask(), 1); //第一个任务抛出异常
		Thread.sleep(1000); 
		timer.schedule(new ThrowTask(), 1); //第二个任务将不能再执行, 并抛出异常Timer already cancelled.
		Thread.sleep(5000);
		System.out.println("end.");
	}
	
	static class ThrowTask extends TimerTask{

		@Override
		public void run() {
			throw new RuntimeException("test timer's error behaviour");
		}
	}
}
