import java.io.File;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
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

import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import tiw5.modele.Album;
import tiw5.modele.Artiste;
import tiw5.modele.Piste;
import junit.framework.TestCase;

/**
 * Classe de test unitaire representant ce que la servlet fera. En attendant de trouver comment acceder a la servlet depuis la jsp...
 * @author Seb
 *
 */
public class fausseServlet extends TestCase {

    private static String dbURL = "jdbc:derby:bdtp1;create=true;";
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
	
	public fausseServlet(String name) {
		super(name);
	}

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(fausseServlet.class);

    private EntityManager em;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        try {
            logger.info("Création du gestionnaire d'entités...");
            em = Persistence.createEntityManagerFactory("pu").createEntityManager();
            logger.info("Gestionnaire d'entités créé.");
            //testPersistence();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Exception during database startup.");
            em.close();
        }
 
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        if (em != null) {
            em.close();
        }
        try {
            DriverManager.getConnection("jdbc:derby:bdtp1;shutdown=true").close();
        } catch (SQLNonTransientConnectionException ex) {
            if (ex.getErrorCode() != 45000) {
                throw ex;
            }
        }
        logger.info("Fin du test.");
    }

    /**
     * Méthode de test de la persistance des albums
     */
    public void testPatate() {
        try {
            List<Album> lesAlbums = em.createQuery("select a from Album a").getResultList();
            for (Album ex : lesAlbums) {
            	logger.info("Album "+ex.getId()+" : " + ex.getTitre());
            }
    		/**
    		 * Ici :
    		 * -Faire ce qui est fait dans testMapping pour recuperer la liste des albums de la base.
    		 * -Parcourir la liste et chercher l'album d'id noAlbum.
    		 * -Si format = xml faire ce qui est fait dans testModSemiStruct pour marshalliser l'album choisi et afficher le xml transformé avec xslt
    		 * -Si format = xhtml, construire simplement l'affichage en html (out.println("...")).
    		 * -Mettre un bouton retour pour revenir à l'index.jsp. 
    		 */
            long numAlbum=2;
            String format="xml";
            
            logger.info("Création du gestionnaire d'entités...");
            EntityManager em = Persistence.createEntityManagerFactory("pu").createEntityManager();
            logger.info("Gestionnaire d'entités créé.");
            
            logger.info("Début requete albums");
            List<Album> albums = em.createQuery("select a from Album a where a.id="+numAlbum).getResultList();

            if (albums.size()>0){
            	if (format.equals("xml")){
            		// Marshalling
        	        // Recuperation du contexte JAXB pour l'album créé
        	        JAXBContext context = JAXBContext.newInstance(albums.get(0).getClass());
        	   
        	        // To convert ex to XML, I need a JAXB Marshaller
        	        Marshaller marshaller = context.createMarshaller();
        	   
        	        // Make the output pretty
        	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        	        StringWriter sw = new StringWriter();
        	   
        	        // marshall the object to XML
        	        //marshaller.marshal(albums.get(0), sw);
        	        marshaller.marshal(albums.get(0), new File("outAlbum.xml"));
        	        //String monXML = sw.toString();    
        	        //System.out.println(monXML);
        	        
            		// Transformation xsl
        	        // TODO
        	        albumXMLToHTML("outAlbum.xml", "albumTrans.xsl", "albumTrans.html");
        	        
            	} else if (format.equals("xhtml")){
            		System.out.println("<HTML>\n<BODY>\n" +
            				"<H1>Infos de l'album</H1>\n" +
            				"<UL>\n" +			   
            		"  <LI>Numéro de l'album: "
        				+ albums.get(0).getId() + "\n" +
    				"  <LI>Nom de l'album: "
        				+ albums.get(0).getTitre()+ "\n" +
    				"  <LI>Nombre de pistes: "
        				+ albums.get(0).getNbPistes()+ "\n" + 
        				"  <LI>Pistes de l'album: \n"+
            		"<table border=\"1\">\n"+
            		"<tr>\n"+
            		"<th>Piste</th>\n"+
            		"<th>Duree</th>\n"+
            		"</tr>\n");
            		
        	            for (int i=0;i<albums.get(0).getNbPistes();i++){
        	            	System.out.println("<tr><td>"+albums.get(0).getPiste(i).getTitre()+"</td><td>"+albums.get(0).getPiste(i).getDuree()+" min</td></tr>\n");
        	            }
        	            System.out.println("</table>\n");

                		System.out.println("<LI>Artistes de l'album: \n"+
                        		"<table border=\"1\">\n"+
                        		"<tr>\n"+
                        		"<th>Prenom</th>\n"+
                        		"<th>Nom</th>\n"+
                        		"</tr>\n");                				
        	            for (int j=0;j<albums.get(0).getNbArtistes();j++){
        	            	System.out.println("<tr><td>"+albums.get(0).getArtiste(j).getPrenom()+"</td><td>"+albums.get(0).getArtiste(j).getNom()+"</td></tr>\n");
        	            }        	            
        	            System.out.println("</table>\n");
        				System.out.println("</UL>\n" );			
            				

            	}
            } else {
            	// Pas d'album avec cet identifiant!
            	System.out.println("Aucun album n'a l'identifiant \""+numAlbum+"\" !");
            	 
            }
            
            //Bouton retour
    		System.out.println("<FORM Method=\"POST\" Action=\"index.jsp\">"+
    		"<INPUT type=submit value=Retour>"+
    		"</FORM>"+				
    				
    		"</BODY></HTML>");
    		
    		
            
        } catch (Exception ex) {
        	logger.error("Erreur",ex);
            em.getTransaction().rollback();
            ex.printStackTrace();
            fail("Exception dans testPersistence()");
            em.close();
        }
    }

    public void albumXMLToHTML(String xml, String xsl, String html) throws Exception{
    	// Création de la source DOM
    	DocumentBuilderFactory fabriqueD = DocumentBuilderFactory.newInstance();
    	DocumentBuilder constructeur = fabriqueD.newDocumentBuilder();
    	File fileXml = new File(xml);
    	Document document = constructeur.parse(fileXml);
    	Source source = new DOMSource(document);
    	
    	// Création du fichier de sortie
    	File fileHtml = new File(html);
    	Result resultat = new StreamResult(fileHtml);
    	
    	// Configuration du transformer
    	TransformerFactory fabriqueT = TransformerFactory.newInstance();
    	StreamSource stylesource = new StreamSource(xsl);
    	Transformer transformer = fabriqueT.newTransformer(stylesource);
    	transformer.setOutputProperty(OutputKeys.METHOD, "html");
    	
    	// Transformation
    	transformer.transform(source, resultat);
    }

}
