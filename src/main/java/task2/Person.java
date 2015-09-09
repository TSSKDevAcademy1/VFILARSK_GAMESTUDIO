package task2;

import java.io.Serializable;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * register.Person.
 */
@Entity
public class Person implements Comparable<Person>, Serializable{
    /**
	 * 
	 */
	@Id
	@GeneratedValue
	private long id;
	private static final long serialVersionUID = 1L;

	/** Name of this person. */
    private String name;
    
    /** Phone number of this person. */
    private String phoneNumber;
    
    /**
     * Construct a person.
     * @param name name of the person
     * @param phoneNumber phone number of the person
     */
    public Person(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;        
    }
    
    public Person(){
    	this.name = "Vlado";
    	this.phoneNumber = "0145865782";
    }
            
    /**
     * Returns name of this person.
     * @return name of this person
     */
    public String getName(){
        return name;
    }
    
    /**
     * Sets name of this person.
     * @param nameNew name of this person
     */
    public void setName(String nameNew) {
        name = nameNew;
    }
    
    /**
     * Returns phone number of this person.
     * @return phone number of this person
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number of this person.
     * @param phoneNumberNew phone number of this person
     */
    public void setPhoneNumber(String phoneNumberNew) {
        this.phoneNumber = phoneNumberNew;
    }
    
    
    //TODO: Implement method isValidPhoneNumber
    /**
     * Validates the phone number. Valid phone numbers contains only digits.
     * @param phoneNumber phone number to validate
     * @return <code>true</code> if phone number is valid, <code>false</code> otherwise
     */
    private boolean isValidPhoneNumber(String phoneNumber) {     
    	/*Pattern p = Pattern.compile("[0-9]*");
		Matcher m = p.matcher(phoneNumber);
		boolean b = m.matches();
        if(b){
        	return true;
        } else {
        	return false;
        } */
    	boolean bool = true;
    	int length = phoneNumber.length();
    	for(int i = 0;i<length;i++){
    		if((bool = Character.isDigit(phoneNumber.charAt(i))) == false){
    			return true;
    		}
    	}
		return bool;
    }
    
    /**
     * Returns a string representation of the person.
     * @return string representation of the person.
     */
    public String toString() {
        return  name + " (" + phoneNumber + ")" +"\n";
    }

	@Override
	public int compareTo(Person o) {
		return this.name.compareTo(name);
}
}
