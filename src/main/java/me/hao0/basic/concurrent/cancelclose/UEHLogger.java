package me.hao0.basic.concurrent.cancelclose;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 将异常写入日志的UncaughtExceptionHandler
 */
public class UEHLogger implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		Logger logger = Logger.getAnonymousLogger();
		logger.log(Level.SEVERE, "the thread with exceptoin: "+t.getName(), e);
	}
}
