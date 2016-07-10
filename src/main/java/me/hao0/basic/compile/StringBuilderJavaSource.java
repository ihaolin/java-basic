package me.hao0.basic.compile;

import javax.tools.SimpleJavaFileObject;
import java.io.IOException;
import java.net.URI;

public class StringBuilderJavaSource extends SimpleJavaFileObject {
	private StringBuilder code;
	
	public StringBuilderJavaSource(String name) {
		super(URI.create("string:///"+name.replace(".", "/")+Kind.SOURCE.extension), Kind.SOURCE);
		code = new StringBuilder();
	}

	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors)
			throws IOException {
		return code;
	}
	
	public void append(String str){
		code.append(str);
		code.append("\n");
	}
}
