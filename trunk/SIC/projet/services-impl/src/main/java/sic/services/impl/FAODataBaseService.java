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

import sic.services.utils.fao.ExpressDBUtils;

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
		ExpressDBUtils parser = ExpressDBUtils.getParser("bd.exp", produits, relations_line, line_name);
		parser.parseFichier();
		
		Set<Entry<String,String>> pnumpnom = parser.getProduits().entrySet();
		Iterator<Entry<String,String>> it1 = pnumpnom.iterator();
		while(it1.hasNext()){
			Entry<String,String> e = (Entry<String, String>) it1.next();
			PnumPnom pN = new PnumPnom();
			pN.setPnum(e.getKey());
			pN.setPnom(e.getValue());
			list.add(pN);
			it1.remove();
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
		ExpressDBUtils parser = ExpressDBUtils.getParser("bd.exp", produits, relations_line, line_name);
		parser.parseFichier();
		
		Set<Entry<String,String[]>> rel_line = parser.getRelations_line().entrySet();
		Iterator<Entry<String,String[]>> it = rel_line.iterator();
		while(it.hasNext()){
			Entry<String,String[]> e = (Entry<String, String[]>) it.next();
			for(int i=0;i<e.getValue().length;i++){
				PmajeurPmineur pN = new PmajeurPmineur();
				pN.setPmajeur(parser.getLine_name().get(e.getKey()));
				pN.setPmineur(parser.getLine_name().get(e.getValue()[i]));
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
