package tp5.serveur;

import java.util.ArrayList;
import java.util.HashMap;

import tp5.Site;
import tp5.SiteXMLDAO;

/**
 * Implementation d'un service d'initialisation des sites de l'annuaire
 * @author D. CRESCENCE et S. FAURE
 *
 */
public class ServiceInitSites extends AbstractAnnuaire {
	
	
	public ServiceInitSites(ArrayList<Site> sites, SiteXMLDAO xdao){
		super(sites, xdao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String process(String commande, HashMap<String, String> parametres) {
		initSites();
		return "";
	}
    
	private void initSites() {
        // synchronisation de la liste et du support de persistance
        Site temp = new Site(dao);
        sites = temp.getAllSites(sites);
    }	
	
}
