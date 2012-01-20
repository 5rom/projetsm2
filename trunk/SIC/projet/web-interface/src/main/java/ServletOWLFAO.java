import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.univ_lyon1.master_info.m2ti.tiw5.services_fao.owl.FAOOwl;
import fr.univ_lyon1.master_info.m2ti.tiw5.services_fao.owl.FAOOwl_Service;


public class ServletOWLFAO extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	/**
	 * Printwriter pour les affichages
	 */
    private PrintWriter out;       
    
	
	/**
     * @see HttpServlet#HttpServlet()
     */
	public ServletOWLFAO() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out = response.getWriter();
		FAOOwl_Service f=new FAOOwl_Service();
		FAOOwl fa = f.getFAOOwlSOAP();
		String s = fa.parseOWL("bd.exp");
		out.println("<html><body><h1>Conversion du fichier express en fichier OWL</h1>" +
				"<p>URL du fichier OWL : <a href='" + s +"'> Ouvrir le fichier</url></p></body></html>");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
