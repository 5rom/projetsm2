package sic.services.utils.fao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;


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

    
    public HashMap<String, String> getProduits() {
		return produits;
	}

	public HashMap<String, String[]> getRelations_line() {
		return relations_line;
	}

	public HashMap<String, String> getLine_name() {
		return line_name;
	}

	private ExpressDBUtils(String filepath,HashMap<String,String> produits, HashMap<String,String[]> relations_line, HashMap<String,String> line_name){
    	this.filepath = filepath;
    	this.produits = produits;
    	this.relations_line = relations_line;
    	this.line_name = line_name;
    }
    
    public static ExpressDBUtils getParser(String filepath,HashMap<String,String> produits, HashMap<String,String[]> relations_line, HashMap<String,String> line_name){
//    	if(instance == null){
    		instance = new ExpressDBUtils(filepath,produits,relations_line,line_name);

    	return instance;
    }
    
    public void parseFichier(){
    	try{
			  // A remettre avec un bouton "parcourir..."
			  //FileInputStream fstream = new FileInputStream("bd.exp");
			  //DataInputStream in = new DataInputStream(fstream);
			  InputStream in = getClass().getResourceAsStream(filepath);
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
