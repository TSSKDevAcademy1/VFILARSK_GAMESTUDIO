package serv1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Pattern p = Pattern.compile("[a-z]{5,10}");
	Pattern p2 = Pattern.compile("[0-9 a-z]{5,10}");
	Pattern pN = Pattern.compile("[0-9 a-z]{5,10}");

	/**
	 * Default constructor.
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		response.setContentType("text/html");

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Hello world</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<img src=\"resources/gfx/wildfly_400x130.jpg\"><br>");
		if (request.getParameter("meno") != null) {
			out.printf("<h1>Helo world %s\n</h1>", request.getParameter("meno"));
		}
		// out.println(new Date());
		out.println("<form action=\"hello\" method=\"post\" id=personForm>");
		out.printf("Name: <input type=\"text\" name=\"meno\" value= \"%s\">", request.getParameter("meno"));
		if (request.getParameter("meno") == null) {
		} else {
			Matcher a = p.matcher(request.getParameter("meno"));
			boolean c = a.matches();
			if (!c) {
				out.println("Zly format mena<br>");
			}
		}
		out.println("Password: <input type=\"password\" name=\"heslo\"><br>");
		if (request.getParameter("heslo") == null) {
		} else {
			Matcher m = p2.matcher(request.getParameter("heslo"));
			boolean b = m.matches();
			if (!b) {
				out.println("Zly format hesla<br>");
			}
		}
		out.println("Age: <input type=\"text\" name=\"vek\"><br>");
		if (request.getParameter("vek") != null) {
			Matcher s = pN.matcher(request.getParameter("vek"));
			boolean j = s.matches();
			int age1;
			if ((age1 = Integer.parseInt(request.getParameter("vek"))) <= 150 && j) {

			} else {
				out.println("Zle zadany vek<br>");
			}
		}
		out.println("<input type=\"submit\" value=\"Send!\">");
		out.println("</form>");
		
		/*
		out.println(<select name="stateList" form="personForm">, arg1);
		  <option value="volvo">Volvo</option>
		  <option value="saab">Saab</option>
		  <option value="opel">Opel</option>
		  <option value="audi">Audi</option>
		</select>
		*/
		
		out.println("<body>");

		out.println("</body>");
		out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
