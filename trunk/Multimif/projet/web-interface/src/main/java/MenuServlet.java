
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import panier.Panier;

import fr.univ_lyon1.master_info.m2ti.tiw5.services.Album;
import fr.univ_lyon1.master_info.m2ti.tiw5.services.CDCataloguePortType;
import fr.univ_lyon1.master_info.m2ti.tiw5.services.CDCatalogueService;

/**
 * Servlet implementation class MenuServlet
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
		Panier p = (Panier)request.getSession().getAttribute("panier");
		if (bouton!=null){
			if (bouton.equals("Afficher tous les albums du catalogue")){
	
				CDCatalogueService cDCS= new CDCatalogueService();
				CDCataloguePortType cDCPT = cDCS.getCDCataloguePort();
				List<Album> albums = cDCPT.getAlbumsFromCatalogue();
				out.println("<html><body>  <h2>Albums de la base<h2>\n<br>"+
		        		"<table border=\"1\">\n"+
		        		"<tr>\n"+
		        		"<th>Artiste</th>\n"+	        		
		        		"<th>Titre</th>\n"+
		        		"<th>Genre</th>\n"+	        		
		        		"<th>Prix</th>\n"+
		        		"</tr>\n");
	
	          for (int i=0; i<albums.size();i++){
	       	   String artistesListe ="";
	       	   for (int j=0;j<albums.get(i).getArtiste().size();j++){
	       		   //artistesListe+=albums.get(i).getArtiste().get(j).getUri()+"; ";
        		   artistesListe+="<a href=\"ArtisteServlet?artiste="+albums.get(i).getArtiste().get(j).getUri().replace(" ", "")+"\">"+albums.get(i).getArtiste().get(j).getUri()+"</a>; ";
	       	   }
		       	   out.println("<tr><td>"+artistesListe+"</td>\n");
		       	   out.println("<td><a href=\"AlbumServlet?id="+albums.get(i).getId()+"\">"+albums.get(i).getTitre()+"</a></td>\n");
		       	   out.println("<td>"+albums.get(i).getGenre()+"</td>\n");
		       	   out.println("<td>"+albums.get(i).getPrix()+"</td></tr>\n");
	          }           
	           
	            out.println("</table><br>\n");   
	            
		        //Bouton retour
				out.println("<FORM Method=\"POST\" Action=\"index.jsp\">"+
				"<INPUT type=\"submit\" value=\"Retour\">"+
				"</FORM>"+				
						
				"</BODY></HTML>");            
			} else 	if (bouton.equals("Afficher les albums de l'artiste") || bouton.equals("Autres albums de l'artiste")){
				
				String artiste =request.getParameter("artiste");
				CDCatalogueService cDCS= new CDCatalogueService();
				CDCataloguePortType cDCPT = cDCS.getCDCataloguePort();
	            out.println("<html><body>  <h2>Albums des artistes trouvés proches de '"+artiste+"' <h2>\n<br>"+
		        		"<table border=\"1\">\n"+
		        		"<tr>\n"+
		        		"<th>Artiste</th>\n"+	        		
		        		"<th>Titre</th>\n"+
		        		"<th>Genre</th>\n"+	        		
		        		"<th>Prix</th>\n"+
		        		"</tr>\n");
	
	           List<Album> albums = cDCPT.getAlbumsFromCatalogueForArtist(artiste);
	           for (int i=0; i<albums.size();i++){
	        	   String artistesListe ="";
	        	   for (int j=0;j<albums.get(i).getArtiste().size();j++){
	        		   //artistesListe+=albums.get(i).getArtiste().get(j).getUri()+"; ";
	        		   artistesListe+="<a href=\"ArtisteServlet?artiste="+albums.get(i).getArtiste().get(j).getUri().replace(" ", "")+"\">"+albums.get(i).getArtiste().get(j).getUri()+"</a>; ";
	        	   }
		       	   out.println("<tr><td>"+artistesListe+"</td>\n");
		       	   out.println("<td><a href=\"AlbumServlet?id="+albums.get(i).getId()+"\">"+albums.get(i).getTitre()+"</a></td>\n");
		       	   out.println("<td>"+albums.get(i).getGenre()+"</td>\n");
		       	   out.println("<td>"+albums.get(i).getPrix()+"</td></tr>\n");
	        	   
	           }			
	            
	            out.println("</table><br>\n");   
	            
		        //Bouton retour
				out.println("<FORM Method=\"POST\" Action=\"index.jsp\">"+
				"<INPUT type=\"submit\" value=\"Retour\">"+
				"</FORM>"+				
				"</BODY></HTML>");            
			} else 	if (bouton.equals("Vider le panier")){
				
				p.emptyPanier();
	
	            out.println("<HTML><BODY><h2>Panier vid&eacute; avec succes<h2>\n<br>"); 
		        //Bouton retour
				out.println("<FORM Method=\"POST\" Action=\"index.jsp\">"+
				"<INPUT type=\"submit\" value=\"Retour\">"+
				"</FORM>"+				
				"</BODY></HTML>");  
			} else 	if (bouton.equals("Afficher les albums de ce genre") || bouton.equals("Albums similaires")){
				
				String genre =request.getParameter("genre");
				CDCatalogueService cDCS= new CDCatalogueService();
				CDCataloguePortType cDCPT = cDCS.getCDCataloguePort();
	            out.println("<html><body>  <h2>Albums trouvés au genre proche de '"+genre+"' <h2>\n<br>"+
		        		"<table border=\"1\">\n"+
		        		"<tr>\n"+
		        		"<th>Artiste</th>\n"+	        		
		        		"<th>Titre</th>\n"+
		        		"<th>Genre</th>\n"+	        		
		        		"<th>Prix</th>\n"+
		        		"</tr>\n");
	
	           List<Album> albums = cDCPT.getAlbumsFromCatalogueForGenre(genre);
	           for (int i=0; i<albums.size();i++){
	        	   String artistesListe ="";
	        	   for (int j=0;j<albums.get(i).getArtiste().size();j++){
	        		   //artistesListe+=albums.get(i).getArtiste().get(j).getUri()+"; ";
	        		   artistesListe+="<a href=\"ArtisteServlet?artiste="+albums.get(i).getArtiste().get(j).getUri().replace(" ", "")+"\">"+albums.get(i).getArtiste().get(j).getUri()+"</a>; ";
	        	   }
		       	   out.println("<tr><td>"+artistesListe+"</td>\n");
		       	   out.println("<td><a href=\"AlbumServlet?id="+albums.get(i).getId()+"\">"+albums.get(i).getTitre()+"</a></td>\n");
		       	   out.println("<td>"+albums.get(i).getGenre()+"</td>\n");
		       	   out.println("<td>"+albums.get(i).getPrix()+"</td></tr>\n");
	        	   
	           }			
	            
	            out.println("</table><br>\n");   
	            
		        //Bouton retour
				out.println("<FORM Method=\"POST\" Action=\"index.jsp\">"+
				"<INPUT type=\"submit\" value=\"Retour\">"+
				"</FORM>"+				
				"</BODY></HTML>");            
			} else 	if (bouton.equals("Afficher les albums avec ce titre")){
				
				String titre =request.getParameter("titre");
				CDCatalogueService cDCS= new CDCatalogueService();
				CDCataloguePortType cDCPT = cDCS.getCDCataloguePort();
	            out.println("<html><body>  <h2>Albums trouvés au titre proche de '"+titre+"' <h2>\n<br>"+
		        		"<table border=\"1\">\n"+
		        		"<tr>\n"+
		        		"<th>Artiste</th>\n"+	        		
		        		"<th>Titre</th>\n"+
		        		"<th>Genre</th>\n"+	        		
		        		"<th>Prix</th>\n"+
		        		"</tr>\n");
	
	           List<Album> albums = cDCPT.getAlbumsFromCatalogueForTitle(titre);
	           for (int i=0; i<albums.size();i++){
	        	   String artistesListe ="";
	        	   for (int j=0;j<albums.get(i).getArtiste().size();j++){
	        		   //artistesListe+=albums.get(i).getArtiste().get(j).getUri()+"; ";
	        		   artistesListe+="<a href=\"ArtisteServlet?artiste="+albums.get(i).getArtiste().get(j).getUri().replace(" ", "")+"\">"+albums.get(i).getArtiste().get(j).getUri()+"</a>; ";
	        	   }
		       	   out.println("<tr><td>"+artistesListe+"</td>\n");
		       	   out.println("<td><a href=\"AlbumServlet?id="+albums.get(i).getId()+"\">"+albums.get(i).getTitre()+"</a></td>\n");
		       	   out.println("<td>"+albums.get(i).getGenre()+"</td>\n");
		       	   out.println("<td>"+albums.get(i).getPrix()+"</td></tr>\n");
	        	   
	           }			
	            
	            out.println("</table><br>\n");   
	            
		        //Bouton retour
				out.println("<FORM Method=\"POST\" Action=\"index.jsp\">"+
				"<INPUT type=\"submit\" value=\"Retour\">"+
				"</FORM>"+				
				"</BODY></HTML>");            
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
