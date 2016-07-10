package me.hao0.basic.concurrent.synccontaners;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier协调细胞自动衍生系统中的计算
 */
public class CellularAutomata {
	private final Board mainBoard;
	private final CyclicBarrier barrier;
	private final Worker[] workers;
	
	public CellularAutomata(Board board){
		this.mainBoard = board;
		int cpus = Runtime.getRuntime().availableProcessors();
		this.barrier = new CyclicBarrier(cpus, new Runnable() {
			@Override
			public void run() {
				mainBoard.commitNewValues();
			}
		});
		this.workers = new Worker[cpus];
		for (int i=0; i<workers.length; i++){
			workers[i] = new Worker(mainBoard);
		}
	}
	
	public void start(){
		for (int i=0; i<workers.length; i++){
			new Thread(workers[i]).start();
		}
		mainBoard.waitForConvergence();
	}
	
	private class Worker implements Runnable{
		private final Board board;
		
		public Worker(Board board){
			this.board = board;
		}
		
		@Override
		public void run() {
			while (!board.hasConverged()){
				// do compute
				for (int x=0; x<board.getMaxX(); x++){
					for (int y=0; y<board.getMaxY(); y++){
						board.setNewValue(x, y, computeValue(x, y));
					}
				}
				try {
					barrier.await(); //阻塞直到当最后一个线程到达
				} catch (InterruptedException e) {
					return;
				} catch (BrokenBarrierException e) {
					return;
				}
			}
		}
	}
	
	private int computeValue(int x, int y) {
		return 0;
	}
}
