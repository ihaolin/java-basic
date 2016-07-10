package me.hao0.basic.concurrent.threadpool;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/**
 * 并发的谜题解答器
 */
public class ConcurrentPuzzleSolver<P, M> {
	private final Puzzle<P, M> puzzle;
	private final ExecutorService exec;
	private final ConcurrentHashMap<P, Boolean> seen;
	final ValueLatch<Node<P, M>> solution
	                = new ValueLatch<Node<P, M>>(); //存放答案
	public ConcurrentPuzzleSolver(Puzzle<P, M> puzzle, ExecutorService exec,
			ConcurrentHashMap<P, Boolean> seen) {
		this.puzzle = puzzle;
		this.exec = exec;
		this.seen = seen;
	}

	public List<M> solve() throws InterruptedException{
		P p = puzzle.initialPosition();
		exec.execute(newTask(p, null, null));
		//阻塞直到找到答案
		Node<P, M> solvNode = solution.getValue();
		return solvNode == null ? null : solvNode.asMoveList();
	}

	protected Runnable newTask(P p, M m, Node<P, M> n) {
		return new SolverTask(p, m, n);
	}
	
	protected class SolverTask extends Node<P, M> implements Runnable {

		public SolverTask(P pos, M move, Node<P, M> prev) {
			super(pos, move, prev);
		}

		@Override
		public void run() {
			if (solution.isSet() //若已经找到了答案，阻止其他线程继续再找
					|| seen.putIfAbsent(pos, true) != null){
				return; 
			}
			if (puzzle.isGoal(pos)){  //找到了
				solution.setValue(this);
			} else{
				for (M m : puzzle.legalMoves(pos)){
					//继续找
					exec.execute(newTask(puzzle.move(pos, m), m, this));
				}
			}
		}
	}
}
