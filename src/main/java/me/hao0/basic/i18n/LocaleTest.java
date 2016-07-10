package me.hao0.basic.i18n;

import org.junit.Test;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class LocaleTest {
	
	@Test
	public void testFormat(){
		Locale loc = new Locale("en", "US");
		NumberFormat fmt = NumberFormat.getCurrencyInstance(loc);
		double amt = 123456.78;
		System.out.println(fmt.format(amt));
	}
	
	@Test
	public void testLocales(){
		Locale[] locales = (Locale[]) DateFormat.getAvailableLocales().clone();
		for (Locale loc : locales){
			System.out.println(loc.getDisplayName());
		}
	}
}
