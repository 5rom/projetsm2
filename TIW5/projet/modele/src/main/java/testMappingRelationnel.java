import java.sql.DriverManager;
import java.sql.SQLNonTransientConnectionException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;


public class testMappingRelationnel extends TestCase {

	public testMappingRelationnel(String name) {
		super(name);
	}

	private static Logger logger = Logger.getLogger(PersistenceUnitTest.class.getName());

    private EntityManagerFactory emFactory;

    private EntityManager em;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        try {
            logger.info("Starting in-memory database for unit tests");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            DriverManager.getConnection("jdbc:derby:memory:unit-testing-jpa;create=true").close();
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Exception during database startup.");
        }
        try {
            logger.info("Building JPA EntityManager for unit tests");
            emFactory = Persistence.createEntityManagerFactory("testPU");
            em = emFactory.createEntityManager();
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Exception during JPA EntityManager instanciation.");
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        logger.info("Shuting down Hibernate JPA layer.");
        if (em != null) {
            em.close();
        }
        if (emFactory != null) {
            emFactory.close();
        }
        logger.info("Stopping in-memory database.");
        try {
            DriverManager.getConnection("jdbc:derby:memory:unit-testing-jpa;shutdown=true").close();
        } catch (SQLNonTransientConnectionException ex) {
            if (ex.getErrorCode() != 45000) {
                throw ex;
            }
            // Shutdown success
        }
        VFMemoryStorageFactory.purgeDatabase(new File("unit-testing-jpa").getCanonicalPath());
    }

    public void testPersistence() {
        try {

            em.getTransaction().begin();

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
            assertFalse(em.contains(u));

            em.getTransaction().commit();

        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
            fail("Exception during testPersistence");
        }
    }

}
