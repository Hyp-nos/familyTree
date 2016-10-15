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
	public  ArrayList<Person> db = new ArrayList<>();
	

	public Database() {
	

	}

	public  void addToDB(Person person) {
		db.add(person);
	}

	public  Person getPerson(String p) {
		Person person = null;
		for (int i = 0; i < db.size(); i++) {
			if (db.get(i).getName().equalsIgnoreCase(p))
				person = db.get(i);
		}

		return person;
	}



	public  void showDb() {
		for (Person p : db) {
			
			System.out.println( p.getName());
		}
	}

	public  void saveDb() {
		try {
			FileOutputStream fos = new FileOutputStream("Database.db");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(db);
			oos.close();
			System.out.println("Person is saved, supposly");
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

	private  void setDb(ArrayList<Person> result) {
		db = result;

	}

	public  boolean searchName(String name) {
		boolean result = false;
		Person p = new Person(name);
		for (Person pp : db) {
			if (p.getName().equalsIgnoreCase(pp.getName())) {
				System.out.println("this name found  >>>"+pp.getName());
				result = true;
			} else
				result = false;

		}
		return result;

	}

}
