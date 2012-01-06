package sic.modele.cao.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
	    private static String nomDeLaBase = "jdbc:sqlite:des.db";


	    private Connection conn;

	    OracleDataSource ods;	    
	    
	   public RelDBUtils (){

		try {
			ods = new OracleDataSource();
			
			  
		       // type de pilote oracle
		       ods.setDriverType("thin"); 

		       // nom de la machine sur laquelle se trouve la base
		       ods.setServerName("pedagowin710.univ-lyon1.fr"); 

		       // numero du port pour se connecter à la base
		       ods.setPortNumber(1521);

		       // nom de la base
		       ods.setDatabaseName("orapeda1");

		       // Pour ouvrir une session (représentée par l'objet connect
		       Connection connect = ods.getConnection("M1IF029","M1IF029");

		       // Travail sur la base
		       // Ici, on écrira du code pour, par exemple, interroger la base

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

	    /**
	     * Reset Database
	     */
	    public void resetBD() {
	        try {
	            Statement stat = conn.createStatement();
	            stat.executeUpdate("drop table if exists entries;");
	            //stat.executeUpdate("create table entries (question, reponse, instant);");
	            stat.executeUpdate("drop table if exists session;");
	            stat.executeUpdate("create table entries (question, reponse, instant,session,screenshot,idreponse,regles);");
	            stat.executeUpdate("create table session (idsession,datedebut,datefin,active,nom,dateexport);"); //ajouter le nom
	        } catch (SQLException ex) {
	            Logger.getLogger(DBConnexion.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }

	    /**
	     * Get Database answers
	     * @return ArrayList of answers
	     */
	    public ArrayList<Reponse> getEntriesNew() {
	        ArrayList<Reponse> lesReponses = new ArrayList<Reponse>();

	        try {
	            Statement stat = null;
	            try {
	                stat = conn.createStatement();
	            } catch (SQLException ex) {
	                Logger.getLogger(DBConnexion.class.getName()).log(Level.SEVERE, null, ex);
	            }
	            String s = "";
	            ResultSet rs = stat.executeQuery("select * from entries;");

	            while (rs.next()) {
	                try {
	                    lesReponses.add(new Reponse(Long.parseLong(rs.getString("idreponse")), rs.getString("question"), rs.getString("reponse"), DateOutils.stringToDate(rs.getString("instant")), getSessionById(Long.parseLong(rs.getString("session"))),rs.getString("screenshot")));
	                } catch (ParseException ex) {
	                    Logger.getLogger(DBConnexion.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	            rs.close();
	            return lesReponses;
	        } catch (SQLException ex) {
	            Logger.getLogger(DBConnexion.class.getName()).log(Level.SEVERE, null, ex);
	            return null;
	        }
	    }

	    /**
	     * Get database answers : old version
	     * @deprecated
	     * @return
	     */
	    public ArrayList<Reponse> getEntries() {
	        ArrayList<Reponse> lesReponses = new ArrayList<Reponse>();

	        try {
	            Statement stat = null;
	            try {
	                stat = conn.createStatement();
	            } catch (SQLException ex) {
	                Logger.getLogger(DBConnexion.class.getName()).log(Level.SEVERE, null, ex);
	            }
	            String s = "";
	            ResultSet rs = stat.executeQuery("select * from entries;");

	            while (rs.next()) {
	                try {
	                    lesReponses.add(new Reponse(rs.getString("question"), rs.getString("reponse"), DateOutils.stringToDate(rs.getString("instant"))));
	                } catch (ParseException ex) {
	                    Logger.getLogger(DBConnexion.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	            rs.close();
	            return lesReponses;
	        } catch (SQLException ex) {
	            Logger.getLogger(DBConnexion.class.getName()).log(Level.SEVERE, null, ex);
	            return null;
	        }
	    }

	    /**
	     * Get all the answers of a session
	     * @param se the session
	     * @return the answers of se
	     */
	    public ArrayList<Reponse> getEntriesBySession(Session se) {
	        ArrayList<Reponse> lesReponses = new ArrayList<Reponse>();

	        try {
	            Statement stat = null;
	            try {
	                stat = conn.createStatement();
	            } catch (SQLException ex) {
	                Logger.getLogger(DBConnexion.class.getName()).log(Level.SEVERE, null, ex);
	            }
	            String s = "";
	            ResultSet rs = stat.executeQuery("select * from entries where session=\""+se.getId()+"\";");

	            while (rs.next()) {
	                try {
	                    Reponse r1= new Reponse(Long.parseLong(rs.getString("idreponse")), rs.getString("question"), rs.getString("reponse"), DateOutils.stringToDate(rs.getString("instant")), rs.getString("screenshot"));
	                    ArrayList<Regle> lesR=new ArrayList<Regle>();
	                    String tokens[]=rs.getString("regles").split(", ");
	                    for (String token : tokens)
	                    {
	                        String all=token;
	                        String type=all.split(":")[0];
	                        String event=all.split(":")[1];
	                        lesR.add(new Regle(event, type));
	                    }
	                    r1.setReglesQuestion(lesR);
	                    lesReponses.add(r1);
	                } catch (ParseException ex) {
	                    Logger.getLogger(DBConnexion.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	            rs.close();
	            return lesReponses;
	        } catch (SQLException ex) {
	            Logger.getLogger(DBConnexion.class.getName()).log(Level.SEVERE, null, ex);
	            return null;
	        }
	    }

	    /**
	     * Get the Database sessions
	     * @return Arraylist of sessions
	     */
	        public ArrayList<Session> getSessions() {
	        ArrayList<Session> lesSessions = new ArrayList<Session>();
	        try {
	            Statement stat = null;
	            try {
	                stat = conn.createStatement();
	            } catch (SQLException ex) {
	                Logger.getLogger(DBConnexion.class.getName()).log(Level.SEVERE, null, ex);
	            }
	            ResultSet rs = stat.executeQuery("select * from session;");

	            while (rs.next()) {
	                try {
	                    if(rs.getString("datefin").equals("null")){
	                                            if(rs.getString("dateexport").equals("null")){
	                                                Session s = new Session(Long.parseLong(rs.getString("idsession")), DateOutils.stringToDate(rs.getString("datedebut")), null, Boolean.parseBoolean(rs.getString("active")), rs.getString("nom"),null);
	                                                s.setTimeToLive(Integer.parseInt(rs.getString("timetolive")));
	                                                s.setQuestionsPerHour(Integer.parseInt(rs.getString("questionsperhour")));
	                                                lesSessions.add(s);
	                                            } else {
	                                                Session s = new Session(Long.parseLong(rs.getString("idsession")), DateOutils.stringToDate(rs.getString("datedebut")), null, Boolean.parseBoolean(rs.getString("active")), rs.getString("nom"),DateOutils.stringToDate(rs.getString("dateexport")));
	                                                s.setTimeToLive(Integer.parseInt(rs.getString("timetolive")));
	                                                s.setQuestionsPerHour(Integer.parseInt(rs.getString("questionsperhour")));
	                                                lesSessions.add(s);
	                                            }
	                    } else {
	                                            if(rs.getString("dateexport").equals("null")){
	                                                Session s = new Session(Long.parseLong(rs.getString("idsession")), DateOutils.stringToDate(rs.getString("datedebut")), DateOutils.stringToDate(rs.getString("datefin")), Boolean.parseBoolean(rs.getString("active")), rs.getString("nom"),null);
	                                                s.setTimeToLive(Integer.parseInt(rs.getString("timetolive")));
	                                                s.setQuestionsPerHour(Integer.parseInt(rs.getString("questionsperhour")));
	                                                lesSessions.add(s);
	                                            } else {
	                                                Session s = new Session(Long.parseLong(rs.getString("idsession")), DateOutils.stringToDate(rs.getString("datedebut")), DateOutils.stringToDate(rs.getString("datefin")), Boolean.parseBoolean(rs.getString("active")), rs.getString("nom"),DateOutils.stringToDate(rs.getString("dateexport")));
	                                                s.setTimeToLive(Integer.parseInt(rs.getString("timetolive")));
	                                                s.setQuestionsPerHour(Integer.parseInt(rs.getString("questionsperhour")));
	                                                lesSessions.add(s);
	                                            }
	                    }
	                    } catch (ParseException ex) {
	                    Logger.getLogger(DBConnexion.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	            rs.close();
	            return lesSessions;

	        } catch (SQLException ex) {
	            Logger.getLogger(DBConnexion.class.getName()).log(Level.SEVERE, null, ex);
	            return lesSessions;
	        }
	    }



}
