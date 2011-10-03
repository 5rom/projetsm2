package tp1;


/**
 * Interface SiteContext
 * Permet la mise en place du pattern Context pour la communication avec le DAO
 * @author D. CRESCENCE et S. FAURE
 *
 */

public interface SiteContext {
	@Deprecated
	public SiteDAO getSiteDAO() throws DaoCallerException;
	@Deprecated
	public void setSiteDAO(SiteDAO dao);
	
	public Object getDAO(String name) throws DaoCallerException;
	
	public void setDAO(String name, Object dao);
}
