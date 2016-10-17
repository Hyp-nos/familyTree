package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.sun.javafx.logging.Logger;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import sun.security.action.OpenFileInputStreamAction;
import sun.util.logging.PlatformLogger.Level;

public class FamilyModel {
	FamilyView view;
	Person tempPerson;
	FileChooser fileChooser = new FileChooser();
	Database db = new Database();
	File selectedFile;
	Person result1;

	public FamilyModel(FamilyView view) {
		this.view = view;

		db.loadDb();
	}

	public void createPerson(String name, String father, String mother, String gender, String husband, String wife,
			int age) {

		Gender gen;
		if (gender.equalsIgnoreCase("Male"))
			gen = Gender.MALE;
		else
			gen = Gender.FEMALE;
		// i have to create the inner persons first to be able to replace them
		// if they existed
		Person fatherP;
		Person motherP;
		Person wifeP;
		Person husbandP;
		
		
		if (db.searchName(father)) {
			 fatherP = db.getPerson(father);
		} else {
			 fatherP = new Person(father); 
		}

		if (db.searchName(mother)) {
			 motherP = db.getPerson(mother);
		} else {
			 motherP = new Person(mother);
		}

		if (db.searchName(husband)) {
			 husbandP = db.getPerson(husband);
		} else {
			 husbandP = new Person(husband);
		}

		if (db.searchName(wife)) {
			 wifeP = db.getPerson(wife);
		} else {
			 wifeP = new Person(wife);
		}

		if (db.searchName(name)) {
			System.out.println("the person is found");
			tempPerson = db.getPerson(name);

		} else {
			System.out.println("the name is not found so create tempPerson");
			tempPerson = new Person(name);
		}

		tempPerson.setGender(gen);
		tempPerson.setAge(age);
		tempPerson.setMother(motherP); motherP.setGender(Gender.FEMALE); fatherP.setWife(motherP);
		tempPerson.setFather(fatherP); fatherP.setGender(Gender.MALE);	motherP.setHusband(fatherP);
		
		if (husband != null) {
			tempPerson.setHusband(husbandP);
			husbandP.setWife(tempPerson);
		}
		if (wife != null) {
			tempPerson.setWife(wifeP);
			wifeP.setHusband(tempPerson);
		}
		motherP.addChildren(tempPerson);
		fatherP.addChildren(tempPerson);

	System.out.println("finished ");
	

	db.addToDB(tempPerson);
	db.addToDB(fatherP);
	db.addToDB(motherP);
	db.addToDB(wifeP);
	db.addToDB(husbandP);
	db.saveDb();
	db.deletePerson("");

	try

	{
		FileOutputStream fos = new FileOutputStream(tempPerson.getName() + ".person");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(tempPerson);
		oos.close();
	}catch(
	IOException e)
	{

		e.printStackTrace();
	}

	}

	/*
	 * private String checkifExist(String name) { Person return null; }
	 */


	public String updateViewAfterAdd() {
		String result = null;
		if (tempPerson.getGender() == Gender.MALE)
			result = "Mr. ";
		else
			result = "Ms. ";

		return result + " " + tempPerson.getName() + " Has been added.\n";

	}

	public void loadPerson() {
		try {

			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Person Files ", "*.person"));

			selectedFile = fileChooser.showOpenDialog(view.stage);

			if (selectedFile != null) {
				FileInputStream fis = new FileInputStream(selectedFile.getName());
				ObjectInputStream ois = new ObjectInputStream(fis);
				result1 = (Person) ois.readObject();
				ois.close();

				OpenFile(result1);

			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void OpenFile(Person result) {

		try {
			view.txtArea.clear();
			view.txtArea.appendText(

					"Name: " + result.getName() + "\nAge: " + result.getAge() + "\nGender: " + result.getGender()
							+ "\nFather Name: " + result.getFather().getName()
			 +"\nChildren: " + Person.loopArray(result.getChildren())

			);
			view.txtName.setText(result.getName());
			view.txtAge.setText(String.valueOf(result.getAge()));
			view.txtFather.setText(result.getFather().getName());
			view.txtMother.setText(result.getMother().getName());
			if (result.getHusband()!=null)
			view.txtHusband.setText(result.getHusband().getName());
			if(result.getWife()!=null)
			view.txtWife.setText(result.getWife().getName());
			
			

		}catch (NullPointerException ne){
			System.out.println("Some data are missing");
		}
		
		
		
		
		catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("smthin went wrong");
		}
	}

	public Person ShowGrandpa(String name) {
		
		return ((db.getPerson(name)).getFather()).getFather();
		
	}
public Person showGrandma(String name) {
		try{
		return (db.getPerson(name)).getMother().getMother();}
		catch (Exception e){view.txtArea.appendText("This person has no grandma");}
		return new Person("noooobody");
	}

}
