package task2;

import java.util.ArrayList;
import java.util.List;


import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless
@WebService
public class HelloWorld {
	List<Student> students = new ArrayList<>();
	public String sayHello() {
		return "Hello world";
	}
	
public List<Student> getAllStudents(){
	students.add(new Student("Vlado", 24));
	students.add(new Student("Filip", 17));
	students.add(new Student("Vlado", 49));
	students.add(new Student("Drahotina", 45));
	return students;
	}

/*public List<Student> getStudentsStartingWith(String prefix){
	return students.stream().filter(s -> s.getName().startsWith(prefix)).collect(Collectors.toList());
}*/

}
