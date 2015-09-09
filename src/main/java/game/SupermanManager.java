package game;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
@Named
@RequestScoped
public class SupermanManager {
	@Inject
	EntityManager entityManager;
	

   
	@Inject
    private Superman superman;
	
  
    @Inject
    PlayerRegistration pRegistration;
    
    @Inject
    EntityManager em;
    
	
	public String login(){
		List<Player> players = em.createQuery("select DISTINCT p FROM Player as p WHERE p.name = :name and p.password = :password", Player.class)
        .setParameter("name", superman.getName())
        .setParameter("password", superman.getPassword())
        .getResultList();	
		Player player = players.get(0);
		System.out.println(superman.getName());
		System.out.println(player.getName());
		if(!(superman.getName() == player.getName())){
			System.out.println("prva volba");
			return "/choice.jsf";
		} else {
			System.out.println("druha volba");
			return "localhost:8080/jboss-javaee6-webapp/gameChoice.xhtml";
		}
	}
	
	public String returnString(){
		return "/choice.xhtml";
	}
	
	
}
