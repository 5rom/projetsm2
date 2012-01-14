package tiw5.fourni.livraison;

import java.io.IOException;
import java.text.DateFormat;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import tiw5.fourni.banque.MessageAction;
import tiw5.fourni.livraison.Livraison.Etat;

/**
 * Servlet implementation class LivraisonServlet
 */
public class LivraisonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(LivraisonServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LivraisonServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		WebApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		LivraisonUtil livUtil = (LivraisonUtil) ctx
				.getBean(LivraisonUtil.class);
		ServletOutputStream out = response.getOutputStream();
		out.println("<html><head><title>Actions livraisons</title></head><body>");
		out.println("<h1>Livraisons disponibles</h1>");
		out.println("<p><a href=\"\">Rafraichir</a></p>");
		out.println("<table>");
		out.println("<tr><th>Action</th><th>etat</th><th>de</th>"
				+ "<th>vers</th><th>id</th><th>cmdId</th><th>date</th><th>info</th></tr>");
		for (Livraison liv : livUtil.getAllLivraisons()) {
			String action = "";
			switch (liv.getEtat()) {
			case Demandee:
				action = "Mettre en attente";
				break;
			case EnAttente:
				action = "Lancer";
				break;
			case EnCours:
				action = "Terminer";
				break;
			case Livree:
				action = "Supprimer";
				break;
			}
			out.println("<tr>");
			out.println("<td><form action=\"\" method=\"post\">"
					+ "<input type=\"hidden\" name=\"id\" value=\""
					+ liv.getId() + "\"/>" + "<input type=\"submit\" value=\""
					+ action + "\" name=\"updt\"/></form></td>");
			out.println("<td>" + liv.getEtat() + "</td>");
			out.println("<td>" + liv.getDepuis() + "</td>");
			out.println("<td>" + liv.getVers() + "</td>");
			out.println("<td>" + liv.getId() + "</td>");
			out.println("<td>" + liv.getIdCmd() + "</td>");
			out.println("<td>"
					+ DateFormat.getDateInstance().format(liv.getDate())
					+ "</td>");
			out.println("<td>vers: "
					+ liv.getFeedbackEpr().replaceAll("<", "&lt;")
							.replaceAll(">", "&gt;") + " </td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		WebApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		LivraisonUtil livUtil = (LivraisonUtil) ctx
				.getBean(LivraisonUtil.class);
		if (request.getParameter("updt") != null) {
			try {
				long id = Long.parseLong(request.getParameter("id"));
				log.debug("id parsed");
				livUtil.miseAJour(id);
			} catch (NullPointerException e) {
				log.warn("No id provided or no such action");
			} catch (NumberFormatException e) {
				log.warn("id n'est pas un long");
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
		}
		doGet(request, response);
	}

}
