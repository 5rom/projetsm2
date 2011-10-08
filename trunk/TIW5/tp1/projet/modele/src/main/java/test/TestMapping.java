package test;

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

import junit.framework.TestCase;

import org.slf4j.LoggerFactory;

import tiw5.modele.Album;
import tiw5.modele.Artiste;
import tiw5.modele.Piste;

/**
 * Test unitaire de configuration du mapping relationnel: testMapping.java
 * Mise en persistence dans la BD derby "bdtp1" d'instances d'albums (contenant pistes et artistes).
 * @author David CRESCENCE et Sebastien FAURE (M2TI)
 * UCBL1 2011-2012
 */
public class TestMapping extends TestCase {

    
    // Connexion JDBC pour s'assurer que les tables de jointure (liees aux xxxToMany sont bien crees)
    private static Connection conn = null;
    private static Statement stmt = null;
    private static String dbURL = "jdbc:derby:bdtp1;create=true;";
	
	public TestMapping(String name) {
		super(name);
	}

	/**
	 * L'objet permettant de faire du logging
	 */
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(TestMapping.class);

    /**
     * Le gestionnaire d'entités
     */
    private EntityManager em;


    // Méthode d'initialisation du test
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        try {
            logger.info("Création du gestionnaire d'entités...");
            // Création du gestionnaire d'entités (va charger l'unité de persistence "pu" de persistence.xml)
            em = Persistence.createEntityManagerFactory("pu").createEntityManager();
            logger.info("Gestionnaire d'entités créé.");
            
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Exception à l'initialisation du test");
            em.close();
        }
 
    }

    // Méthode de conclusion du test
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        try {
            DriverManager.getConnection("jdbc:derby:bdtp1;shutdown=true").close();
        } catch (SQLNonTransientConnectionException ex) {
            if (ex.getErrorCode() != 45000) {
                throw ex;
            }
        }
        logger.info("Au revoir.");
    }

    /**
     * Méthode de test de la persistance des albums
     */
    public void test() {
        try {
        	// Ouverture d'une transaction
            em.getTransaction().begin();
            logger.info("Début de la transaction...");
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

            logger.info("Albums créés.");
            
            // On demande au gestionnaire d'entités de rendre nos objets persistants
            em.persist(a1);
            em.persist(a2);
            
            logger.info("Méthode persist appelée.");
            

        	// Validation de la transaction (et cloture) pour ne pas perdre nos ajouts à la base!
            em.getTransaction().commit();
            logger.info("commit ok. Fin de la transaction");
            
            /**
             * Contrôle du résultat de la demande de persistance : affichage des albums et pistes
             */
            try
            {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
                //Récupération de la connection via l'url
                conn = DriverManager.getConnection(dbURL); 
            }
            catch (Exception except)
            {
                except.printStackTrace();
            }
            
            
            logger.info("Début requete albums");
            // On requête le gestionnaire d'entités pour qu'il nous renvoie les objets albums de la base
            List<Album> lesAlbums = em.createQuery("select a from Album a").getResultList();
            for (Album ex : lesAlbums) {
            	// Affichage des informations des albums
            	logger.info("Album "+ex.getId()+" : " + ex.getTitre()+ " - "+ex.getNbPistes()+" pistes et "+ex.getNbArtistes()+" artistes.");
            }
            
            // On requête le gestionnaire d'entités pour qu'il nous renvoie les objets pistes de la base
            List<Piste> lesPistes = em.createQuery("select p from Piste p").getResultList();
            for (Piste ex : lesPistes) {
            	logger.info("Piste "+ ex.getId()+ ": " + ex.getTitre()+" - "+ex.getDuree()+"'");
            }
            
            // On requête le gestionnaire d'entités pour qu'il nous renvoie les objets artistes de la base
            List<Artiste> lesArtistes = em.createQuery("select ar from Artiste ar").getResultList();
            for (Artiste ex : lesArtistes) {
            	logger.info("Artiste "+ ex.getId()+ ": " + ex.getPrenom()+" - "+ex.getNom()+"'");
            }            
            
            logger.info("Fin requete albums");
            
            /*
            try
            {
            	logger.info("Liste d'albums");
                stmt = conn.createStatement();
                ResultSet results = stmt.executeQuery("select * from " + "Album");
                ResultSetMetaData rsmd = results.getMetaData();
                int numberCols = rsmd.getColumnCount();
                String cdisp1="";
                for (int i=1; i<=numberCols; i++)
                {
                    //print Column Names
                	cdisp1+=rsmd.getColumnLabel(i)+"\t\t";
                }
                logger.info(cdisp1);  

                logger.info("\n-------------------------------------------------");

                while(results.next())
                {
                    int id = results.getInt(1);
                    String titre = results.getString(2);
                    logger.info(id + "\t\t" + titre + "\t\t");
                }
                results.close();
                stmt.close();
            }
            catch (SQLException sqlExcept)
            {
                sqlExcept.printStackTrace();
            }
            */
            
            /**
             * On vérifie le contenu des tables de jointures entre les objets
             */
            try
            {
            	logger.info("Table de correspondance piste/artiste");
                stmt = conn.createStatement();
                ResultSet results = stmt.executeQuery("select * from " + "Piste_Artiste");
                ResultSetMetaData rsmd = results.getMetaData();
                int numberCols = rsmd.getColumnCount();
                String cdisp2="";
                for (int i=1; i<=numberCols; i++)
                {
                	cdisp2+=rsmd.getColumnLabel(i)+"\t\t";  
                }
                logger.info(cdisp2);
                logger.info("\n-------------------------------------------------");

                while(results.next())
                {
                    String idAlbum = results.getString(1);
                    String idPiste = results.getString(2);
                    logger.info(idAlbum + "\t\t"+idPiste);
                }
                results.close();
                stmt.close();
            }
            catch (SQLException sqlExcept)
            {
                sqlExcept.printStackTrace();
            }
            try
            {
            	logger.info("Table de correspondance album/artiste");
                stmt = conn.createStatement();
                ResultSet results = stmt.executeQuery("select * from " + "Album_Artiste");
                ResultSetMetaData rsmd = results.getMetaData();
                int numberCols = rsmd.getColumnCount();
                String cdisp2="";
                for (int i=1; i<=numberCols; i++)
                {
                	cdisp2+=rsmd.getColumnLabel(i)+"\t\t";  
                }
                logger.info(cdisp2);
                logger.info("\n-------------------------------------------------");

                while(results.next())
                {
                    String idAlbum = results.getString(1);
                    String idPiste = results.getString(2);
                    logger.info(idAlbum + "\t\t"+idPiste);
                }
                results.close();
                stmt.close();
            }
            catch (SQLException sqlExcept)
            {
                sqlExcept.printStackTrace();
            }            
            
         
            try
            {
            	logger.info("Table de correspondance album/piste");
                stmt = conn.createStatement();
                ResultSet results = stmt.executeQuery("select * from " + "Album_Piste");
                ResultSetMetaData rsmd = results.getMetaData();
                int numberCols = rsmd.getColumnCount();
                String cdisp2="";
                for (int i=1; i<=numberCols; i++)
                {
                	cdisp2+=rsmd.getColumnLabel(i)+"\t\t";  
                }
                logger.info(cdisp2);
                logger.info("\n-------------------------------------------------");

                while(results.next())
                {
                    String idAlbum = results.getString(1);
                    String idPiste = results.getString(2);
                    logger.info(idAlbum + "\t\t"+idPiste);
                }
                results.close();
                stmt.close();
            }
            catch (SQLException sqlExcept)
            {
                sqlExcept.printStackTrace();
            }        
            
            // Fermeture du gestionnaire d'entites
            logger.info("Fermeture du gestionnaire d'entites");
            em.close();
            
            logger.info("Fin du test : SUCCES");
            
        } catch (Exception ex) {
        	logger.error("Erreur",ex);
            em.getTransaction().rollback();
            ex.printStackTrace();
            fail("Exception dans testPersistence()");
            em.close();
        }
    }

}
