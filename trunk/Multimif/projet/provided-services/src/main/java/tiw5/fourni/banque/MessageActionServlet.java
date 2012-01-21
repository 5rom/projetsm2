package tiw5.fourni.banque;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class MessageActionServlet
 */
public class MessageActionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static Logger log = LoggerFactory.getLogger(MessageActionServlet.class);

	private MessageActionDAO dao;

	/**
	 * Default constructor.
	 */
	public MessageActionServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		WebApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
		dao = ctx.getBean(MessageActionDAO.class);
		ServletOutputStream out = response.getOutputStream();
		out.println("<html><head><title>Actions banque</title></head><body>");
		out.println("<h1>Actions disponibles</h1>");
		out.println("<p><a href=\"\">Rafraichir</a></p>");
		out.println("<table>");
		for (MessageAction ma : dao.getAllActions()) {
			out.println("<tr>");
			out.println("<td><form action=\"\" method=\"post\">"
					+ "<input type=\"hidden\" name=\"id\" value=\""
					+ ma.getId()
					+ "\"/>"
					+ "<input type=\"submit\" value=\"Declencher\" name=\"confirm\"/></form></td>");
			out.println("<td>" + ma.getTransactionId() + "</td>");
			out.println("<td>vers: "
					+ ma.getAdresseXMLAsString().replaceAll("<", "&lt;")
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
				.getWebApplicationContext(getServletContext());
		dao = ctx.getBean(MessageActionDAO.class);
		log.debug("in POST, confirm={}",request.getParameter("confirm"));
		if (request.getParameter("confirm") != null) {
			try {
				long id = Long.parseLong(request.getParameter("id"));
				log.debug("id parsed");
				dao.sendMessageAction(id);
			} catch (NullPointerException e) {
				log.warn("No id provided or no such action");
			} catch (NumberFormatException e) {
				log.warn("id n'est pas un long");
			}
		}
		doGet(request, response);
	}

}
