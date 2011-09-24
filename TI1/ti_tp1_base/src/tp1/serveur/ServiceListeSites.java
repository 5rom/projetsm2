package tp1.serveur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import tp1.Site;
import tp1.SiteXMLDAO;

/**
 * Implementation d'un service de listing des sites de l'annuaire
 * @author D. CRESCENCE et S. FAURE
 *
 */
public class ServiceListeSites extends AbstractAnnuaire {

	public ServiceListeSites(ArrayList<Site> sites, SiteXMLDAO xdao) {
		super(sites, xdao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String process(String commande, HashMap<String, String> parametres) {
		// TODO Auto-generated method stub
		System.out.println(listSites());
		return new String();
	}

	@Override
	public void start() {
		System.out.println("Service de listing des sites démarré. "+"Objet d'accès aux données: "+dao.toString());	
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
    private String listSites() {
        String ls = new String();
        for (Iterator<Site> i = sites.iterator(); i.hasNext();) {
            Site s = (Site) i.next();
            ls += "Description :\t" + s.getDescription() + "\n";
            ls += "URL :\t" + s.getURL() + "\n";
        }
        return ls;
    }	

}
