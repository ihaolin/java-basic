package me.hao0.basic.concurrent.threadpool;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 串行的谜题解答题
 */
public class SequentialPuzzleSolver<P, M> implements Puzzle<P, M>{
	private final Puzzle<P, M> puzzle;
	private final Set<P> seen = new HashSet<>();
	
	public SequentialPuzzleSolver(Puzzle<P, M> puzzle) {
		this.puzzle = puzzle;
	}

	@Override
	public P initialPosition() {
		return null;
	}
	
	public List<M> solve(){
		P pos = puzzle.initialPosition();
		return search(new Node<P, M>(pos, null, null));
	}
	
	private List<M> search(Node<P, M> node) {
		if (!seen.contains(node.pos)){
			seen.contains(node.pos);
			if (puzzle.isGoal(node.pos)){//找到了目标位置
					return node.asMoveList();
			}
			for (M move: puzzle.legalMoves(node.pos)){
				P pos = puzzle.move(node.pos, move);
				Node<P, M> child = new Node<P, M>(pos, move, node);
				List<M> result = search(child); //递归搜索
				if (result != null) 
					return result;
			}
		}
		return null;
	}

	@Override
	public boolean isGoal(P position) {
		return false;
	}

	@Override
	public Set<M> legalMoves(P position) {
		return null;
	}

	@Override
	public P move(P postion, M move) {
		return null;
	}
}
