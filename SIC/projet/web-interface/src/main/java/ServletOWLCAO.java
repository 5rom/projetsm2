import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.owl.CAOOWL;
import fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.owl.CAOOWL_Service;


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
		String s = new String(""+f.parseOWL("/tmp/StyloCAO.owl"));
		out.println("<html><body><h1>Traduction de la BD relationnelle en ontologie OWL</h1>" +
				"<p>URL du fichier OWL : <a href='" + s.replace("file:", "") +"'> "+s.replace("file:", "")+"</a></p><br>");
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
