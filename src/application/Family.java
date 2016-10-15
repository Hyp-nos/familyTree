package application;

import java.io.Serializable;
import java.util.ArrayList;

public class Family implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String familyName;

	ArrayList<Person> household;

	public Family(String familyName) {

		this.familyName = familyName;

	}

	public void addMember(Person person) {
		household.add(person);
	}

	public ArrayList<Person> getMembers(Family family) {
		return household;
	}

	public Person getMember(Person person) {
		Person result = null;
		for (Person p : household) {
			if (person.getName().equalsIgnoreCase(p.getName())) {
				result = p;
				return result;
			}
		}

		return result;
	}

	public boolean isMember(Person person) {
		boolean result = false;
		for (Person p : household) {
			if (person.getName().equalsIgnoreCase(p.getName())) {
				return true;
			}

		}

		return result;
	}
}