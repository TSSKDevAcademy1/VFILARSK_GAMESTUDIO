package task2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.tools.examples.model.Member;

@Path("/persons")
@RequestScoped
public class RegisterRestService {
	
	@Inject
	private EntityManager entityManager;
	
	@Inject
	Register register;
	
	
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> listAllMembers() {
		
		
        return register.allPersons();
    }
	
	 @POST
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response createMember(Person person) {

	        Response.ResponseBuilder builder = null;

	        
	            // Validates member using bean validation
	            
	        	
	            entityManager.persist(person);
	          

	            // Create an "ok" response
	            builder = Response.ok();
	       

	        return builder.build();
	    }
	 
	  
}
