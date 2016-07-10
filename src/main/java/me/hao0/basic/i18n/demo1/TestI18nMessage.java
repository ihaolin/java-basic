package me.hao0.basic.i18n.demo1;

import org.junit.Test;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class TestI18nMessage {
	
	@Test
	public void testResourceBundle(){
		Locale curLocale = Locale.getDefault();
		ResourceBundle res = ResourceBundle.getBundle("resources/message", Locale.US);
		System.out.println(res.getString("hello"));
	}
	
	@Test
	public void testMessageFormat(){
		Locale curLocale = Locale.getDefault();
		ResourceBundle res = ResourceBundle.getBundle("resources/message", curLocale);
		System.out.println(MessageFormat.format(res.getString("test"), "林浩"));
	}
}
