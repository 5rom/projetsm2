package sic.services.utils.cao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.pool.OracleDataSource;

/**
 * Class DBConnexion.java
 * @description SQLite database management
 * @author SÃ©bastien Faure  <sebastien.faure3@gmail.com>
  * @version 2011-07-18
 */
public class RelDBUtils {

	    /**
	     * ATTRIBUTS
	     */

	    /**
	     * instance du singleton
	     */
	    static protected RelDBUtils instance = null;
	    /**
	     * Ã©lÃ©ment de paramÃ¨tres statiques
	     */
	    //private String nomDeLaBase = "orapeda1";
	    private String nomDeLaBase = "xe";
	    
	    /**
	     * Server : sur un reseau prive apres avoir ouvert un tunnel ssh (port 1521) vers l'université
	     */
	    private String nomDeServeur = "localhost";

	    /**
	     * Server : sur le réseau de l'université
	     */
	    //private static String nomDeServeur = "pedagowin710.univ-lyon1.fr";
	    
	    /**
	     * Port
	     */
	    private int port = 1521;
	    
	    /**
	     * Login d'accès à la base
	     */
	    //private String login = "M1IF029";
	    private String login = "SIC";
	    
	    /**
	     * Mot de passe d'accès à la base
	     */	    
	    //private String pwd = "admin";
	    private String pwd = "admin";
	    
	    private Connection connect;

	    OracleDataSource ods;	    
	    
	   public RelDBUtils (){

		try {
				
				// Source de données Oracle
				ods = new OracleDataSource();
			
			  
		       // type de pilote oracle
		       ods.setDriverType("thin"); 

		       // nom de la machine sur laquelle se trouve la base
		       ods.setServerName(nomDeServeur); 

		       // numero du port pour se connecter à la base
		       ods.setPortNumber(port);

		       // nom de la base
		       ods.setDatabaseName(nomDeLaBase);

		       // Pour ouvrir une session (représentée par l'objet connect
		       connect = ods.getConnection(login,pwd);

			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	    }

	   
	    /**
	     * To get the unique instance of DB Connexion
	     * @return unique DB Connexion instance
	     */
	    public static RelDBUtils getConnexion() {
	        if (instance == null) {
	            instance = new RelDBUtils();
	        }
	        return instance;
	    }



		public RelDBUtils(String database, String login, String pwd ){
			this.nomDeLaBase = database;
			this.login = login;
			this.pwd = pwd;
		}
		
	       
	/**
	 * retourne la connexion courante
	 * 
	 * @return
	 */
	public Connection getConnection() {

	   return this.connect;
	       
	}

	/**
	 * Ferme la connexion courante
	 * 
	 * @throws SQLException
	 */
	public void close() throws SQLException {

	    this.connect.close();
	}


	/**
	 * retourne la liste de tuples r�pondant � la requ�te query
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<String> getTuples(String query ) throws SQLException {
		 ArrayList<String> resultat = new ArrayList<String>();
		 

	try{
//		this.openConnection() ;
		Statement st = this.getConnection().createStatement();
		ResultSet res = st.executeQuery(  query  );
		ResultSetMetaData resmd = res.getMetaData(); 

		int columnNumber = resmd.getColumnCount(); 

		String tmp = "";
		while (res.next()){
			tmp = "";
			for(int i=1; i<=columnNumber; i++) { 
		
				tmp += res.getString(i) + ", " ;
				
			}
			tmp = tmp.substring(0, tmp.length()-2);
			resultat.add(tmp);
		}

		
		}catch (SQLException e){ e.printStackTrace();}


		return resultat;
	}

	    

}
