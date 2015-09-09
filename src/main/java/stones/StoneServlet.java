package stones;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import game.PlayerController;
import game.Score;
import game.ScoreController;



@WebServlet("/stone")
public class StoneServlet extends HttpServlet {
	
	
	@Inject
	ScoreController scoreController;
	@Inject
	PlayerController playerController;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Field field;
		ConsoleUI consoleUI;
		long time;
		if(session.getAttribute("stoneField") == null){
		field = new Field(3,3);
		consoleUI = new ConsoleUI(field);
		session.setAttribute("stoneField", field);
		session.setAttribute("consoleUI", consoleUI);
		time = System.currentTimeMillis();
		session.setAttribute("time",time);
		} else{
			field = (Field)session.getAttribute("stoneField");
			consoleUI = (ConsoleUI)session.getAttribute("consoleUI");
			time = (long)session.getAttribute("time");
		}
		PrintWriter out = response.getWriter();

		response.setContentType("text/html");

		// if (request.getParameter("rowC") != null &&
		// request.getParameter("columnC") != null) {
		// field.openTile(Integer.parseInt(request.getParameter("rowC")),
		// Integer.parseInt(request.getParameter("columnC")));
		// update();
		// }
		if(request.getParameter("valueX") !=null && request.getParameter("valueY") != null) {
			field.move(Integer.parseInt(request.getParameter("valueX")), Integer.parseInt(request.getParameter("valueY")));
		}
	
		if(consoleUI.isSucessfull()){
			out.println("VYHRAL SI");
			Score score = scoreController.getActualScore();
			score.setGame("Mines");
			score.setPlayer(playerController.getPlayer().getName());
			score.setScore((System.currentTimeMillis() - time)/1000);
			scoreController.writeScore(score);
			time = System.currentTimeMillis();
			session.setAttribute("time", time);
			session.setAttribute("stoneField", field);
		}else{
		out.println(field.toString());
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	

}
