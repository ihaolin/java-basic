package me.hao0.basic.nio;

import org.omg.CORBA.IntHolder;

public class TestIntHolder {

	public static void main(String[] args) {
		IntHolder x = new IntHolder(3);
		triple(x);
		System.out.println(x.value);
	}

	public static void triple(IntHolder x){
		x.value = x.value + 3;
	}
}
