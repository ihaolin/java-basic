package me.hao0.basic.nio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TestComparator {

	public static void main(String[] args) {
		ArrayList<Person0> persons = new ArrayList<Person0>();
		
		persons.add(new Person0("aa", 33));
		persons.add(new Person0("bb", 23));
		persons.add(new Person0("cc", 13));
		persons.add(new Person0("dd", 43));
		persons.add(new Person0("ee", 32));
		
		Collections.sort(persons, new MyComparator());
		
		for (Person0 p : persons){
			System.out.println(p.getNum());
		}
	}

}

class MyComparator implements Comparator<Person0>{

	public int compare(Person0 o1, Person0 o2) {
		return o1.getNum() - o2.getNum();
	}
	
}

class Person0 implements Comparable<Person0>{ 
	private String name;
	private int num;
	
	public Person0(String name, int num) {
		this.name = name;
		this.num = num;
	}

	public int compareTo(Person0 o) {
		return this.num - o.num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
