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
	
	public SiteDAO getSiteDAO() throws DaoCallerException{
		Throwable t = new Throwable(); 
		 StackTraceElement[] elements = t.getStackTrace(); 
		 String callerClassName = elements[1].getClassName();
		 //System.out.println("\n"+callerClassName);
		 
		 if (new String("tp1.Site").equals(callerClassName))
			 return dao;
		 //else return null;
		 else throw new DaoCallerException(callerClassName);
	}
	
	public void setSiteDAO(SiteDAO dao){
		this.dao = dao;
	}
}
