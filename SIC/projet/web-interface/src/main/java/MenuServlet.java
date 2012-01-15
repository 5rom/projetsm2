
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.univ_lyon1.master_info.m2ti.tiw5.services.Album;

import fr.univ_lyon1.master_info.m2ti.tiw5.services.CDCataloguePortType;
import fr.univ_lyon1.master_info.m2ti.tiw5.services.CDCatalogueService;







/**
 * Servlet implementation class ServletDBCAO
 */
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * Printwriter pour les affichages
	 */
    private PrintWriter out;       
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuServlet() {
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
			/*CAODataBase_Service cAODBS= new CAODataBase_Service();
			CAODataBase cAODB = cAODBS.getCAODataBaseSOAP();
            out.println("<html><body>  Produits de la base\n<br>"+
	        		"<table border=\"1\">\n"+
	        		"<tr>\n"+
	        		"<th>Pnum</th>\n"+
	        		"<th>Pnom</th>\n"+
	        		"</tr>\n");
			//for chaque element de la liste de cAODB.getProductList()
			//faire out.println(out.println("<tr><td>"+albums.get(0).getPiste(i).getTitre()+"</td><td>"+albums.get(0).getPiste(i).getDuree()+" min</td></tr>\n");
           List<PnumPnom> aLPN = cAODB.getProduitList();
           for (int i=0; i<aLPN.size();i++){
        	   out.println("<tr><td>"+aLPN.get(i).getPnum()+"</td><td>"+aLPN.get(i).getPnom()+"</td></tr>\n");
           }
           */
			
			CDCatalogueService cDCS= new CDCatalogueService();
			CDCataloguePortType cDCPT = cDCS.getCDCataloguePort();
            out.println("<html><body>  Albums de la base\n<br>"+
	        		"<table border=\"1\">\n"+
	        		"<tr>\n"+
	        		"<th>Titre</th>\n"+
	        		//"<th>Pnom</th>\n"+
	        		"</tr>\n");
			//for chaque element de la liste de cAODB.getProductList()
			//faire out.println(out.println("<tr><td>"+albums.get(0).getPiste(i).getTitre()+"</td><td>"+albums.get(0).getPiste(i).getDuree()+" min</td></tr>\n");
           List<Album> albums = cDCPT.getAlbumsFromCatalogue();
           for (int i=0; i<albums.size();i++){
        	   out.println("<tr><td>"+albums.get(i).getTitre()+"</td></tr>\n");
           }			
            
            out.println("</table><br>\n");   
            
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
