package me.hao0.basic.concurrent.gui;

import java.util.concurrent.*;

/**
 * 支持取消，完成通知及进度通知的任务
 */
public abstract class BackgroundTask<T> implements Runnable, Future<T> {
	private final FutureTask<T> computation = new Computation();
	
	/**
	 * 计算任务
	 */
	private class Computation extends FutureTask<T>{
		public Computation() {
			super(new Callable<T>() {
				@Override
				public T call() throws Exception {
					return BackgroundTask.this.compute();
				}
			});
		}
		
		@Override
		protected final void done(){
			GuiExecutor.instance().execute(new Runnable() {
				@Override
				public void run() {
					T value = null;
					Throwable thrown = null;
					boolean cancelled = false;
					try {
						value = get();
					} catch (InterruptedException e) {
					} catch (ExecutionException e) {
						thrown = e.getCause();
					} catch (CancellationException e){
						cancelled = true;
					} finally{
						onCompletion(value, thrown, cancelled);
					}
				}
			});
		}
	}
	
	protected void setProgress(final int current, final int max){
		GuiExecutor.instance().execute(new Runnable() {
			@Override
			public void run() {
				onProgress(current, max);
			}
		});
	}
	
	private void onProgress(int current, int max) {}
	
	private void onCompletion(T value, Throwable thrown,
			boolean cancelled) {}
	
	/**
	 * 计算过程
	 */
	protected abstract T compute();

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return computation.cancel(mayInterruptIfRunning);
	}

	@Override
	public void run() {
		computation.run();
	}
	
	@Override
	public boolean isCancelled() {
		return computation.isCancelled();
	}

	@Override
	public boolean isDone() {
		return computation.isDone();
	}

	@Override
	public T get() throws InterruptedException, ExecutionException {
		return computation.get();
	}

	@Override
	public T get(long timeout, TimeUnit unit) throws InterruptedException,
			ExecutionException, TimeoutException {
		return computation.get(timeout, unit);
	}
}
