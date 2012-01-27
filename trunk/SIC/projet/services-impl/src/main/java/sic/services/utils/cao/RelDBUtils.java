package sic.services.utils.cao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.pool.OracleDataSource;

/**
 * Classe RelDBUtils
 * Classe utilitaire de connexion à la base de données
 * Les identifiants de connexion à la base sont paramétrables et par défaut pour une base Oracle 11g xe
 * Créée par Sébastien Faure et David Crescence
 * @author David CRESCENCE <crescence.david@gmail.com> et Sébastien FAURE <sebastien.faure3@gmail.com>
 * UCBL M2TI 2011-2012 
 */
public class RelDBUtils {

	    /**
	     * ATTRIBUTS
	     */

		/**
		 * --- Paramètres : identifiants de la base ---
		 */
	    /**
	     * instance du singleton
	     */
	    static protected RelDBUtils instance = null;
	    /**
	     * Ã©lÃ©ment de paramÃ¨tres statiques
	     */
	    //private String nomDeLaBase = "orapeda1"; // Reseau universite
	    private String nomDeLaBase = "xe";
	    
	    /**
	     * Server : sur un reseau prive apres avoir ouvert un tunnel ssh (port 1521) vers l'université
	     */
	    private String nomDeServeur = "localhost";

	    /**
	     * Server : sur le réseau de l'université
	     */
	    //private static String nomDeServeur = "pedagowin710.univ-lyon1.fr"; // Reseau universite
	    
	    /**
	     * Port
	     */
	    private int port = 1521;
	    
	    /**
	     * Login d'accès à la base
	     */
	    //private String login = "M2TIW15"; // Reseau universite
	    private String login = "SIC";
	    
	    /**
	     * Mot de passe d'accès à la base
	     */	    
	    //private String pwd = "M2TIW15"; // Reseau universite
	    private String pwd = "admin";

		/**
		 * --- Fin paramètres : identifiants de la base ---
		 */
	    
	    /**
	     * La connection
	     */
	    private Connection connect;

	    /**
	     * La source de données Oracle
	     */
	    OracleDataSource ods;	    
	   
	   /**
	    * Constructeur
	    */
	   private RelDBUtils (){

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
	     * Retourne l'unique instance de connexion
	     * @return instance unique de connexion
	     */
	    public static RelDBUtils getConnexion() {
	        if (instance == null) {
	            instance = new RelDBUtils();
	        }
	        return instance;
	    }



	    /**
	     * Constructeur
	     * @param database la base de données
	     * @param login le mot de passe
	     * @param pwd le login
	     */
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
	 * retourne la liste de tuples repondant a la requete query
	 * @param query requete SQL
	 * @return les tuples
	 * @throws SQLException
	 */
	public ArrayList<String> getTuples(String query ) throws SQLException {
		 ArrayList<String> resultat = new ArrayList<String>();
		 
	try{
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
