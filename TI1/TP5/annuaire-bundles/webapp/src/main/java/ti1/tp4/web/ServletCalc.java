package ti1.tp4.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import calc.AddBeanRemote;

/**
 * Servlet implementation class ServletCalc
 */
public class ServletCalc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//static AddBeanRemote add;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCalc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		float a = 3, b = 3;
		//Utilisation du bean
		//add = lookupAddBean();
		//System.out.println(a + " + " + b + " = " + add.add(a, b));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	/*public static AddBeanRemote lookupAddBean() { 

		try {
			Context c = new InitialContext();
			//Récupération de la référence sur une instance du bean
			//Remarque :
			//contrairement à ce qui est indiqué dans le code généré automatiquement,
			//il faut faire une référence vers le nom du bean/remote

			return (AddBeanRemote) c.lookup("AddBean/remote");
		} catch (NamingException ne) {
			Logger.getLogger(ne.getClass().getName()).log(Level.SEVERE, "exception caught", ne);
			throw new RuntimeException(ne);
		}
	}	*/
	
}
