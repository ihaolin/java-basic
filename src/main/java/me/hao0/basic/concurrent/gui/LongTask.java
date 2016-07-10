package me.hao0.basic.concurrent.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * GUI中的长时间任务
 */
public class LongTask {
	private static ExecutorService exec = Executors.newCachedThreadPool();
	
	public static void main(String[] args) {
		final JButton button = new JButton();
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exec.execute(new Runnable() { //执行任务
					@Override
					public void run() {
						doMuchComptation();
					}

				});
			}
		});
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.setText("Loading...");
				button.setEnabled(false);
				exec.execute(new Runnable() { //执行任务
					@Override
					public void run() {
						try {
							doMuchComptation();
						} finally{
							exec.execute(new Runnable() {
								@Override
								public void run() { //恢复button表现
									button.setText("Load");
									button.setEnabled(true);
								}
							});
						}
					}

				});
			}
		});
	}
 
	private static void doMuchComptation() {
		
	}
}
