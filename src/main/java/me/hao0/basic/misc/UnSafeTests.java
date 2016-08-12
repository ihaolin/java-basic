package me.hao0.basic.misc;

import me.hao0.basic.entities.Person;
import org.junit.Before;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * UnSafe测试
 * 下午3:22:40
 */
public class UnSafeTests {
	private static Unsafe UNSAFE;
	@Before
	public void init() throws Exception{
		Class<?> clazz = Class.forName("sun.misc.Unsafe");
		Field field = clazz.getDeclaredField("theUnsafe");
		field.setAccessible(true);
		UNSAFE = (Unsafe) field.get(null);
	}
	
	/**
	 * 
	 */
	@Test
	public void testArrayIndexScale(){
		System.out.println(UNSAFE.arrayIndexScale(Person[].class));
	}
	
	/**
	 * 获取内存中数组类型的首偏移量
	 */
	@Test
	public void testArrayBaseOffset(){
		System.out.println(UNSAFE.arrayBaseOffset(Person[].class));
	}
	
	@Test
	public void testPutOrderedObject(){
		Person[] persons = new Person[3];
		int offset = UNSAFE.arrayBaseOffset(Person[].class);
		UNSAFE.putOrderedObject(persons, offset , new Person(3L, "linhao"));
		for (int i=0; i<persons.length; i++){
			System.out.println(persons[i]);
		}
	}
	
	/**
	 * 得到属性域偏移量
	 */
	@Test
	public void testObjectFieldOffset() throws NoSuchFieldException, SecurityException{
		long idOffset = UNSAFE.objectFieldOffset(Person.class.getDeclaredField("id"));
		long nameOffset = UNSAFE.objectFieldOffset(Person.class.getDeclaredField("name"));
		System.out.println("id offset: " + idOffset);
		System.out.println("name offset: " + nameOffset);
	}
	
	@Test
	public void testFields() throws NoSuchFieldException, SecurityException{
		long idOffset = UNSAFE.objectFieldOffset(Person.class.getDeclaredField("id"));
		long nameOffset = UNSAFE.objectFieldOffset(Person.class.getDeclaredField("name"));
		long ageOffset = UNSAFE.objectFieldOffset(Person.class.getDeclaredField("age"));
		Person p = SpeedReflector.newInstance(Person.class);
		UNSAFE.putInt(p, idOffset, 1);
		UNSAFE.putObject(p, nameOffset, "linhao");
		UNSAFE.putLong(p, ageOffset, 22L);
		System.out.println(p);
	}
	
	@Test
	public void testNewInstances() throws InstantiationException, IllegalAccessException{
		int count = 1000000000;
		long start = System.currentTimeMillis();
		Person p;
		for (int i=0; i<count; i++){
			//p = Person.class.newInstance();
			p = (Person)UNSAFE.allocateInstance(Person.class);
		}
		System.out.println("cost : " + (System.currentTimeMillis() - start));
	}
}
