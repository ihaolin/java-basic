package me.hao0.basic.compile;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URI;

public class ByteArrayJavaClass extends SimpleJavaFileObject {
	private ByteArrayOutputStream stream;
	
	public ByteArrayJavaClass(String name){
		super(URI.create("bytes:///" + name), Kind.CLASS);
		stream = new ByteArrayOutputStream();
	}
	
	public OutputStream openOutputStream(){
		return stream;
	}
	
	public byte[] getBytes(){
		return stream.toByteArray();
	}
}
