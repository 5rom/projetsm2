package tp1.serveur;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.picocontainer.MutablePicoContainer;

import tp1.Site;
import tp1.SiteContext;
//import tp1.SiteXMLDAO;

/**
 * Implementation d'un service de listing des sites de l'annuaire
 * @author D. CRESCENCE et S. FAURE
 *
 */
public class ServiceListeSites extends AbstractAnnuaire {

	public ServiceListeSites(GestionnaireEntite sites, SiteContext sc) {
		//super(sites, xdao);
		super(sites, sc);
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
		//System.out.println("Service de listing des sites démarré. "+"Objet d'accès aux données: "+dao.toString());
		System.out.println("Service de listing des sites démarré. "+"Objet d'accès aux données: "+sc.toString());
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
	@Deprecated
    private String listSitesOld() {
    	// Question 5.3
        /*
        for (Iterator<Site> i = sites.iterator(); i.hasNext();) {
            Site s = (Site) i.next();
            ls += "Description :\t" + s.getDescription() + "\n";
            ls += "URL :\t" + s.getURL() + "\n";
        }*/
    	String ls = new String();
    	List<java.lang.Object> siteslist = sites.getComponents();
    	Site temp;
		Iterator<java.lang.Object> it = siteslist.iterator();
		while(it.hasNext()) {
            temp = (Site) it.next();
            ls += "Description :\t" + temp.getDescription() + "\n";
            ls += "URL :\t" + temp.getURL() + "\n";
    	}
        return ls;
	}
	
	private String listSites() {
    	String ls = new String();
    	List<java.lang.Object> siteslist = sites.getComponents();
    	Site temp;
		Iterator<java.lang.Object> it = siteslist.iterator();
		while(it.hasNext()) {
            temp = (Site) it.next();
            ls += "Description :\t" + temp.getDescription() + "\n";
            ls += "URL :\t" + temp.getURL() + "\n";
    	}
        return ls;
    }	

}
