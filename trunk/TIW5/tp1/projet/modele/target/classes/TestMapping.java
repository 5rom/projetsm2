
import java.sql.DriverManager;
import java.sql.SQLNonTransientConnectionException;
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

	public TestMapping(String name) {
		super(name);
	}

	//private static Logger logger = Logger.getLogger(PersistenceUnitTest.class.getName());
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(TestMapping.class);

    
    //private EntityManagerFactory emFactory;

    private EntityManager em;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        try {
            logger.info("Starting derby database for unit tests");
            /*Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            DriverManager.getConnection("jdbc:derby:tiw5tp5;create=true").close();*/
            logger.info("ICI");
            em = Persistence.createEntityManagerFactory("machin").createEntityManager();
            logger.info("LA");
            testPersistence();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Exception during database startup.");
            em.close();
        }
 
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        logger.info("Shuting down Hibernate JPA layer.");
        if (em != null) {
            em.close();
        }
        //if (emFactory != null) {
        //    emFactory.close();
        //}
        
        
        logger.info("Stopping in-memory database.");
        try {
            DriverManager.getConnection("jdbc:derby:tiw5tp7;shutdown=true").close();
        } catch (SQLNonTransientConnectionException ex) {
            if (ex.getErrorCode() != 45000) {
                throw ex;
            }
            // Shutdown success
        }

    }

    public void testPersistence() {
        try {
        	//DriverManager.getConnection("jdbc:derby:tiw5tp5;shutdown=true").close();
        	logger.info("TOTO L ASTICOT?");
            em.getTransaction().begin();
            logger.info("Transaction qui commence");
            Album a1 = new Album("The New Eye Scream - The Very Best Of");
            Album a2 = new Album("Wolfmother - Wolfmother");
            Album a3 = new Album("The Parlor Mob - Dogs");

            a1.addPiste(new Piste("Song1", 3));
            logger.info("album crée");
            em.persist(a1);
            em.persist(a2);
            em.persist(a3);
            logger.info("persist ok");
            


            em.getTransaction().commit();
            logger.info("commit ok");
            List<Album> lesAlbums = em.createQuery("select a from Album a").getResultList();
            for (Album ex : lesAlbums) {
            	System.out.println("Nom: " + ex.getTitre());
            }
            logger.info("fin");
            em.close();
        } catch (Exception ex) {
        	logger.info("Marche pas");
            //em.getTransaction().rollback();
            ex.printStackTrace();
            fail("Exception during testPersistence");
            em.close();
        }
    }

}
