package tp1.serveur;

import java.util.ArrayList;
import java.util.HashMap;

import tp1.DaoCallerException;
import tp1.Site;
import tp1.SiteContext;
import tp1.SiteDAO;
import tp1.SiteXMLDAO;

/**
 * Implementation d'un service d'initialisation des sites de l'annuaire
 * @author D. CRESCENCE et S. FAURE
 *
 */
public class ServiceInitSites extends AbstractAnnuaire {
	
	public ServiceInitSites(ArrayList<Site> sites, SiteContext sc) {
		//super(sites, xdao);
		super(sites,sc);
	}

	@Override
	public String process(String commande, HashMap<String, String> parametres) {
		initSites();
		return "";
	}
    
	private void initSites() {
        // synchronisation de la liste et du support de persistance
		//Site temp = new Site(dao);
		Site temp = new Site(sc);
        try {
			sites = temp.getAllSites(sites);
		} catch (DaoCallerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void start() {
			// TODO Auto-generated method stub
			// initSites();
			initSites();
			// Affichage des informations du serveur
			//System.out.println("Service d'initialisation des sites démarré. "+"Objet d'accès aux données: "+dao.toString());		
			try {
				//System.out.println("Service d'initialisation des sites démarré. "+"Objet d'accès aux données: "+sc.getSiteDAO().toString());
				System.out.println("Service d'initialisation des sites démarré. "+"Objet d'accès aux données: "+ ((SiteDAO) sc.getDAO("SiteDAO")).toString());
				
			} catch (DaoCallerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}	
	
}
