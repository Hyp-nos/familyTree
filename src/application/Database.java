package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Database {
	private static final long serialVersionUID = 1L;
	public static ArrayList<Person> db = new ArrayList<>();
	Person personTemp = null;

	public Database() {
	

	}

	public  void addToDB(Person person) {
		if(searchName(person.getName())){
			System.out.println("the person is already in database");
		} else
		db.add(person);
		
	}

	public  Person getPerson(String p) {
		try{
		for (int i = 0; i < db.size(); i++) {
			if (db.get(i).getName().equalsIgnoreCase(p)){
				personTemp = db.get(i);
				}
		}

		return personTemp;}
		catch (Exception e){
			System.out.println("Person not found");
			return null;
		}
	}



	public  String showDb() {
		String result="";
		
		for (Person p : db) {
			
			result += "\nName: "+p.getName();
		}
		return result+"\n --------------\n";
	}

	public  void saveDb() {
		try {
			FileOutputStream fos = new FileOutputStream("Database.db");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeUnshared(db);
			oos.close();
			System.out.println("changes are saved!");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public  void loadDb() {
		try {

			FileInputStream fis = new FileInputStream("Database.db");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<Person> result = (ArrayList<Person>) ois.readObject();
			ois.close();

			setDb(result);

		} catch (FileNotFoundException e1) {
			System.out.println("There was no database so we created one for you");
			this.saveDb();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}

	private  void setDb(ArrayList<Person> result) {
		db = result;

	}

	public  boolean searchName(String name) {
		boolean result = false;

		for (Person pp : db) {
			if (name.equalsIgnoreCase(pp.getName())) {
				System.out.println("this name found  >>>"+name);
				result = true; break;
			} else
				result = false;

		}
		return result;

	}

	public void deletePerson(String name) {
		for (Person pp: db){
			if(name.equalsIgnoreCase(pp.getName())) {db.remove(pp); saveDb(); break;}
			
			
		}
		
	}

}
