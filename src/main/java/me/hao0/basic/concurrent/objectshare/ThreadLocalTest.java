package me.hao0.basic.concurrent.objectshare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ThreadLocalTest {
	
	private static ThreadLocal<Connection> connectionHolder = 
			new ThreadLocal<Connection>(){
				@Override
				protected Connection initialValue() {
					try {
						return DriverManager.getConnection("DB_URL");
					} catch (SQLException e) {
						e.printStackTrace();
					} 
					return null;
				}
			};
	
	public static Connection getConnection(){
		//不同线程每次得到的Connection, 都是独立的备份
		return connectionHolder.get();
	}
	
	public static void main(String[] args) {
		
	}
}
