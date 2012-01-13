import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.univ_lyon1.master_info.m2ti.tiw5.services_fao.FAODataBase;
import fr.univ_lyon1.master_info.m2ti.tiw5.services_fao.PmajeurPmineur;
import fr.univ_lyon1.master_info.m2ti.tiw5.services_fao.PnumPnom;
import fr.univ_lyon1.master_info.m2ti.tiw5.services_fao.FAODataBase;
import fr.univ_lyon1.master_info.m2ti.tiw5.services_fao.FAODataBase_Service;


public class ServletExpressFAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Printwriter pour les affichages
	 */
    private PrintWriter out;       
    
	
	/**
     * @see HttpServlet#HttpServlet()
     */
	public ServletExpressFAO() {
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
			FAODataBase_Service fao_service= new FAODataBase_Service();
			FAODataBase fao_express = fao_service.getFAODataBaseSOAP();
			out.println("<html><body>  Produits de la base\n<br>"+
	        		"<table border=\"1\">\n"+
	        		"<tr>\n"+
	        		"<th>Pnum</th>\n"+
	        		"<th>Pnom</th>\n"+
	        		"</tr>\n");
           List<PnumPnom> aLPN = fao_express.getProduitList();
           for (int i=0; i<aLPN.size();i++){
        	   out.println("<tr><td>"+aLPN.get(i).getPnum()+"</td><td>"+aLPN.get(i).getPnom()+"</td></tr>\n");
           }
            
            out.println("</table><br>\n");
            
            out.println("Relations entre les produits du fichier express\n<br>"+
	        		"<table border=\"1\">\n"+
	        		"<tr>\n"+
	        		"<th>Element</th>\n"+
	        		"<th>Lié à</th>\n"+
	        		"</tr>\n");
           List<PmajeurPmineur> aLPM = fao_express.getCompositionList();
           for (int i=0; i<aLPM.size();i++){
        	   out.println("<tr><td>"+aLPM.get(i).getPmajeur()+"</td><td>"+aLPM.get(i).getPmineur()+"</td></tr>\n");
           }
            
            out.println("</table>\n");            
            
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
