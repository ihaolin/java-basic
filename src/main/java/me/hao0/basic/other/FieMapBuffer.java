package me.hao0.basic.other;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.CRC32;


public class FieMapBuffer {

	/**
	 * �ļ�ӳ���ڴ����
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("InputStream:");
		long start = System.currentTimeMillis();
		long crcValue = checksunInputStream("E:\\temp\\test.txt");
		long end = System.currentTimeMillis();
		System.out.println(Long.toHexString(crcValue));
		System.out.println((end - start) + "milliseconds");
		
		System.out.println("BufferedInputStream:");
		start = System.currentTimeMillis();
		crcValue = checksunBufferedInputStream("E:\\temp\\test.txt");
		end = System.currentTimeMillis();
		System.out.println(Long.toHexString(crcValue));
		System.out.println((end - start) + "milliseconds");
		
		System.out.println("RandomAccessFile:");
		start = System.currentTimeMillis();
		crcValue = checksunRandomAccessFile("E:\\temp\\test.txt");
		end = System.currentTimeMillis();
		System.out.println(Long.toHexString(crcValue));
		System.out.println((end - start) + "milliseconds");
		
		System.out.println("Mapped File:");
		start = System.currentTimeMillis();
		crcValue = checksunMappedFile("E:\\temp\\test.txt");
		end = System.currentTimeMillis();
		System.out.println(Long.toHexString(crcValue));
		System.out.println((end - start) + "milliseconds");
	}
	
	public static long checksunInputStream(String filename) throws IOException{
		InputStream in = new FileInputStream(filename);
		CRC32 crc = new CRC32();
		
		int c;
		while((c = in.read()) != -1){
			crc.update(c);
		}
		in.close();
		return crc.getValue();
	}
	
	public static long checksunBufferedInputStream(String filename) throws IOException{
		InputStream in = new BufferedInputStream(new FileInputStream(filename));
		CRC32 crc = new CRC32();
		
		int c;
		
		while((c = in.read()) != -1){
			crc.update(c);
		}
		in.close();
		return crc.getValue();
	}
	
	public static long checksunRandomAccessFile(String filename) throws IOException{
		RandomAccessFile file = new RandomAccessFile(filename, "r");
		long length = file.length();
		CRC32 crc = new CRC32();
		
		int c;
		for (long p = 0; p < length; p++){
			file.seek(p);
			c = file.readByte();
			crc.update(c);
		}
		file.close();
		return crc.getValue();
	}
	
	public static long checksunMappedFile(String filename) throws IOException{
		FileInputStream in = new FileInputStream(filename);
		FileChannel channel = in.getChannel();
		
		CRC32 crc = new CRC32();
		int length = (int)channel.size();
		MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);
		
		int c;
		for (int p = 0; p < length; p++){
			
			c = buffer.get(p);
			crc.update(c);
		}
		in.close();
		return crc.getValue();
	}
}
