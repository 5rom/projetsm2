package tiw5.modele;
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


public class TestMappingRelationnel extends TestCase {

	public TestMappingRelationnel(String name) {
		super(name);
	}

	//private static Logger logger = Logger.getLogger(PersistenceUnitTest.class.getName());
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(TestMappingRelationnel.class);

    
    //private EntityManagerFactory emFactory;

    private EntityManager em;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        try {
            logger.info("Starting derby database for unit tests");
            //Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //DriverManager.getConnection("jdbc:derby:bdtp1;create=true").close();
            logger.info("ICI");
            em = Persistence.createEntityManagerFactory("pu").createEntityManager();
            testPersistence();
            logger.info("LA");
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Exception during database startup.");
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
            DriverManager.getConnection("jdbc:derby:bdtp1;shutdown=true").close();
        } catch (SQLNonTransientConnectionException ex) {
            if (ex.getErrorCode() != 45000) {
                throw ex;
            }
            // Shutdown success
        }

    }

    public void testPersistence() {
        try {

            em.getTransaction().begin();
            Album a1 = new Album("The New Eye Scream - The Very Best Of");
            //Album a2 = new Album("Wolfmother - Wolfmother");
            //Album a3 = new Album("The Parlor Mob - Dogs");
            a1.addPiste(new Piste("Song1", 3));
            em.persist(a1);
            
            
            
            /*
            User u = new User();
            u.setEmail("eskatos@yopmail.com");
            u.setFirstName("eskatos");
            u.setLastName("YOP");
            u.setOrganisation("Tagada");

            em.persist(u);
            assertTrue(em.contains(u));

            Group g = new Group();
            g1.addUser(u);

            em.persist(g);
            assertTrue(em.contains(g));

            g.removeUser(u);
            em.remove(u);
            em.merge(g);
            assertFalse(em.contains(u));*/

            em.getTransaction().commit();
            
            List<Album> lesAlbums = em.createQuery("SELECT a FROM Album a").getResultList();
            for (Album p : lesAlbums) {
            	logger.info("Nom: " + p.getTitre());
            }

        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
            fail("Exception during testPersistence");
        }
    }

}
