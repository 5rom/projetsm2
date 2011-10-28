package client;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import calc.AddBeanRemote;

public class Main {

	static AddBeanRemote add;

	public static void main(String[] args) {
		float a = 3, b = 3;
		//Utilisation du bean
		add = Main.lookupAddBean();
		System.out.println(a + " + " + b + " = " + add.add(a, b)); 
	}

	public static AddBeanRemote lookupAddBean() { 
		Properties props = new Properties();
		props.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
		props.setProperty( "java.naming.provider.url", "127.0.0.1:1099" );
		props.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming" );

		try {
			Context c = new InitialContext(props);
			//Récupération de la référence sur une instance du bean
			//Remarque :
			//contrairement à ce qui est indiqué dans le code généré automatiquement,
			//il faut faire une référence vers le nom du bean/remote

			return (AddBeanRemote) c.lookup("AddBean/remote");
		} catch (NamingException ne) {
			Logger.getLogger(ne.getClass().getName()).log(Level.SEVERE, "exception caught", ne);
			throw new RuntimeException(ne);
		}
	}
}
