package me.hao0.basic.concurrent.synccontaners;

public interface Computable<A, V> {
	V compute(A arg) throws InterruptedException;
}
