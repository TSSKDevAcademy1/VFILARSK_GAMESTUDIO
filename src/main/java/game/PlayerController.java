package game;



import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.tools.examples.model.Member;
import javax.enterprise.inject.New;

@Named
@SessionScoped
public class PlayerController implements Serializable {
	@Inject
	EntityManager entityManager;
	@Inject
	ActualUser actualUser;
	
    @Produces
    @Named
    private Player newPlayer;
	
    @PostConstruct
    public void initNewPlayer() {
        newPlayer = new Player();
    }
    @Inject
    PlayerRegistration pRegistration;
    
    @Inject
    EntityManager em;
    
	public void addPlayer(){
		System.out.println("pridanie hraca");
		pRegistration.addPlayer(newPlayer);
		initNewPlayer();
	}
	
	public String login(){
		List<Player> players = em.createQuery("select DISTINCT p FROM Player as p WHERE p.name = :name and p.password = :password", Player.class)
        .setParameter("name", newPlayer.getName())
        .setParameter("password", newPlayer.getPassword())
        .getResultList();	
		Player player = players.get(0);
		System.out.println(newPlayer.getName());
		System.out.println(player.getName());
		if(!(newPlayer.getName() == player.getName())){
			System.out.println("prva volba");
			return "/choice.xhtml";
		} else {
			actualUser.setName(newPlayer.getName());
			actualUser.setPassword(newPlayer.getPassword());
			System.out.println("druha volba");
			return "localhost:8080/jboss-javaee6-webapp/gameChoice.xhtml";
		}
	}
	
	public void register(){
		pRegistration.addPlayer(newPlayer);
		}
	
	
	public Player getPlayer(){
		return newPlayer;
	}
	
}
