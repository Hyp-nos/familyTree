package application;

public class FamilyModel {
	FamilyView view;
	Person tempPerson;
	
	public FamilyModel(FamilyView view){
		this.view=view;
	}
	public void createPerson(String name, String father, String mother) {
		 tempPerson = new Person(name, father,mother);
		
	}
	public String updateView() {
		
		return "name of person: "+ tempPerson.getName() + "\nHis Father is: "+tempPerson.getFather().getName()+"\nHis mom is: "+tempPerson.getMother().getName();
	}
}
