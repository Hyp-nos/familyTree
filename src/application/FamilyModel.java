package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

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
		tempPerson.setMother(motherP);
		motherP.setGender(Gender.FEMALE);
		fatherP.setWife(motherP);
		tempPerson.setFather(fatherP);
		fatherP.setGender(Gender.MALE);
		motherP.setHusband(fatherP);

		if (husband != null) {
			tempPerson.setHusband(husbandP);
			husbandP.setWife(tempPerson);
		}
		if (wife != null) {
			tempPerson.setWife(wifeP);
			wifeP.setHusband(tempPerson);
		}

		System.out.println("finished ");

		db.addToDB(tempPerson);
		db.addToDB(fatherP);
		db.addToDB(motherP);
		if (wifeP != null) {
			db.addToDB(wifeP);
			savePersontoFile(wifeP);
		}
		if (husbandP != null) {
			db.addToDB(husbandP);
			savePersontoFile(husbandP);
		}

		db.deletePerson("NA");
		db.deletePerson("");
		savePersontoFile(tempPerson);
		savePersontoFile(fatherP);
		savePersontoFile(motherP);
		db.saveDb();

	}

	public void savePersontoFile(Person tempPer) {
		try

		{
			FileOutputStream fos = new FileOutputStream(tempPer.getName() + ".person");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(tempPer);
			oos.close();
		} catch (IOException e) {
			System.out.println("Something went wrong during saving this person");
			e.printStackTrace();
		}

	}

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
			System.out.println("File not found");
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
		String kids = "";
		String spouse="";
		if (spouse.equalsIgnoreCase("") || spouse.isEmpty()) {
			spouse = "N/A";
		}else
		 spouse = result.getWife().getName() + result.getHusband().getName();
		
		for (Person p : Database.db) {

			if (p.getMother() != null && p.getFather() != null)
				if (p.getMother().getName().equalsIgnoreCase(result.getName())
						|| p.getFather().getName().equalsIgnoreCase(result.getName()))

					kids += p.getName() + ", ";

		}
		try {

			view.txtArea.clear();
			view.txtArea.appendText(

					"Name: " + result.getName() + "\nAge: " + result.getAge() + "\nGender: " + result.getGender()
							+ "\nFather: " + result.getFather().getName() + "\nMother: " + result.getMother().getName()
							+ "\nSpouse: " + spouse + "\nChildren: " + kids

			);

			view.txtName.setText(result.getName());
			view.txtAge.setText(String.valueOf(result.getAge()));
			view.txtFather.setText(result.getFather().getName());
			view.txtMother.setText(result.getMother().getName());
			if (result.getHusband() != null)
				view.txtHusband.setText(result.getHusband().getName());
			if (result.getWife() != null)
				view.txtWife.setText(result.getWife().getName());

		} catch (NullPointerException ne) {
			System.out.println("Some data are missing");
		}

		catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("smthin went wrong");
		}
	}

	public ArrayList<Person> ShowGrandpa(String name) {
		try {
			System.out.println("presenting the oldies... ");
			ArrayList<Person> result = new ArrayList<>();
			result.add(((db.getPerson(name)).getFather()).getFather());
			result.add(((db.getPerson(name)).getMother()).getFather());
			return result;
		} catch (Exception e) {
			System.out.println("There are no grandfathers for this person");
			return null;
		}

	}

	public ArrayList<Person> showGrandma(String name) {
		System.out.println("presenting the Nannies... ");
		ArrayList<Person> result = new ArrayList<>();
		if (db.getPerson(name).getMother() != null && db.getPerson(name).getFather() != null)
			result.add(db.getPerson(name).getMother().getMother());
		result.add(db.getPerson(name).getFather().getMother());
		return result;

	}

	public ArrayList<Person> showSiblings(String name) {
		System.out.println("presenting brothers and sisters... ");
		ArrayList<Person> list = new ArrayList<>();
		for (Person p : Database.db) {
			if (p.getFather() != null && p != db.getPerson(name)) {
				if (p.getFather().getName().equalsIgnoreCase(db.getPerson(name).getFather().getName()))
					list.add(p);
			}
		}
		return list;
	}

	public ArrayList<Person> showCousins(String name) {
		System.out.println("presenting Cousins... ");
		ArrayList<Person> list = new ArrayList<>();
		for (Person p : Database.db) {
			// we should not loop throught the person himself
			if (!p.getName().equalsIgnoreCase(name)) {
				if (p.getFather() != null && p.getMother() != null && p.getFather().getFather() != null) {
					if ((p.getFather().getFather().getName()
							.equalsIgnoreCase(db.getPerson(name).getFather().getFather().getName()))
							|| (p.getMother().getMother().getName()
									.equalsIgnoreCase(db.getPerson(name).getMother().getMother().getName()))
							|| (p.getFather().getFather().getName()
									.equalsIgnoreCase(db.getPerson(name).getMother().getFather().getName()))
									|| (p.getMother().getFather().getName()
											.equalsIgnoreCase(db.getPerson(name).getFather().getFather().getName())

							))
						
					list.add(p);
				}
			}
		}	
		
		
		
		
		return list;
	}

}
