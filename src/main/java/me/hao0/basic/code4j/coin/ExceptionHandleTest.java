package me.hao0.basic.code4j.coin;

import java.io.*;

/**
 * 1.支持catch同时包括多个exception
 * 2.try-with-resources(TWR)
 * @author haolin
 *
 */
public class ExceptionHandleTest {
	public static void main(String[] args) throws NullPointerException {
		try {
			File f = new File("xxx");
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
			bis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/**
		 * 将申请资源放入try()中，可自动释放资源
		 */
		try(
			FileInputStream fis = new FileInputStream("xxx.txt");
			ObjectInputStream ois = new ObjectInputStream(fis)){
			// DO SOMETHING
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
