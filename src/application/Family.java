package application;

import java.util.ArrayList;

public class Family  {
	String familyName;
	Person husband;
	Person wife;
	ArrayList<Person> household ;
	
	public Family(String familyName,Person husband, int husbandAge, Person wife, int wifeAge){
		
		this.husband=husband;
		this.husband.setGender(Gender.MALE);
		this.husband.setAge(husbandAge);
		
		this.wife=wife;
		this.wife.setGender(Gender.FEMALE);
		this.wife.setAge(wifeAge);
		
		this.familyName=familyName;
		this.husband.setWife(wife);
		this.wife.setHusband(husband);

		household.add(this.husband);
		household.add(this.wife);
	}
	
	public void addChildren(Person person){
		household.add(person);
	}
	
	public ArrayList<Person> getMembers(Family family){
		return household;
	}
	
	

}
