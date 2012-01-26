import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.univ_lyon1.master_info.m2ti.tiw5.services_owl.OWLMapping;
import fr.univ_lyon1.master_info.m2ti.tiw5.services_owl.OWLMapping_Service;

/**
 * Classe Servlet ServletOWLMapping
 * Appelle le service OWLMappingService pour réaliser le mapping entre les ontologies StyloCAO et StyloFAO
 * Créée par Sébastien Faure et David Crescence
 * @author David CRESCENCE <crescence.david@gmail.com> et Sébastien FAURE <sebastien.faure3@gmail.com>
 * UCBL M2TI 2011-2012 
 */
public class ServletOWLMapping extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
		
		/**
		 * Printwriter pour les affichages
		 */
	    private PrintWriter out;       
	    
		
		/**
	     * @see HttpServlet#HttpServlet()
	     */
		public ServletOWLMapping() {
	        super();
	    }
		
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			out = response.getWriter();
			OWLMapping_Service oMS = new OWLMapping_Service();
			OWLMapping oMP = oMS.getOWLMappingSOAP();
			String s=oMP.mapOWL("/tmp/mapping.txt", "/tmp/resmapping.txt");
			out.println("<html><body><h1>Fichier de mapping OWL obtenu</h1>" +
					"<p>URL du fichier OWL : <a href='" + s +"'> Ouvrir le fichier</a></p><br>");
			//Bouton retour
			out.println("<FORM Method=\"POST\" Action=\"index.jsp\">"+
			"<INPUT type=\"submit\" value=\"Retour\">"+
			"</FORM>");            
			out.println("</body></html>");
		}
		
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}
}
