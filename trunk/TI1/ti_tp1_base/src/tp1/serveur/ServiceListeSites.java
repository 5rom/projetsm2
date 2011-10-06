package tp1.serveur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import tp1.DaoCallerException;
import tp1.Site;
import tp1.SiteContext;

/**
 * Implementation d'un service de listing des sites de l'annuaire
 * @author D. CRESCENCE et S. FAURE
 *
 */
public class ServiceListeSites extends AbstractAnnuaire {

	/**
	 * Constructeur prenant le gestionnaire d'entité et le contexte en argument.
	 * @param sites
	 * @param sc
	 */
	public ServiceListeSites(GestionnaireEntite sites, SiteContext sc) {
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
		System.out.println("Service de listing des sites démarré. "+"Objet d'accès aux données: "+sc.toString());
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Méthode pour lister les sites.
	 * @return
	 */
	private String listSites() {
    	String ls = new String();
    	Site buffer = sites.getComponent(Site.class);
    	try {
			ArrayList<Site> liste = buffer.getAllSites();
			Iterator<Site> it = liste.iterator();
			while(it.hasNext()){
				buffer = (Site) it.next();
	            ls += "Description :\t" + buffer.getDescription() + "\n";
	            ls += "URL :\t" + buffer.getURL() + "\n";
			}
			buffer.setDescription(new String(""));
			buffer.setURL(new String(""));
		} catch (DaoCallerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ls;
    }	

}
