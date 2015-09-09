package game;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class ScoreController implements Serializable{
	@Inject
	ScoreSaver scoreSaver;
	@Inject
	PlayerController pController;
	@Produces
	@Named
	Score newScore;
	List<Score> scores;
	List<Score> stoneScores;
	List<Score> mineScores;
	
	
	@PostConstruct
    public void initNewScore() {
        newScore = new Score();
    }
	
	public void writeScore(Score score){
		if(pController.getPlayer().getName() != null){
		scoreSaver.saveScore(score);
		initNewScore();
		}
	}
	
	public List<Score> getAllScore(String game){
		return scoreSaver.loadScore(game);
	}
	
	public Score getActualScore(){
		return newScore;
	}
	
	public List<Score> getScores(String game){
		return scoreSaver.loadScore(game);
	}
	
	public List<Score> getMinesScores(){
		return scoreSaver.loadScore("Mines");
	}
	
	public List<Score> getStonesScores(){
		return scoreSaver.loadScore("Stones");
	}
	
	
	
}
