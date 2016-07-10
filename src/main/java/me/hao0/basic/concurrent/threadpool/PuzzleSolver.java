package me.hao0.basic.concurrent.threadpool;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 在解决器中找不到解答时
 */
public class PuzzleSolver<P, M> extends ConcurrentPuzzleSolver<P, M> {
	private final AtomicInteger taskCount = new AtomicInteger(); //通过计数来标志是否找到解答
	
	public PuzzleSolver(Puzzle<P, M> puzzle, ExecutorService exec,
			ConcurrentHashMap<P, Boolean> seen) {
		super(puzzle, exec, seen);
	}

	@Override
	protected Runnable newTask(P p, M m, Node<P, M> n) {
		return new CountingSolverTask(p, m, n);
	}

	class CountingSolverTask extends SolverTask{
		
		public CountingSolverTask(P pos, M move, Node<P, M> prev) {
			super(pos, move, prev);
			taskCount.incrementAndGet();
		}

		@Override
		public void run() {
			try{
				super.run();
			} finally{
				if (taskCount.decrementAndGet() == 0){
					solution.setValue(null);
				}
			}
		}
	}
}
