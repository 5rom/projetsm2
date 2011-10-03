package tp1;

import java.util.HashMap;



/**
 * Classe SiteContext
 * Implémentation de la classe Site
 * @author D. CRESCENCE et S. FAURE
 *
 */
public class SiteContextImpl implements SiteContext {
	@Deprecated
	private SiteDAO dao;
	
	private HashMap<String,Object> listeobjets = new HashMap<String,Object>();
	
	public SiteContextImpl(){
		
	}
	
	@Deprecated
	public SiteDAO getSiteDAO() throws DaoCallerException{
		Throwable t = new Throwable(); 
		 StackTraceElement[] elements = t.getStackTrace(); 
		 String callerClassName = elements[1].getClassName();
		 //System.out.println("\n"+callerClassName);
		 
		 if (new String("tp1.Site").equals(callerClassName))
			 return dao;
		 else throw new DaoCallerException(callerClassName);
	}
	
	@Deprecated
	public void setSiteDAO(SiteDAO dao){
		this.dao = dao;
	}
	
	public Object getDAO(String name) throws DaoCallerException{
		Throwable t = new Throwable(); 
		 StackTraceElement[] elements = t.getStackTrace(); 
		 String callerClassName = elements[1].getClassName();
		 //System.out.println("\n"+callerClassName);
		 
		 if (new String("tp1.Site").equals(callerClassName))
			 return listeobjets.get(name);
		 else throw new DaoCallerException(callerClassName);
	}
	
	public void setDAO(String name, Object dao){
		listeobjets.put(name, dao);
	}
}
