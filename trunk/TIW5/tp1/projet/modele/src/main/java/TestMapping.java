
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.slf4j.LoggerFactory;

import tiw5.modele.Album;
import tiw5.modele.Artiste;
import tiw5.modele.Piste;


import junit.framework.TestCase;


public class TestMapping extends TestCase {

    private static String dbURL = "jdbc:derby:bdtp1;create=true;";
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
	
	public TestMapping(String name) {
		super(name);
	}

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(TestMapping.class);

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
            em.getTransaction().begin();
            logger.info("Début de la transaction...");
            /*Album a1 = new Album("The New Eye Scream - The Very Best Of");*/
            Album a2 = new Album("The Parlor Mob - Dogs");

            /*Piste p1 = new Piste("La chanson un", 3);            
            Piste p2 = new Piste("La chanson deux", 3);*/            
            Piste p3 = new Piste("How It's Going To Be", 3);            
            Piste p4 = new Piste("Into The Sun", 4);            
            Piste p5 = new Piste("Fall Back", 3);            
            Piste p6 = new Piste("Pratice In Patience", 4);         


            /*Artiste ar1=new Artiste("Eye", "Scream");
            a1.addArtiste(ar1);
            p1.addArtiste(ar1);
            p2.addArtiste(ar1); */
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
            //ATTENTION : Pour l'instant un artiste ne peut pas être sur deux piste differentes (violation de cle dans Piste_Artiste).
            //p6.addArtiste(ar5);
            
            
            /*
            a1.addPiste(p1);
            a1.addPiste(p2);
            */
            
            a2.addPiste(p3);
            a2.addPiste(p4);
            a2.addPiste(p5);
            a2.addPiste(p6);

            logger.info("Albums créés.");
            em.persist(a2);

            /*            
            
            
            
            //em.persist(ar1);
            
            em.persist(a1);
            //em.persist(p1);
            em.getTransaction().commit();
            //em.persist(p2);
            //em.persist(a1);
            
            
            em.persist(p3);
            em.persist(p4);
            em.persist(p5);
            em.persist(p6);
            em.persist(p7);
            em.persist(p8);
*/
            

            
            logger.info("Méthode persist appelée.");
            


            em.getTransaction().commit();
            logger.info("commit ok. Fin de la transaction");
            
            /**
             * Contrôle du résultat de la demande de persistance : affichage des albums et pistes
             */
            try
            {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
                //Get a connection
                conn = DriverManager.getConnection(dbURL); 
            }
            catch (Exception except)
            {
                except.printStackTrace();
            }
            
            
            logger.info("Début requete albums");
            List<Album> lesAlbums = em.createQuery("select a from Album a").getResultList();
            for (Album ex : lesAlbums) {
            	logger.info("Album "+ex.getId()+" : " + ex.getTitre());
            }
            
            List<Piste> lesPistes = em.createQuery("select p from Piste p").getResultList();
            for (Piste ex : lesPistes) {
            	logger.info("Piste "+ ex.getId()+ ": " + ex.getTitre()+" - "+ex.getDuree()+"'");
            }
            
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
                    //int id = results.getInt(1);
                    String idAlbum = results.getString(1);
                    String idPiste = results.getString(2);
                    logger.info(/*id + "\t\t" + */idAlbum + "\t\t"+idPiste);
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
                    //int id = results.getInt(1);
                    String idAlbum = results.getString(1);
                    String idPiste = results.getString(2);
                    logger.info(/*id + "\t\t" + */idAlbum + "\t\t"+idPiste);
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
                    //int id = results.getInt(1);
                    String idAlbum = results.getString(1);
                    String idPiste = results.getString(2);
                    logger.info(/*id + "\t\t" + */idAlbum + "\t\t"+idPiste);
                }
                results.close();
                stmt.close();
            }
            catch (SQLException sqlExcept)
            {
                sqlExcept.printStackTrace();
            }            
            
            
        } catch (Exception ex) {
        	logger.error("Erreur",ex);
            em.getTransaction().rollback();
            ex.printStackTrace();
            fail("Exception dans testPersistence()");
            em.close();
        }
    }

}
