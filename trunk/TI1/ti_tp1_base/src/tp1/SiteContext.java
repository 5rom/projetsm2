package tp1;


/**
 * Interface SiteContext
 * Permet la mise en place du pattern Context pour la communication avec le DAO
 * @author D. CRESCENCE et S. FAURE
 *
 */

public interface SiteContext {
	public SiteDAO getSiteDAO();
	public void setSiteDAO(SiteDAO dao);
	

}
