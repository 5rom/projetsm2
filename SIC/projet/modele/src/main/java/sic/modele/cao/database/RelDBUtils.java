package sic.modele.cao.database;

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
	    private static String nomDeLaBase = "orapeda1";

	    /**
	     * Server
	     */
	    private static String nomDeServeur = "pedagowin710.univ-lyon1.fr";

	    /**
	     * Server
	     */
	    private static int port = 1521;
	    
	    private Connection conn;

	    OracleDataSource ods;	    
	    
	   public RelDBUtils (){

		try {
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
		       Connection connect = ods.getConnection("M1IF029","M1IF029");

		       // Travail sur la base
		       // Ici, on écrira du code pour, par exemple, interroger la base
		       // Test 
		       Statement stat = connect.createStatement();
		       ResultSet rs = stat.executeQuery("SELECT * FROM PRODUIT");
		       System.out.println("Select");
		       while(rs.next()) {
		         System.out.println(rs.getInt("Pnum")+" - "+ rs.getString("Pnom"));
		       }
		       System.out.println("Fin du select");
		       
		       // Ne pas oublier de fermer la session quand on a fini de manipuler la base
		       connect.close();	   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	    }

	   


	   
	    /**
	     * METHODES PROPRES
	     */

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

	    

		String database=null;
		String login=null;
		String pwd=null;
		Connection connexion = null;

		public RelDBUtils(String database, String login, String pwd ){
			this.database = database;
			this.login = login;
			this.pwd = pwd;
		}
		
		
		/**
		 * Ouvre une connexion � la base
		 * 
		 * @throws SQLException
		 */
	public void openConnection() throws SQLException {
	        
	        OracleDataSource ods = new OracleDataSource();
	        ods.setDriverType("thin");
	        //ods.setServerName("pedagowin710");  //@univ
	        ods.setServerName("pedagowin710");     //@home
	        ods.setPortNumber(1521);
	        ods.setDatabaseName(this.database);
	        this.connexion = ods.getConnection(this.login , this.pwd);
	} 
	       
	/**
	 * retourne la connexion courante
	 * 
	 * @return
	 */
	public Connection getConnection() {

	   return this.connexion;
	       
	}

	/**
	 * Ferme la connexion courante
	 * 
	 * @throws SQLException
	 */
	public void close() throws SQLException {

	    this.connexion.close();
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
		this.openConnection() ;
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
