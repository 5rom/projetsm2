package sic.services.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sic.services.utils.cao.RelDBUtils;
import fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.CAODataBase;
import fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.PmajeurPmineur;
import fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.PnumPnom;





/**
 * Classe d'implementation du service CAODataBaseService
 * Créée par Sébastien Faure et David Crescence pour publication.
 * @author David CRESCENCE <crescence.david@gmail.com> et Sébastien FAURE <sebastien.faure3@gmail.com>
 * UCBL M2TI 2011-2012 
 */

public class CAODataBaseService implements CAODataBase{

	/**
	 * Le logger
	 */
	//private static final Logger log = LoggerFactory.getLogger(CAODataBaseService.class);

	@Override
	public Boolean addProduit(long pnum, String pnom) {
		RelDBUtils r = RelDBUtils.getConnexion();
		Statement st=null;
		Boolean success=false;
		try {
			st = r.getConnection().createStatement();
			st.executeUpdate("INSERT INTO PRODUIT (Pnum,Pnom) VALUES("+pnum+",'"+pnom+"')");	
			success=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*
		try {
			r.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/		
		return success;
	}

	@Override
	public List<PnumPnom> getProduitList() {
		ArrayList<PnumPnom> list = new ArrayList<PnumPnom>();
		RelDBUtils r = RelDBUtils.getConnexion();
		Statement st=null;
		
		try {
			st = r.getConnection().createStatement();
			ResultSet rs=st.executeQuery("SELECT * FROM PRODUIT ORDER BY Pnum");	

            // On parcourt les enregistrements de Produit
            while (rs.next()){
            		PnumPnom pN = new PnumPnom();
            		pN.setPnum(Long.parseLong(rs.getString("Pnum")));
            		pN.setPnom(rs.getString("Pnom"));
            		list.add(pN);
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*
		try {
			r.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		return list;
	}

	@Override
	public List<PmajeurPmineur> getCompositionList() {
		ArrayList<PmajeurPmineur> list = new ArrayList<PmajeurPmineur>();
		RelDBUtils r = RelDBUtils.getConnexion();
		Statement st=null;
		
		try {
			st = r.getConnection().createStatement();
			ResultSet rs=st.executeQuery("SELECT * FROM COMPOSITION ORDER BY Pmajeur");	

            // On parcourt les enregistrements de Produit
            while (rs.next()){
            	PmajeurPmineur pM = new PmajeurPmineur();
            	pM.setPmajeur(Long.parseLong(rs.getString("Pmajeur")));
            	pM.setPmineur(Long.parseLong(rs.getString("Pmineur")));
        		list.add(pM);
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*
		try {
			r.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		return list;
	}

	@Override
	public Boolean deleteProduit(long pnum) {
		RelDBUtils r = RelDBUtils.getConnexion();
		Statement st=null;
		Boolean success=false;
		try {
			st = r.getConnection().createStatement();
			// Suppression des compositions qui portent sur ce produit
			st.executeUpdate("DELETE FROM COMPOSITION WHERE Pmajeur="+pnum+" OR Pmineur="+pnum);
			// Suppression du produit lui-même
			int res=st.executeUpdate("DELETE FROM PRODUIT WHERE Pnum="+pnum);
			if (res>0){
				success=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*
		try {
			r.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/		
		return success;
	}

	@Override
	public Boolean deleteComposition(long pmajeur, long pmineur) {
		RelDBUtils r = RelDBUtils.getConnexion();
		Statement st=null;
		Boolean success=false;
		try {
			st = r.getConnection().createStatement();
			// Suppression de la composition correspondante
			int res=st.executeUpdate("DELETE FROM COMPOSITION WHERE Pmajeur="+pmajeur+" AND Pmineur="+pmineur);
			if (res>0){
				success=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*
		try {
			r.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/		
		return success;
	}

	@Override
	public Boolean addComposition(long pmajeur, long pmineur) {
		RelDBUtils r = RelDBUtils.getConnexion();
		Statement st=null;
		Boolean success=false;
		try {
			st = r.getConnection().createStatement();
			st.executeUpdate("INSERT INTO COMPOSITION (Pmajeur,Pmineur) VALUES("+pmajeur+",'"+pmineur+"')");	
			success=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*
		try {
			r.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		return success;
	}



	@Override
	public Boolean updateProduit(long pnum, String pnom) {
		RelDBUtils r = RelDBUtils.getConnexion();
		Statement st=null;
		Boolean success=false;
		try {
			st = r.getConnection().createStatement();
			// Mise à jour du produit
			int res=st.executeUpdate("UPDATE PRODUIT SET Pnom='"+pnom+"' WHERE Pnum="+pnum);
			if (res>0){
				success=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*
		try {
			r.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		return success;

	}

}



