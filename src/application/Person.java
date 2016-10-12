package application;

public class Person {
	Person father;
	Person mother;
	  String name;
	  String [] siblings;
	  String [] cousins;
	
public Person(String name){
		this.name=name;
	}
	public Person(String name,String father, String mother){
		this.name=name;
		this.father= new Person(father);
		this.mother= new Person(mother);
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	public Person getFather(){
		return father;
	}
	public Person getMother(){
		return mother;
	}
}
