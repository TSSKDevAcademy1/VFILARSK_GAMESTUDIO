package game;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class CommentSaver {
	@Inject
	EntityManager entityManager;
	
	public void saveComment(Comment comment){
		entityManager.persist(comment);
	}
	
	public List<Comment> loadComments(String game){
		return entityManager.createQuery("select c from Comment as c where c.game = :game", Comment.class).setParameter("game", game).getResultList();
	}
	
}
