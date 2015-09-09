package game;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Named
@SessionScoped
public class CommentController implements Serializable {

	@Inject
	CommentSaver commentSaver;
	@Inject
	PlayerController pController;
	
    @Produces
    @Named
    private Comment newComment;
	
    private List<Comment> mineComments;
    
    
    @PostConstruct
    public void initNewComment() {
        newComment = new Comment();
    }
    
    
    @Inject
    EntityManager em;
    
	public void addMineComment(){
		newComment.setPlayer(pController.getPlayer().getName());
		newComment.setGame("mines");
		commentSaver.saveComment(newComment);
		initNewComment();
	}
	
	public void addStoneComment(){
		newComment.setPlayer(pController.getPlayer().getName());
		newComment.setGame("stones");
		commentSaver.saveComment(newComment);
		initNewComment();
	}

	public List<Comment> getMineComments() {
		return commentSaver.loadComments("mines");
	}
	
	public List<Comment> getStoneComments() {
		return commentSaver.loadComments("stones");
	}

	
	
	
	
	
}
