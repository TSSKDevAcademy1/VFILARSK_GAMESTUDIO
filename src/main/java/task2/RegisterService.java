package task2;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebService;
import javax.persistence.EntityManager;



@Singleton
@WebService(name="serv1")
public class RegisterService {
		@Inject @Default
		Register register;
		
		@Inject
		private EntityManager entityManager;

		public int getCount() {
			return register.getCount();
		}

		public int getSize() {
			return register.getSize();
		}

		public Person getPerson(int index) {
			return register.getPerson(index);
		}

		public void addPerson(Person person) {
			
			entityManager.persist(person);
		}

		public List<Person> findPersonByName(String name) {
			List<Person> persons = entityManager.createQuery("select p FROM Person p WHERE p.name = :name", Person.class).setParameter("name", name).getResultList();
			return persons;
		}

		public List<Person> findPersonByPhoneNumber(String phoneNumber) {
			List<Person> persons = entityManager.createQuery("select p FROM Person p WHERE p.phoneNumber = :phoneNumber", Person.class).setParameter("phoneNumber", phoneNumber).getResultList();
			return persons;
			
		}

		public int getActual() {
			return register.getActual();
		}

		public void removePerson(Person person) {
			register.removePerson(person);
		}

		public void vypis() {
			register.vypis();
		}
		
		public List<Person> allPersons() {
			List<Person> persons = entityManager.createQuery("select p FROM Person p", Person.class).getResultList();
			return persons;
		}
		
}
