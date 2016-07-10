package me.hao0.basic.code4j.performance;

/**
 * -verbose:gc -Xms12M -Xmx12M -Xmn8M -XX:SurvivorRatio=6
 */
public class GCDemoTest {
	private static int _1MB = 1024 * 1024;
	
	public static void main(String[] args) throws InterruptedException {
		byte[] alloc1, alloc2, alloc3, alloc4;
		
		// Eden allocated 6M
		alloc1 = new byte[2 * _1MB];
		alloc2 = new byte[2 * _1MB];
		alloc3 = new byte[2 * _1MB];
		
		// Eden not enough, occur first Minor GC,
		alloc4 = new byte[2 * _1MB];
		
		while (true);
	}
}
