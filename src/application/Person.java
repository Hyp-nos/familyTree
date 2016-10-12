package application;

import java.io.Serializable;

public class Person implements Serializable {
	Person father;
	Person mother;
	Person husband;
	Person wife;
	  String name;
	  String [] siblings;
	  String [] cousins;
	  Gender gender;
	  int age;
	
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
public Person(String name){
		this.name=name;
	}
	public Person(String name,Person father, Person mother, Gender gender, Person husband, Person wife, int age){
		this.name=name;
		this.father= father;
		this.mother= mother;
		this.husband = husband;
		this.wife=wife;
		this.age=age;
		this.gender=gender;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
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
	public Person getFather(){
		return father;
	}
	public Person getMother(){
		return mother;
	}
	public String[] getSiblings() {
		return siblings;
	}
	public void setSiblings(String[] siblings) {
		this.siblings = siblings;
	}
	public String[] getCousins() {
		return cousins;
	}
	public void setCousins(String[] cousins) {
		this.cousins = cousins;
	}
}
