package game;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Stateless
public class ScoreSaver {
	@Inject
	EntityManager em;
	
	
	
	public void saveScore(Score score){
		List<Score> scores = this.loadScore(score.getGame());
		Long maximum;
		em.persist(score);
		//if(scores.size() > 10 ){
		//	List<Long>  maxScore = em.createQuery("select max(s.score) from Score s",Long.class).getResultList();
		//	maximum = maxScore.get(0);
		//	loadScore(maximum);
		//}
		
	}
	
	public void loadScore(Long maximum){
		em.createQuery("delete from Score as score where  score.score = :maximum",Score.class).setParameter("maximum", maximum).getResultList();
	}
	
	public List<Score> loadScore(String game){
		return em.createQuery("select s from Score as s where s.game = :game ORDER BY s.score",Score.class).setParameter("game", game).getResultList();
	}
}
