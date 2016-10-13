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

public class FamilyModel  {
	FamilyView view;
	Person tempPerson;
	FileChooser fileChooser = new FileChooser();
	
	File selectedFile;
	Person result;
	
	public FamilyModel(FamilyView view){
		this.view=view;
	}
	public void createPerson(String name, String father, String mother, String gender, String husband, String wife, int age) {
		Gender gen ;
		if (gender.equalsIgnoreCase("Male")) gen= Gender.MALE; else gen= Gender.FEMALE;
		
		 
	/*	try {
			FileInputStream fis = new FileInputStream("Person.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			 result = (Person) ois.readObject();
			ois.close();
			
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
		
		
		if (result.getName())*/
		
		
		
		
		 tempPerson = new Person(name, new Person(father),new Person(mother),
				 
				 gen,new Person(husband),new Person(wife),age);
		 
			try {
				FileOutputStream fos = new FileOutputStream(tempPerson.getName()+".person");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(tempPerson);
				oos.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
	}
	public String updateViewAfterAdd() {
		String result=null;
		 if(tempPerson.getGender()==Gender.MALE)
			 result = "Mr. ";
		 else result ="Ms. ";

		return result +" "+tempPerson.getName()+" Has been added.\n";
			 
	}
	public void loadPerson() {
		try {
		
			fileChooser.getExtensionFilters().addAll(
			          new ExtensionFilter("Person Files ", "*.person"));
			
			  selectedFile = fileChooser.showOpenDialog(view.stage);
			 
			 if(selectedFile!=null){
				 FileInputStream fis = new FileInputStream(selectedFile.getName());
				 ObjectInputStream ois = new ObjectInputStream(fis);
			 result = (Person) ois.readObject();
			ois.close();
				
				 OpenFile(result);
			
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
	private void OpenFile(Person file) {
		
		try {
			view.txtArea.clear();
			view.txtArea.appendText(
					
					"Name: "+ result.getName()+
					"\nAge: "+ result.getAge()+
					"\nGender: " + result.getGender()
					
					
					
					);
			
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
		

}
