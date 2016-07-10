package me.hao0.basic.concurrent.performance_retractility;

import java.util.Set;

/**
 * 多个状态由多个锁来保护
 */
public class BetterServerStatus {
	public final Set<String> users;
	private final Set<String> queries;
	
	public BetterServerStatus(Set<String> users, Set<String> queries) {
		super();
		this.users = users;
		this.queries = queries;
	}

	public void addUser(String u){
		synchronized(users){
			users.add(u);
		}
	}
	
	public void addQuery(String q){
		synchronized(queries){
			queries.add(q);
		}
	}
}
