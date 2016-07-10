package me.hao0.basic.code4j.concurrent;

/**
 * 构建器模式来实现不可变类
 * @author haolin
 *
 */
public class Update {
	private final String index;
	private final String value;
	
	private Update(Builder b){
		this.index = b.index;
		this.value = b.value;
	}
	
	public static class Builder implements ObjBuilder<Update>{
		private String index;
		private String value;
		
		@Override
		public Update build() {
			return new Update(this);
		}
		
		public Builder index(String index){
			this.index = index;
			return this;
		}
		
		public Builder value(String value){
			this.value = value;
			return this;
		}
	}
	
	public static void main(String[] args) {
		Builder b = new Builder();
		Update u = b.index("index").value("value").build();
	}
}
