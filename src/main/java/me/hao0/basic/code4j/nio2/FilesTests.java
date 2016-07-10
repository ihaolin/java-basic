package me.hao0.basic.code4j.nio2;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.List;
import java.util.Set;

/**
 * 文件操作
 * @author haolin
 *
 */
public class FilesTests {
	
	@Test
	public void testCreateFile() throws IOException{
		Path newFilePath = Paths.get("./temp/test1.txt").normalize();
		Files.createFile(newFilePath);
	}
	
	/**
	 * set file access permission,
	 * but no functional because of parent directory's permission
	 * @throws IOException
	 */
	@Test
	public void testCreateFileWithPermission() throws IOException{
		Path newFilePath = Paths.get("./temp/test1.txt").normalize();
		Set<PosixFilePermission> permissions = 
				PosixFilePermissions.fromString("rw-rw-rw-");
		FileAttribute<Set<PosixFilePermission>> attr = 
					PosixFilePermissions.asFileAttribute(permissions);
		Files.createFile(newFilePath, attr);
	}
	
	@Test
	public void testDeleteFile() throws IOException{
		Path existedFilePath = Paths.get("./temp/test1.txt").normalize();
		Files.delete(existedFilePath);
	}
	
	@Test
	public void testCopyFile() throws IOException{
		// copy
		Path srcPath = Paths.get("./temp/test1.txt").normalize();
		Path destPath = Paths.get("./temp2/test2.txt").normalize();
		Files.copy(srcPath, destPath, 
				StandardCopyOption.REPLACE_EXISTING);
	}
	
	@Test 
	public void testMoveFile() throws IOException{
		Path srcPath = Paths.get("./temp/test1.txt").normalize();
		Path destPath = Paths.get("./temp2/test3.txt").normalize();
		
		Files.move(srcPath, destPath, 
					StandardCopyOption.ATOMIC_MOVE,
					StandardCopyOption.REPLACE_EXISTING
		);
	}
	
	@Test
	public void testReadQuick() throws IOException{
		Path path = Paths.get("./temp2/test2.txt").normalize();
		try(
			BufferedReader reader = 
					Files.newBufferedReader(path, StandardCharsets.UTF_8);
		){
			String line;
			while ((line = reader.readLine()) != null){
				System.out.println(line);
			}
		}
	}
	
	@Test
	public void testWriteQuick() throws IOException{
		Path path = Paths.get("./temp2/test3.txt").normalize();
		try(
			BufferedWriter writer = 
				Files.newBufferedWriter(path, StandardCharsets.UTF_8,
						StandardOpenOption.APPEND);
		){
			writer.write("This is a quick writer.");
		}
	}
	
	@Test
	public void testReadAll() throws IOException{
		Path path = Paths.get("./temp2/test2.txt").normalize();
		List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		
		for (String line : lines){
			System.out.println(line);
		}
		
		byte[] bytes = Files.readAllBytes(path);
		for (byte b : bytes){
			System.out.print(b + " ");
		}
		
	}
}
