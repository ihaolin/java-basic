package me.hao0.basic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GroupTests {
	public static void main(String[] args) {
		Pattern p = Pattern.compile("(/d{3,5})([a-z]{2})");
		String s = "123aa-34345bb-234cc-00";
		Matcher m = p.matcher(s);
		while (m.find()) {
			System.out.println("m.group(1):" + m.group(1));// 打印数字的
			System.out.println("m.group(2):" + m.group(2));// 打印字母的
		}
	}
}
