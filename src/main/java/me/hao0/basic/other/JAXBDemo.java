package me.hao0.basic.other;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;


public class JAXBDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File xmlFile = new File("e:\\temp\\test.xml");
		JAXBContext context;
		
		try {
			context = JAXBContext.newInstance(Student.class);
			Marshaller m = context.createMarshaller();
			
			Student s = new Student();
			s.setName("haolin");
			s.setNumber(2010051030);
			m.marshal(s, xmlFile);
			System.out.println("marshal finished!");
			
			Unmarshaller u = context.createUnmarshaller();
			
			Student s1 = (Student)u.unmarshal(xmlFile);
			System.out.println(s1.getName()+" "+ s1.getNumber());
			System.out.println("un marshal finished!");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}

@XmlRootElement
class Student{
	String name;
	Integer number;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
}
