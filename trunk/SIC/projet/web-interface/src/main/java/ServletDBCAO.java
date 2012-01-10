
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.univ_lyon1.master_info.m2ti.tiw5.services.CAODataBase;
import fr.univ_lyon1.master_info.m2ti.tiw5.services.CAODataBase_Service;
import fr.univ_lyon1.master_info.m2ti.tiw5.services.PmajeurPmineur;
import fr.univ_lyon1.master_info.m2ti.tiw5.services.PnumPnom;






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
			//for chaque element de la liste de cAODB.getProductList()
			//faire out.println(out.println("<tr><td>"+albums.get(0).getPiste(i).getTitre()+"</td><td>"+albums.get(0).getPiste(i).getDuree()+" min</td></tr>\n");
           List<PnumPnom> aLPN = cAODB.getProduitList();
           for (int i=0; i<aLPN.size();i++){
        	   out.println("<tr><td>"+aLPN.get(i).getPnum()+"</td><td>"+aLPN.get(i).getPnom()+"</td></tr>\n");
           }
            
            out.println("</table><br>\n");
            
            out.println("Composition des produits de la base\n<br>"+
	        		"<table border=\"1\">\n"+
	        		"<tr>\n"+
	        		"<th>Pmajeur</th>\n"+
	        		"<th>Pmineur</th>\n"+
	        		"</tr>\n");
			//for chaque element de la liste de cAODB.getProductList()
			//faire out.println(out.println("<tr><td>"+albums.get(0).getPiste(i).getTitre()+"</td><td>"+albums.get(0).getPiste(i).getDuree()+" min</td></tr>\n");
           List<PmajeurPmineur> aLPM = cAODB.getCompositionList();
           for (int i=0; i<aLPM.size();i++){
        	   out.println("<tr><td>"+aLPM.get(i).getPmajeur()+"</td><td>"+aLPM.get(i).getPmineur()+"</td></tr>\n");
           }
            
            out.println("</table>\n");            
            
	        //Bouton retour
			out.println("<FORM Method=\"POST\" Action=\"index.jsp\">"+
			"<INPUT type=\"submit\" value=\"Retour\">"+
			"</FORM>"+				
					
			"</BODY></HTML>");            
		} else if (bouton.equals("Ajouter le produit dans la base")){
			Long pnum = Long.parseLong(request.getParameter("pnum"));
			String pnom = request.getParameter("pnom");
			CAODataBase_Service cAODBS= new CAODataBase_Service();
			CAODataBase cAODB = cAODBS.getCAODataBaseSOAP();
			
    		if (cAODB.addProduit(pnum, pnom)){
    			out.println("<HTML><BODY><br><br><h2>Ajout du produit "+pnum+" - "+pnom+" effectu&eacute; avec succ&egrave;s</h2><br><br>");
    		} else {
    			out.println("<HTML><BODY><br><br><h2>Erreur lors de l'ajout du produit. Veuillez vérifier que ce produit n'existe pas déjà : "+pnum+" - "+pnom+"</h2><br><br>");
    		}
	        //Bouton retour
			out.println("<FORM Method=\"POST\" Action=\"index.jsp\">"+
			"<INPUT type=\"submit\" value=\"Retour\">"+
			"</FORM>"+				
					
			"</BODY></HTML>");
		} else if (bouton.equals("Supprimer le produit de la base")){
			Long pnum = Long.parseLong(request.getParameter("pnum"));
			CAODataBase_Service cAODBS= new CAODataBase_Service();
			CAODataBase cAODB = cAODBS.getCAODataBaseSOAP();
			
    		if (cAODB.deleteProduit(pnum)){
    			out.println("<HTML><BODY><br><br><h2>Suppression du produit "+pnum+" effectu&eacute;e avec succ&egrave;s</h2><br><br>");
    		} else {
    			out.println("<HTML><BODY><br><br><h2>Erreur lors de la suppression du produit "+pnum+". Veuillez vérifier que ce produit existe bien et qu'il n'est pas dans la table de composition!</h2><br><br>");
    		}
	        //Bouton retour
			out.println("<FORM Method=\"POST\" Action=\"index.jsp\">"+
			"<INPUT type=\"submit\" value=\"Retour\">"+
			"</FORM>"+				
					
			"</BODY></HTML>");			
			
		} else if (bouton.equals("Ajouter la composition dans la base")){
			Long pmajeur = Long.parseLong(request.getParameter("pmajeur"));
			Long pmineur = Long.parseLong(request.getParameter("pmineur"));
			CAODataBase_Service cAODBS= new CAODataBase_Service();
			CAODataBase cAODB = cAODBS.getCAODataBaseSOAP();
			
    		if (cAODB.addComposition(pmajeur, pmineur)){
    			out.println("<HTML><BODY><br><br><h2>Ajout de la composition "+pmajeur+" - "+pmineur+" effectu&eacute; avec succ&egrave;s</h2><br><br>");
    		} else {
    			out.println("<HTML><BODY><br><br><h2>Erreur lors de l'ajout de la composition "+pmajeur+" - "+pmineur+". Veuillez vérifier que cette composition n'existe pas déjà!</h2><br><br>");
    		}
	        //Bouton retour
			out.println("<FORM Method=\"POST\" Action=\"index.jsp\">"+
			"<INPUT type=\"submit\" value=\"Retour\">"+
			"</FORM>"+				
					
			"</BODY></HTML>");
		} else if (bouton.equals("Supprimer la composition de la base")){
			Long pmajeur = Long.parseLong(request.getParameter("pmajeur"));
			Long pmineur = Long.parseLong(request.getParameter("pmineur"));
			CAODataBase_Service cAODBS= new CAODataBase_Service();
			CAODataBase cAODB = cAODBS.getCAODataBaseSOAP();
			
    		if (cAODB.deleteComposition(pmajeur, pmineur)){
    			out.println("<HTML><BODY><br><br><h2>Suppression de la composition "+pmajeur+" - "+pmineur+" effectu&eacute; avec succ&egrave;s</h2><br><br>");
    		} else {
    			out.println("<HTML><BODY><br><br><h2>Erreur lors de la suppression de la composition "+pmajeur+" - "+pmineur+". Veuillez vérifier que cette composition existe!</h2><br><br>");
    		}
	        //Bouton retour
			out.println("<FORM Method=\"POST\" Action=\"index.jsp\">"+
			"<INPUT type=\"submit\" value=\"Retour\">"+
			"</FORM>"+				
					
			"</BODY></HTML>");			

		} else if (bouton.equals("Mettre à jour le nom du produit dans la base")){
			Long pnum = Long.parseLong(request.getParameter("pnum"));
			String pnom = request.getParameter("pnom");
			CAODataBase_Service cAODBS= new CAODataBase_Service();
			CAODataBase cAODB = cAODBS.getCAODataBaseSOAP();
			
    		if (cAODB.updateProduit(pnum, pnom)){
    			out.println("<HTML><BODY><br><br><h2>Mise à jour du produit "+pnum+" - "+pnom+" effectu&eacute;e avec succ&egrave;s</h2><br><br>");
    		} else {
    			out.println("<HTML><BODY><br><br><h2>Erreur lors de la mise à jour du produit. Veuillez vérifier que ce produit existe bien : "+pnum+"</h2><br><br>");
    		}
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
