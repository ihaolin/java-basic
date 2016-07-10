package me.hao0.basic.code4j.coin;

/**
 * 数字常量可用下划线连接
 * @author haolin
 *
 */
public class NumberConstantTest {
	public static void main(String[] args) {
		int x = 123_456_789; // java7 new feature, 方便查看
		System.out.println("x=" + x);
	}
}
