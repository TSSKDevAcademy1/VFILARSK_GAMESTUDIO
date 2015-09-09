package task2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;


@Stateless
@Default
public class ListRegister implements Register, Serializable {

	/**
	 * 
	 */
	@Inject
	private EntityManager entityManager;
	
	private List<Person> persons = new ArrayList<Person>();
	
	
	
	@Override
	public int getCount() {
		return persons.size();
	}

	
	
	@Override
	public int getSize() {
		return persons.size();
	}

	@Override
	public Person getPerson(int index) {
		return persons.get(index);
	}

	@Override
	public void addPerson(Person person) {
		entityManager.persist(person);
		
		
	}

	@Override
	public List<Person> findPersonByName(String name) {
		
		List<Person> persons = entityManager.createQuery("select p FROM Person p WHERE p.name = :name", Person.class).setParameter("name", name).getResultList();
		for(Person person : persons){
			System.out.println(person);
			}
		return persons;
		
	}
	
	@Override
	public Person findPersonByPhoneNumber(String phoneNumber) {
		//return persons.stream().filter(s -> s.getPhoneNumber().equalsIgnoreCase(phoneNumber)).findFirst().orElse(null);
		return null;
	}
	

	@Override
	public int getActual() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removePerson(Person person) {
		Iterator<Person> fIterator = persons.iterator();
		while(fIterator.hasNext()){
			if(fIterator.next().equals(person)){
				fIterator.remove();
			}
		}
	}
	

	
	public void vypis(){
		Collections.sort(persons);
		StringBuilder stringBuilder = new StringBuilder();
		System.out.println(persons.size());
		Formatter f = new Formatter();
		Iterator<Person> fIterator = persons.iterator();
		while(fIterator.hasNext()){
			stringBuilder.append(fIterator.next().toString()); 
			
		}
		System.out.println(stringBuilder.toString());
		f.close();
	}
	
	public List<Person> allPersons() {
		List<Person> persons = entityManager.createQuery("select p FROM Person p", Person.class).getResultList();
		return persons;
	}

}
