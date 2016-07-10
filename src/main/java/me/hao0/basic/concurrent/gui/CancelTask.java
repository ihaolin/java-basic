package me.hao0.basic.concurrent.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 取消任务
 */
public class CancelTask {
	private static ExecutorService exec = Executors.newCachedThreadPool();
	private static Future<?> runningTask = null; //任务
	
	public static void main(String[] args) {
		final JButton startBtn = new JButton();
		final JButton stopBtn = new JButton();
		
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (runningTask != null){
					runningTask = exec.submit(new Runnable() {
						@Override
						public void run() {
							while (moreWork()){
								if (Thread.currentThread().isInterrupted()){//若中断了
									doClean();
									break;
								}
								doWork();
							}
						}
					});
				}
			}
		});
		
		stopBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (runningTask != null){ 
					runningTask.cancel(true); //取消任务
				}
			}
		});
	}
	
	private static void doWork() {
		
	}
	
	private static void doClean() {
		
	}

	private static boolean moreWork() {
		return false;
	}
}
