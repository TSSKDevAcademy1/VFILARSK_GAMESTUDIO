package minesweeper.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import game.ActualUser;

import game.Informal;
import game.Player;
import game.PlayerController;
import game.Score;
import game.ScoreController;
import game.Superman;
import servlet.User;
import javax.inject.Named;
import javax.enterprise.inject.New;
@SessionScoped
@WebServlet("/hello")
public class MineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Field field = new Field(8, 8, 2);
	@Inject
	PlayerController playerController;
	@Inject
	ScoreController scoreController;
	
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Field field;
		long time;
		if((session.getAttribute("field") == null)){
			field = new Field(8, 8, 2);
			time = System.currentTimeMillis();
			session.setAttribute("time", time);
			session.setAttribute("field", field);
			System.out.println("nebol field");
		} else{ 
		time = (long)session.getAttribute("time");
		field = (Field)session.getAttribute("field");
		System.out.println("bol field");
		}
		PrintWriter out = response.getWriter();

		response.setContentType("text/html");

		out.println(playerController.getPlayer().getName());
		out.println((System.currentTimeMillis() - time)/1000);
		out.println("<br>");
		if (!(field.getGameState().equals(GameState.FAILED) && field.getGameState().equals(GameState.SOLVED))) {
			if (request.getParameter("rowC") != null && request.getParameter("columnC") != null) {
				field.openTile(Integer.parseInt(request.getParameter("rowC")),
						Integer.parseInt(request.getParameter("columnC")));
				//update();
			}
			out.println(field.toString());
		}
		if (field.getGameState().equals(GameState.FAILED)) {
			out.println("<br>Prehral si<br>");
			field = new Field(8,8,5);
			time = System.currentTimeMillis();
			session.setAttribute("field", field);
			field.setGameState(GameState.PLAYING);
			
		} else if (field.getGameState().equals(GameState.SOLVED)) {
			out.println("<br>Vyhral si<br>");
			Score score = scoreController.getActualScore();
			score.setGame("Mines");
			score.setPlayer(playerController.getPlayer().getName());
			score.setScore((System.currentTimeMillis() - time)/1000);
			scoreController.writeScore(score);
			time = System.currentTimeMillis();
			session.setAttribute("time", time);
			session.setAttribute("field", field);
		} else {

		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	/*
	public void update() {
		if (field.getGameState().equals(GameState.PLAYING)) {

			System.out.println("Pocet nenajdenych min: " + field.getRemainigMineCount());
			String pole = field.toString();
			System.out.printf("%4s", " ");
			for (int i = 1; i <= field.getColumnCount(); i++) {
				System.out.printf("%4s", i);
			}
			System.out.printf("%n");
			System.out.printf("%n");
			for (int r = 0; r < field.getRowCount(); r++) {
				for (int c = 0; c < field.getColumnCount(); c++) {
					System.out.printf("%4s", field.getTile(r, c));
				}
				System.out.printf("%n");
			}
		} else if (field.getGameState().equals(GameState.SOLVED)) {
			System.out.println("Vyhral si");
		} else {
			System.out.println("Prehral si");
		}

	}
	*/
}
