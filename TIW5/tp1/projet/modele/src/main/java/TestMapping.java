
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
    public void testPersistence() {
        try {
            em.getTransaction().begin();
            logger.info("Début de la transaction...");
            Album a1 = new Album("The New Eye Scream - The Very Best Of");
            Album a2 = new Album("The Parlor Mob - Dogs");

            a1.addPiste(new Piste("La chanson un", 3));
            a1.addPiste(new Piste("La chanson deux", 3));
            a2.addPiste(new Piste("How It's Going To Be", 3));
            a2.addPiste(new Piste("Into The Sun", 4));
            a2.addPiste(new Piste("Fall Back", 3));
            a2.addPiste(new Piste("Pratice In Patience", 4));
            a2.addPiste(new Piste("American Dream", 3));
            a2.addPiste(new Piste("Hard Enough", 7));
            logger.info("Albums créés.");
            em.persist(a1);
            em.persist(a2);
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
            
            
            
            /*//Voir pourquoi ne marche pas "No Such Field - exception"
            List<Album> lesAlbums = em.createQuery("select a from Album a").getResultList();
            for (Album ex : lesAlbums) {
            	System.out.println("Nom: " + ex.getTitre());
            }*/
            
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
            
            try
            {
            	logger.info("Liste de pistes");
                stmt = conn.createStatement();
                ResultSet results = stmt.executeQuery("select * from " + "Album_Pistes");
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
                    int id = results.getInt(1);
                    String duree = results.getString(2);
                    String titre = results.getString(3);
                    logger.info(id + "\t\t" + duree + "\t\t"+titre);
                }
                results.close();
                stmt.close();
            }
            catch (SQLException sqlExcept)
            {
                sqlExcept.printStackTrace();
            }     
            
            
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
            fail("Exception dans testPersistence()");
            em.close();
        }
    }

}
