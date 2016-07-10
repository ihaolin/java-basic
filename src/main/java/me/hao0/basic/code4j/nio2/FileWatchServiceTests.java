package me.hao0.basic.code4j.nio2;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;

/**
 * 文件修改通知服务
 * @author haolin
 *
 */
public class FileWatchServiceTests {
	
	@Test
	public void testDirMonitor(){
		try {
			WatchService watcher = FileSystems.getDefault().newWatchService();
			Path dir = Paths.get("./temp").normalize();
			WatchKey key = dir.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);
			
			while (true){
				key = watcher.take();
				for (WatchEvent<?> event : key.pollEvents()){
					if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY){
						System.out.println("dir changed!");
					}
				}
				key.reset();
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
