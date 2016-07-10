package me.hao0.basic.other;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;


public class TestCharset {

	public static void main(String[] args) {
		/*Map<String, Charset> charsets = Charset.availableCharsets();
		
		for (String name : charsets.keySet()){
			System.out.println(name);
		}*/
		
		Charset cs = Charset.forName("ISO-8859-1");
		System.out.println(cs.name());
		
		//String -> Byte[]
		String str = "I Love You";
		ByteBuffer buffer = cs.encode(str);
		byte[] bytes = buffer.array();
		System.out.println(bytes);
		
		//Byte[] -> String
		ByteBuffer bbuf = ByteBuffer.wrap(bytes);
		CharBuffer cbuf = cs.decode(bbuf);
		System.out.println(cbuf.toString());
	}

}
