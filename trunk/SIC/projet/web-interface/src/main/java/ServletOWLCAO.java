import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.owl.CAOOwl;
import fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.owl.CAOOwl_Service;


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
		CAOOwl_Service cAOOS = new CAOOwl_Service();
		CAOOwl cAOO = cAOOS.getCAOOwlSOAP();
		out.println("<html><body><h2>"+cAOO.parseOWL("")+"</h2></body></html>");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
