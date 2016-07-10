package me.hao0.basic.nio;

public class TestComparable {

	public static void main(String[] args) {
		Person[]  persons = new Person[3];
		
		persons[0] = new Person("aa", 33);
		persons[1] = new Person("cc", 11);
		persons[2] = new Person("cc", 22);
		
		for (Person p : persons){
			System.out.println(p.getNum());
		}
	}

}

class Person implements Comparable<Person>{ 
	private String name;
	private int num;
	
	public Person(String name, int num) {
		this.name = name;
		this.num = num;
	}

	public int compareTo(Person o) {
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
