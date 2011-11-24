package tp5.serveur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import tp5.Factory;
import tp5.Site;
import tp5.SiteInterface;

/**
 * Implementation d'un service de listing des sites de l'annuaire
 * @author D. CRESCENCE et S. FAURE
 *
 */
public class ServiceListeSites extends AbstractAnnuaire {


	public ServiceListeSites(ArrayList<SiteInterface> sites, Factory factory){
		super(sites, factory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String process(String commande, HashMap<String, String> parametres) {
		// TODO Auto-generated method stub
		//System.out.println(listSites());
		return listSites();
	}

	
    private String listSites() {
        String ls = new String();
        for (Iterator<SiteInterface> i = sites.iterator(); i.hasNext();) {
            Site s = (Site) i.next();
            ls += "Description : " + s.getDescription() + "</br>";
            ls += "URL : " + s.getURL() + "</br></br>";
        }
        return ls;
    }	

}
