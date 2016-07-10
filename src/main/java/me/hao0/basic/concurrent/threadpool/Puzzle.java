package me.hao0.basic.concurrent.threadpool;

import java.util.Set;

/**
 * 谜题框架的抽象类
 */
public interface Puzzle<P, M> {
	P initialPosition();
	boolean isGoal(P position);
	Set<M> legalMoves(P position);
	P move(P postion, M move);
}
