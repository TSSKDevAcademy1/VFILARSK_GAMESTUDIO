package game;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class PlayerRegistration {

	@Inject
	EntityManager em;
	public void addPlayer(Player newPlayer){
		em.persist(newPlayer);
		
	}
}
