package application;

import java.io.Serializable;

public class FamilyModel  {
	FamilyView view;
	Person tempPerson;
	
	public FamilyModel(FamilyView view){
		this.view=view;
	}
	public void createPerson(String name, String father, String mother, String gender, String husband, String wife, int age) {
		Gender gen ;
		if (gender.equalsIgnoreCase("Male")) gen= Gender.MALE; else gen= Gender.FEMALE;
		 tempPerson = new Person(name, new Person(father),new Person(mother),
				 
				 gen,new Person(husband),new Person(wife),age);
		
	}
	public String updateView() {
		String result=null;
		 if(tempPerson.getGender()==Gender.MALE)
			 result = "Mr. ";
		 else result ="Ms. ";

		return result +" "+tempPerson.getName()+" Has been added.\n";
			 
	}
}
