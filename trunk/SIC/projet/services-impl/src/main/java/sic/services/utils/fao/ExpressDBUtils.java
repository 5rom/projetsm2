package sic.services.utils.fao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Classe ExpressDBUtils
 * Classe utilitaire de manipulation d'un fichier EXPRESS
 * Créée par Sébastien Faure et David Crescence
 * @author David CRESCENCE <crescence.david@gmail.com> et Sébastien FAURE <sebastien.faure3@gmail.com>
 * UCBL M2TI 2011-2012 
 */
public class ExpressDBUtils {
	/**
     * instance du singleton
     */
    static protected ExpressDBUtils instance = null;
    
    private String filepath;
    /**
     * Contient les pnum et les pname
     */
    private HashMap<String,String> produits;
	/**
	 * Contient les relations indexees par les indices express (#1,#2,...)
	 */
	private HashMap<String,String[]> relations_line;
	/**
	 * Contient la correspondance index/pnum
	 */
	private HashMap<String,String> line_name;

    /**
     * Getter de la liste de produits
     * @return liste de produits du fichier
     */
    public HashMap<String, String> getProduits() {
		return produits;
	}

    /**
     * Getter des relations indexees
     * @return les relations
     */
	public HashMap<String, String[]> getRelations_line() {
		return relations_line;
	}

	/**
	 * Getter sur les correspondances index/pnum
	 * @return les correspondances index/pnum
	 */
	public HashMap<String, String> getLine_name() {
		return line_name;
	}

	/**
	 * Constructeur
	 * @param filepath chemin du fichier EXPRESS
	 * @param produits liste de produits
	 * @param relations_line liste de relations
	 * @param line_name liste de correspondances
	 */
	private ExpressDBUtils(String filepath,HashMap<String,String> produits, HashMap<String,String[]> relations_line, HashMap<String,String> line_name){
    	this.filepath = filepath;
    	this.produits = produits;
    	this.relations_line = relations_line;
    	this.line_name = line_name;
    }
    
	/**
	 * Recupération de l'instance unique du parser
	 * @param filepath chemin du fichier EXPRESS
	 * @param produits liste de produits
	 * @param relations_line liste de relations
	 * @param line_name liste de correspondances
	 * @return
	 */
    public static ExpressDBUtils getParser(String filepath,HashMap<String,String> produits, HashMap<String,String[]> relations_line, HashMap<String,String> line_name){
//    	if(instance == null){
    		instance = new ExpressDBUtils(filepath,produits,relations_line,line_name);

    	return instance;
    }
    
    /**
     * Methode de parsing du fichier
     * Remplit les tables de produits, relations et correspondances
     */
    public void parseFichier(){
    	try{
    		  FileInputStream in = new FileInputStream(filepath);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  while ((strLine = br.readLine()) != null)   {
				  if(strLine.startsWith("#")){
					  String[] firstsplit = strLine.split("=", 2);
					  String key = firstsplit[0].trim();
					  String[] secondsplit = firstsplit[1].split("\\(", 3);
					  String tablename = secondsplit[0].trim();
					  secondsplit[1].replace("$", "");
					  String[] thirdsplit = secondsplit[1].split(",");
					  String pnum = thirdsplit[0].replace("'","");
					  String pname = thirdsplit[1].replace("'","").replace(" ", "_");
					  String[] relation;
					  if(secondsplit.length > 2){
						  relation = secondsplit[2].replace(")", "").replace(";","").split(",");
						  relations_line.put(key,relation);
					  }
					  line_name.put(key, pnum);
					  produits.put(pnum, pname);
				  }
			  }
			  in.close();
		}catch (Exception e){
			  System.err.println("Error parsing: " + e.getMessage());
		}
    }

}
