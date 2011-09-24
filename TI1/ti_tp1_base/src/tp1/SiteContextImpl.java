package tp1;


/**
 * Classe SiteContext
 * Implémentation de la classe Site
 * @author D. CRESCENCE et S. FAURE
 *
 */
public class SiteContextImpl implements SiteContext {
	SiteDAO dao;
	
	public SiteContextImpl(){
	}
	
	public SiteDAO getSiteDAO(){
		return dao;
	}
	
	public void setSiteDAO(SiteDAO dao){
		this.dao = dao;
	}
}
