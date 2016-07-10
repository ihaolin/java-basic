package me.hao0.basic.concurrent.synccontaners;

import java.util.concurrent.Exchanger;

/**
 * 通过Exchanger交换2个线程
 */
public class ExchangerTest {
	public static void main(String[] args) {
		Exchanger<String> exchanger = new Exchanger<>();

		ExchangerRunnable exchangerRunnable1 =
		        new ExchangerRunnable(exchanger, "A");

		ExchangerRunnable exchangerRunnable2 =
		        new ExchangerRunnable(exchanger, "B");

		new Thread(exchangerRunnable1).start();
		new Thread(exchangerRunnable2).start();
	}
	
	private static class ExchangerRunnable implements Runnable{
		private Exchanger<String> exchanger;
		private String data;
		public ExchangerRunnable(Exchanger<String> exchanger, String data){
			this.exchanger = exchanger;
			this.data = data;
		}

		@Override
		public void run() {
			try {
	            String beforeData = this.data;
	            //阻塞直到另一个线程调用exchanger.exchange(), 交换数据
	            this.data = this.exchanger.exchange(this.data); 
	            System.out.println(
	                    Thread.currentThread().getName() +
	                    " exchanged " + beforeData + " for " + this.data
	            );
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		}
	}
}
