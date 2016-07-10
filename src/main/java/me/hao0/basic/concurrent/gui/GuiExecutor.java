package me.hao0.basic.concurrent.gui;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GuiExecutor {
	private static final Executor executor = Executors.newCachedThreadPool();
	
	public static Executor instance() {
		return executor;
	}
}
