package me.hao0.basic.nio;

import java.io.Console;
import java.util.Scanner;

public class Standard_io {
	public static void main(String[] args) {
		
		//���ӱ�׼������
		Scanner in = new Scanner(System.in);
		
		/*System.out.println("What's your name?");
		String name = in.nextLine();
		
		System.out.println("How old are you?");
		int age = in.nextInt();

		System.out.println("Hello, " + name + ",you are "+ age + " years");*/
		
		//��ȡ����
		/*System.out.println("please input sentence;");
		while(in.hasNext()){
			System.out.println(in.next());
		}*/
		
		//������������console
		Console cons = System.console();
		System.out.println(cons);
		String name = cons.readLine("user name:");
		char[] password = cons.readPassword("password: ");
	}
}
