package sic.services.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sic.services.utils.cao.RelDBUtils;
import fr.univ_lyon1.master_info.m2ti.tiw5.services.CAODataBase;




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
	private static final Logger log = LoggerFactory.getLogger(CAODataBaseService.class);

	@Override
	public Boolean addProduit(long pnum, String pnom) {
		RelDBUtils r = new RelDBUtils();
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

		try {
			r.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}
 

}



