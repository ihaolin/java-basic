package me.hao0.basic.code4j.nio2;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 处理目录及目录树
 * @author haolin
 *
 */
public class DirectoryTests {
	
	@Test
	public void testFindFile(){
		Path dir = Paths.get("./bin/org/haol/coder4j/nio2");
		try(
			DirectoryStream<Path> stream = 	
						Files.newDirectoryStream(dir, "*.class");
		){
			for (Path entry : stream){
				System.out.println(entry.getFileName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testIterateDirTree() throws IOException{
		Path dir = Paths.get("./").normalize();
		Files.walkFileTree(dir, new FindJavaVisitor());
	}
	
	private class FindJavaVisitor extends SimpleFileVisitor<Path>{

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
				throws IOException {
			if (file.toString().endsWith(".java")){
				System.out.println(file.getFileName());
				//System.out.println(attrs);
			}
			return super.visitFile(file, attrs);
		}
	}
}
