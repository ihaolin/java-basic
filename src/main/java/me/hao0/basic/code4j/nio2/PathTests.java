package me.hao0.basic.code4j.nio2;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Path类的应用
 * @author haolin
 *
 */
public class PathTests {

	@Test
	public void testCreatePath(){
		String path = "/Users/haolinß"; 
		
		// equals : FileSystems.getDefault().getPath(path)
		Path listing = Paths.get(path);
		//System.out.println("project path=" + listing.toAbsolutePath());
		System.out.println("File Name : " + listing.getFileName());
		System.out.println("Name Count : " + listing.getNameCount());
		System.out.println("Parent path : " + listing.getParent());
		System.out.println("Root path : " + listing.getRoot());
		System.out.println("Subpath 2 from Root : " + listing.subpath(0, 2));
	}
	
	/**
	 * 移除冗余项
	 * @throws IOException
	 */
	@Test
	public void testRemoveReduntantItems() throws IOException{
		Path normalizePath = Paths.get("./bin/org/haol/coder4j/nio2/PathTests.class").normalize();
		System.out.println("normalizePath ="+normalizePath);
		String absolutePath = normalizePath.toAbsolutePath().toString();
		System.out.println("absolutePath ="+absolutePath);
		
		Path realPath = Paths.get(absolutePath).toRealPath();
		System.out.println("realPath ="+ realPath);
	}
	
	@Test
	public void testConvertPath(){
		Path prefix = Paths.get("/Users/");
		Path completePath = prefix.resolve("haolin/Learning");
		
		System.out.println("complete path =" + completePath);
		
		Path p1 = Paths.get("/Users");
		Path p2 = Paths.get("/Users/haolin/Learning");
		Path relativePath = p2.relativize(p1);
		System.out.println(relativePath);
	}
}
