import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.owl.CAOOWL;
import fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.owl.CAOOWL_Service;

/**
 * Classe Servlet ServletOWLCAO
 * Appelle le service CAOOWLService pour traduire la BDR de l'expert CAO en OWL
 * Créée par Sébastien Faure et David Crescence
 * @author David CRESCENCE <crescence.david@gmail.com> et Sébastien FAURE <sebastien.faure3@gmail.com>
 * UCBL M2TI 2011-2012 
 */
public class ServletOWLCAO extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	/**
	 * Printwriter pour les affichages
	 */
    private PrintWriter out;       
    
	
	/**
     * @see HttpServlet#HttpServlet()
     */
	public ServletOWLCAO() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out = response.getWriter();
		CAOOWL_Service c = new CAOOWL_Service();
		CAOOWL f = c.getCAOOWLSOAP();
		//String s = new String(""+f.parseOWL(request.getParameter("file")));
		f.parseOWL(request.getParameter("file"));
		out.println("<html><body><h1>Traduction de la BD relationnelle en ontologie OWL</h1>" +
				"<p>URL du fichier OWL : <a href=\"file:"+request.getParameter("file")+"\">"+request.getParameter("file")+"</a></p><br>");
        //Bouton retour
		out.println("<FORM Method=\"POST\" Action=\"menucao.jsp\">"+
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
