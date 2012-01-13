package sic.services.impl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import fr.univ_lyon1.master_info.m2ti.tiw5.services_fao.PmajeurPmineur;
import fr.univ_lyon1.master_info.m2ti.tiw5.services_fao.PnumPnom;
import fr.univ_lyon1.master_info.m2ti.tiw5.services_fao.FAODataBase;

public class FAODataBaseService implements FAODataBase{
	
	
	@Override
	public Boolean deleteProduit(String pnum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteComposition(String pmajeur, String pmineur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PnumPnom> getProduitList() {
		// TODO Auto-generated method stub
		HashMap<String,String> produits = new HashMap<String,String>();
		HashMap<String,String[]> relations_line = new HashMap<String,String[]>();
		HashMap<String,String> line_name = new HashMap<String,String>();

		ArrayList<PnumPnom> list = new ArrayList<PnumPnom>();
		try{
			  // A remettre avec un bouton "parcourir..."
			  //FileInputStream fstream = new FileInputStream("bd.exp");
			  //DataInputStream in = new DataInputStream(fstream);
			  InputStream in = getClass().getResourceAsStream("bd.exp");
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
					  
					  // lecture du contenu des hashmap
					  /*Set<Entry<String,String[]>> rel_line = relations_line.entrySet();
					  Iterator<Entry<String,String[]>> it = rel_line.iterator();
					  while(it.hasNext()){
						  Entry<String,String[]> e = (Entry<String, String[]>) it.next();
						  System.err.println(e.getKey() + " " + e.getValue()[0]+e.getValue()[1]);
						  it.remove();
					  }
					  Set<Entry<String,String>> pnumpnom = this.produits.entrySet();
					  Iterator<Entry<String,String>> it1 = pnumpnom.iterator();
					  while(it1.hasNext()){
						  Entry<String,String> e = (Entry<String, String>) it1.next();
						  System.err.println(e.getKey() + " " + e.getValue());
						  it1.remove();
					  }
					  Set<Entry<String,String>> linepnum = this.line_name.entrySet();
					  Iterator<Entry<String,String>> it2 = linepnum.iterator();
					  while(it2.hasNext()){
						  Entry<String,String> e = (Entry<String, String>) it2.next();
						  System.err.println(e.getKey() + " " + e.getValue());
						  it2.remove();
					  }*/
					  Set<Entry<String,String>> pnumpnom = produits.entrySet();
					  Iterator<Entry<String,String>> it1 = pnumpnom.iterator();
					  while(it1.hasNext()){
						  Entry<String,String> e = (Entry<String, String>) it1.next();
						  PnumPnom pN = new PnumPnom();
						  pN.setPnum(e.getKey());
						  pN.setPnom(e.getValue());
						  list.add(pN);
						  it1.remove();
					  }
					  
				  }
			  }
			  in.close();
		}catch (Exception e){
			  System.err.println("Error: " + e.getMessage());
		}
		return list;
	}

	@Override
	public List<PmajeurPmineur> getCompositionList() {
		// TODO Auto-generated method stub
		HashMap<String,String> produits = new HashMap<String,String>();
		HashMap<String,String[]> relations_line = new HashMap<String,String[]>();
		HashMap<String,String> line_name = new HashMap<String,String>();
		
		ArrayList<PmajeurPmineur> list = new ArrayList<PmajeurPmineur>();
		try{
			  // A remettre avec un bouton "parcourir..."
			  //FileInputStream fstream = new FileInputStream("bd.exp");
			  //DataInputStream in = new DataInputStream(fstream);
			  InputStream in = getClass().getResourceAsStream("bd.exp");
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
			  System.err.println("Error: " + e.getMessage());
		}
		Set<Entry<String,String[]>> rel_line = relations_line.entrySet();
		Iterator<Entry<String,String[]>> it = rel_line.iterator();
		while(it.hasNext()){
			Entry<String,String[]> e = (Entry<String, String[]>) it.next();
			for(int i=0;i<e.getValue().length;i++){
				PmajeurPmineur pN = new PmajeurPmineur();
				pN.setPmajeur(line_name.get(e.getKey()));
				pN.setPmineur(line_name.get(e.getValue()[i]));
				list.add(pN);
			}
		}
			  
		return list;
	}

	@Override
	public Boolean addProduit(String pnum, String pnom, List<String> composants) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateProduit(String pnum, String pnom,
			List<String> composants) {
		// TODO Auto-generated method stub
		return null;
	}

}
