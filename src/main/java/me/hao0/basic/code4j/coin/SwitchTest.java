package me.hao0.basic.code4j.coin;

/**
 * switch支持string
 * @author haolin
 *
 */
public class SwitchTest {
	public static void main(String[] args) {
		String type = "";
		switch (type){
			case "a": 
				System.out.println("a");break;
			case "b":
				System.out.println("b");break;
			default:
				System.out.println("not a and b");break;
		}
	}
}
