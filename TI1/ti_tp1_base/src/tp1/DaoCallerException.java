package tp1;

import java.util.Calendar;

/**
 * Exception d'appel de méŽthode getDAO
 * @author D. CRESCENCE et S. FAURE
 *
 */

public class DaoCallerException extends Exception {
	
	/**
	 * Generated
	 */
	private static final long serialVersionUID = 1L;
	private String classname;

	public DaoCallerException(String s){
		this.classname = s;
	}
	
	public String toString(){
		return "Bad caller exception : " + classname + " called a DAO at " + Calendar.getInstance().getTime() + "\n";
	}
	
	
	
}
