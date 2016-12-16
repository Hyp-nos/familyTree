package application;

import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {
	Person father;
	Person mother;
	Person husband;
	Person wife;
	String name;

	Gender gender;
	int age;
	private static final long serialVersionUID = 1L;
	
	public Person(String name, Person father, Person mother, Gender gender, Person husband,
			Person wife, int age) {
		
		this.name = name;
		this.father = father;
		this.mother = mother;
		this.husband = husband;
		this.wife = wife;
		this.age = age;
		this.gender = gender;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Gender getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Person(String name) {
		this.name = name;
	}

	

	public String getName() {
		if (name != null)
			return name;
		else
			return "NA";
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person getHusband() {
		return husband;
	}

	public void setHusband(Person husband) {
		this.husband = husband;
	}

	public Person getWife() {
		return wife;
	}

	public void setWife(Person wife) {
		this.wife = wife;
	}

	public void setFather(Person father) {

		this.father = father;
	}

	public void setMother(Person mother) {
		this.mother = mother;
	}

	public Person getFather() {
		if (father != null)
			return father;
		else
			return null;
	}

	public Person getMother() {
		if (mother != null)
			return mother;
		else
			return null;
	}

	public static String loopArray(ArrayList<Person> array) {
		String result = "\n";
		try {
		for (Person p : array) {
			if(p!=null){
			result += p.getName() + ",  ";
			}
		}
		return result;

	}catch	(Exception e)
	{
		System.out.println("Something went wrong during looping this array");
		e.printStackTrace();
	}
		return null;
	}

}
