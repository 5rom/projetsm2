package tp1;

import java.util.HashMap;



/**
 * Classe SiteContext
 * ImpléŽmentation de la classe Site
 * @author D. CRESCENCE et S. FAURE
 *
 */
public class SiteContextImpl implements SiteContext {
	
	private HashMap<String,Object> listeobjets = new HashMap<String,Object>();
	
	public SiteContextImpl(){
		
	}
	
	public Object getDAO(String name) throws DaoCallerException{
		Throwable t = new Throwable(); 
		 StackTraceElement[] elements = t.getStackTrace(); 
		 String callerClassName = elements[1].getClassName();
		 //System.out.println("\n"+callerClassName);
		 
		 if (new String("tp1.Site").equals(callerClassName) || new String("tp1.serveur.GestionnaireEntite").equals(callerClassName))
			 return listeobjets.get(name);
		 else throw new DaoCallerException(callerClassName);
	}
	
	public void setDAO(String name, Object dao){
		listeobjets.put(name, dao);
	}
}
