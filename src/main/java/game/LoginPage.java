package game;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.User;

/**
 * Servlet implementation class LoginPage
 */
@WebServlet("/loginPage")
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernatePerzistence");
	private EntityManager em = factory.createEntityManager();
	EntityTransaction transaction = em.getTransaction();

    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		User user = new User();
		user.setAge(15);
		user.setName("Vlado");
		transaction.begin();
		em.persist(user);
		transaction.commit();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
