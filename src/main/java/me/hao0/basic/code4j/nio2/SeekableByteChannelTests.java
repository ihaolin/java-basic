package me.hao0.basic.code4j.nio2;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 随机访问文件
 * @author haolin
 *
 */
public class SeekableByteChannelTests {
	
	@Test
	public void testAccessRandom() throws IOException{
		Path path = Paths.get("./temp2/test2.txt");
		ByteBuffer buffer = ByteBuffer.allocate(20);
		FileChannel channel = FileChannel.open(path, StandardOpenOption.READ);
		channel.read(buffer, 2);
		System.out.println(new String(buffer.array(), "UTF-8"));
	}
}
