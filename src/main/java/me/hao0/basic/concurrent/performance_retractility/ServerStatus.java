package me.hao0.basic.concurrent.performance_retractility;

import java.util.Set;

/**
 * 多个状态由一个锁来保护
 */
public class ServerStatus {
	public final Set<String> users;
	private final Set<String> queries;
	
	public ServerStatus(Set<String> users, Set<String> queries) {
		super();
		this.users = users;
		this.queries = queries;
	}

	public synchronized void addUser(String u){
		users.add(u);
	}
	
	public synchronized void addQuery(String q){
		queries.add(q);
	}
}
