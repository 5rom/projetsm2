
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.univ_lyon1.master_info.m2ti.tiw5.services.CAODataBase;
import fr.univ_lyon1.master_info.m2ti.tiw5.services.CAODataBase_Service;






/**
 * Servlet implementation class ServletDBCAO
 */
public class ServletDBCAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * Printwriter pour les affichages
	 */
    private PrintWriter out;       
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDBCAO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out = response.getWriter();
		String bouton=request.getParameter("bouton");
		if (bouton.equals("Afficher les produits de la base")){
			CAODataBase_Service cAODBS= new CAODataBase_Service();
			CAODataBase cAODB = cAODBS.getCAODataBaseSOAP();
            out.println("<html><body>  Produits de la base\n<br>"+
	        		"<table border=\"1\">\n"+
	        		"<tr>\n"+
	        		"<th>Pnum</th>\n"+
	        		"<th>Pnom</th>\n"+
	        		"</tr>\n");
			//for chaque element de la liste de cAODB.getProductLists()
			//faire out.println(out.println("<tr><td>"+albums.get(0).getPiste(i).getTitre()+"</td><td>"+albums.get(0).getPiste(i).getDuree()+" min</td></tr>\n");
            
            out.println("</table>\n");
            
	        //Bouton retour
			out.println("<FORM Method=\"POST\" Action=\"index.jsp\">"+
			"<INPUT type=\"submit\" value=\"Retour\">"+
			"</FORM>"+				
					
			"</BODY></HTML>");            
		} else {
			Long pnum = Long.parseLong(request.getParameter("pnum"));
			String pnom = request.getParameter("pnom");
			CAODataBase_Service cAODBS= new CAODataBase_Service();
			CAODataBase cAODB = cAODBS.getCAODataBaseSOAP();
			cAODB.addProduit(pnum, pnom);
    		out.println("<HTML><BODY><br><br><h2>Ajout du produit "+pnom+" effectu&eacute; avec succ&egrave;s</h2><br><br>");
	        //Bouton retour
			out.println("<FORM Method=\"POST\" Action=\"index.jsp\">"+
			"<INPUT type=\"submit\" value=\"Retour\">"+
			"</FORM>"+				
					
			"</BODY></HTML>");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
