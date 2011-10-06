import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;;
import java.io.ByteArrayInputStream;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import tiw5.modele.*;
/**
 * Servlet implementation class ServiceServlet :
 * Simule une service d'acces a la base de donnees d'albums
 * @author David CRESCENCE et Sebastien FAURE (M2TI)
 * UCBL1 2011-2012
 */
public class ServiceServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * Logger
	 */
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(ServiceServlet.class);	
	
    /**
	 * Printwriter pour les affichages
	 */
    private PrintWriter out;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			try{
			out = response.getWriter();

			String init=request.getParameter("bouton");
			
			// Service fourni : remplissage de la BD avec deux albums
			if (init.equals("Ajouter deux albums a la BD")) {
				logger.info("Ajoute deux albums à la BD");				
		        EntityManager em = Persistence.createEntityManagerFactory("pu").createEntityManager();
				
	            em.getTransaction().begin();
	            
	            Album a1 = new Album("The New Eye Scream - The Very Best Of");
	            Album a2 = new Album("The Parlor Mob - Dogs");

	            Piste p1 = new Piste("Blues Session", 3);            
	            Piste p2 = new Piste("A Cat That Rocks", 3);          
	            Piste p3 = new Piste("How It's Going To Be", 3);            
	            Piste p4 = new Piste("Into The Sun", 4);            
	            Piste p5 = new Piste("Fall Back", 3);            
	            Piste p6 = new Piste("Pratice In Patience", 4);         


	            Artiste ar1=new Artiste("Eye", "Scream");
	            a1.addArtiste(ar1);
	            p1.addArtiste(ar1);
	            p2.addArtiste(ar1);
	            Artiste ar2=new Artiste("Marc", "Melicia");
	            Artiste ar3=new Artiste("Sam", "Bey");
	            Artiste ar4=new Artiste("Dave", "Rosen");
	            Artiste ar5=new Artiste("Anthony", "Chick");
	            Artiste ar6=new Artiste("Paul", "Ritchie");
	            
	            
	            a2.addArtiste(ar2);
	            a2.addArtiste(ar3);
	            a2.addArtiste(ar4);
	            a2.addArtiste(ar5);
	            a2.addArtiste(ar6);
	            
	            p3.addArtiste(ar2);
	            p3.addArtiste(ar3);
	            
	            p4.addArtiste(ar4);
	            p5.addArtiste(ar5);
	            p6.addArtiste(ar6);

	            p6.addArtiste(ar5);
	            
	            a1.addPiste(p1);
	            a1.addPiste(p2);
	            
	            a2.addPiste(p3);
	            a2.addPiste(p4);
	            a2.addPiste(p5);
	            a2.addPiste(p6);


	            em.persist(a1);
	            em.persist(a2);
	            


	            em.getTransaction().commit();
				logger.info("close entity manager");
	            em.close();
        		out.println("<HTML><BODY><br><br><h2>Ajout de deux albums effectu&eacute; avec succ&egrave;s</h2><br><br>");
		        //Bouton retour
				out.println("<FORM Method=\"POST\" Action=\"index.jsp\">"+
				"<INPUT type=\"submit\" value=\"Retour\">"+
				"</FORM>"+				
						
				"</BODY></HTML>");
        		
			} else {
				logger.info("Accès BD infos album");
				/**
				 * Format choisi : xml (affiché ensuite en HTML grâce à transformation XSLT) ou XHTML
				 */
				String format=request.getParameter("format");
		
				/**
				 * Service de la servlet : fournir les infos de l'album demandé
				 * -Faire ce qui est fait dans testMapping pour recuperer la liste des albums de la base.
				 * -Demander aux gestionnaires d'entités l'album d'id noAlbum.
				 * -Si format = xml faire ce qui est fait dans testModSemiStruct pour marshalliser l'album choisi et afficher le xml transformé avec xslt en html
				 * -Si format = xhtml, construire simplement l'affichage en html (out.println("...")).
				 * -Mettre un bouton retour pour revenir à l'index.jsp. 
				 */
				
				// Le numéro d'album recherché
		        long numAlbum=Long.parseLong(request.getParameter("noAlbum"));
		        
		        // Création du gestionnaire d'entités (définition de "pu" dans persistence.xml)
		        EntityManager em = Persistence.createEntityManagerFactory("pu").createEntityManager();
		        
		        // Requêtage pour récupération de l'objet album qui nous intéresse
		        List<Album> albums = em.createQuery("select a from Album a where a.id="+numAlbum).getResultList();
		        if (albums.size()>0){
		        	// Si l'utilisateur a choisi la méthode xml on fait le marshalling
		        	if (format.equals("xml")){
		        		// Marshalling
		    	        // Recuperation du contexte JAXB pour l'album créé
		    	        JAXBContext context = JAXBContext.newInstance(albums.get(0).getClass());
		    	   
		    	        //Instanciation du marshaller
		    	        Marshaller marshaller = context.createMarshaller();
		    	        
		    	        // Formater la sortie
		    	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    	        StringWriter sw = new StringWriter();
		    	   
		    	        // marshallise l'objet vers XML
		    	        marshaller.marshal(albums.get(0), sw);
		    	        
		        		// Transformation xsl
		    	        out.println("<HTML><BODY>");
	    	        	out.println(albumXMLToHTML(sw.toString(), "albumTrans.xsl"));

		        	} else if (format.equals("xhtml")){
	    	        	//Si l'utilisateur a choisi la méthode xhtml on fait l'affichage html simple
		        		out.println("<HTML>\n<BODY>\n" +
		        				"<H1>Infos de l'album</H1>\n" +
		        				"<UL>\n" +			   
		        		"  <LI>Num&eacute;ro de l'album: "
		    				+ albums.get(0).getId() + "\n" +
						"  <LI>Nom de l'album: "
		    				+ albums.get(0).getTitre()+ "\n" +
						"  <LI>Nombre de pistes: "
		    				+ albums.get(0).getNbPistes()+ "\n" +
		    				
			        		// Affichage des artistes de l'album	
		    				"<LI>Artistes de l'album: \n"+
	                		"<table border=\"1\">\n"+
	                		"<tr>\n"+
	                		"<th>Pr&eacute;nom</th>\n"+
	                		"<th>Nom</th>\n"+
	                		"</tr>\n");
		        		
			            for (int j=0;j<albums.get(0).getNbArtistes();j++){
			            	out.println("<tr><td>"+albums.get(0).getArtiste(j).getPrenom()+"</td><td>"+albums.get(0).getArtiste(j).getNom()+"</td></tr>\n");
			            }        	        
			            out.println("</table>\n");
			    				
		        		// Affichage des pistes de l'album
			            out.println("  <LI>Pistes de l'album: \n"+
				        		"<table border=\"1\">\n"+
				        		"<tr>\n"+
				        		"<th>Piste</th>\n"+
				        		"<th>Dur&eacute;e</th>\n"+
				        		"</tr>\n");
			            for (int i=0;i<albums.get(0).getNbPistes();i++){
			            	out.println("<tr><td>"+albums.get(0).getPiste(i).getTitre()+"</td><td>"+albums.get(0).getPiste(i).getDuree()+" min</td></tr>\n");
			            }
			            out.println("</table>\n");
			            out.println("</UL>\n" );			
		        				

		        	}
		        } else {
		        	// Pas d'album avec cet identifiant!
					// On avertit et affiche le nombre d'albums présents toutefois
					logger.info("Pas d'album avec cet id");
		        	out.println("<HTML>\n<BODY>\n");
		        	out.println("Aucun album n'a l'identifiant \""+numAlbum+"\" !\n");
			        albums = em.createQuery("select a from Album a").getResultList();
			        if (albums.size()==0){
				        out.println("Je constate qu'il n'y a actuellement aucun album en base !\n");
			        	out.println("Indice: Nous vous conseillons d'utiliser le bouton d'ajout de deux albums de la page d'accueil. Pour remplir la base! ");
			        } else {
				        out.println("Je constate qu'il y a actuellement "+albums.size()+" albums en base !\n");
			        	out.println("Indice: Je constate que le premier album de la liste a pour id \""+albums.get(0).getId()+"\"");
			        }
			        
		        	 
		        }
				logger.info("close entity manager");
	            em.close();
		        
		        //Bouton retour
				out.println("<FORM Method=\"POST\" Action=\"index.jsp\">"+
				"<INPUT type=submit value=Retour>"+
				"</FORM>"+				
						
				"</BODY></HTML>");
			}
		} catch (Exception ex){
			logger.info("Erreur: "+ex.getMessage());
			if (ex.getMessage().equals("For input string: \"\"")){
				out.println("<HTML><BODY>");
				out.println("Veuillez fournir un identifiant d'album &agrave; rechercher s'il-vous-plait.\n\n");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * Renvoie l'album initialement en XML convertit en HTML par transformation XSLT
	 * @param xml
	 * @param xsl
	 * @param html
	 * @return
	 * @throws Exception
	 */
    public String albumXMLToHTML(String xmlContentString, String xslFilePath) throws Exception{
    	// Création de la source DOM
    	DocumentBuilderFactory fabriqueD = DocumentBuilderFactory.newInstance();
    	DocumentBuilder constructeur = fabriqueD.newDocumentBuilder();

    	// Construction d'un InputStream pour ne pas avoir a creer de fichier
    	byte[] barray = xmlContentString.getBytes();
    	InputStream is = new ByteArrayInputStream(barray);
    	
    	Document document = constructeur.parse(is);
    	Source source = new DOMSource(document);
    	
    	// Création du fichier de sortie
    	StringWriter ssw = new StringWriter();
    	Result resultat = new StreamResult(ssw);
    	
    	// Configuration du transformer
    	TransformerFactory fabriqueT = TransformerFactory.newInstance();
    	StreamSource stylesource = new StreamSource(xslFilePath);
    	Transformer transformer = fabriqueT.newTransformer(stylesource);
    	transformer.setOutputProperty(OutputKeys.METHOD, "html");
    	
    	// Transformation
    	transformer.transform(source, resultat);
    	return ssw.toString();
    }	
	
}

