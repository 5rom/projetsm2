package tp1.serveur;

import java.util.HashMap;
import java.util.List;

import org.picocontainer.MutablePicoContainer;

import tp1.DaoCallerException;
import tp1.Site;
import tp1.SiteContext;
import tp1.SiteDAO;

/**
 * Implementation d'un service d'initialisation des sites de l'annuaire
 * @author D. CRESCENCE et S. FAURE
 *
 */
public class ServiceInitSites extends AbstractAnnuaire {
	
	public ServiceInitSites(GestionnaireEntite sites, SiteContext sc) {
		//super(sites, xdao);
		super(sites,sc);
	}

	@Override
	public String process(String commande, HashMap<String, String> parametres) {
		try {
			initSites();
		} catch (DaoCallerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
    
	@Deprecated
	private void initSitesOld() throws DaoCallerException {
        // synchronisation de la liste et du support de persistance
		// Question 3.2
		//Site temp = new Site(dao);
		// Question 5.2
		//Site temp = new Site(sc);
		
		
		// Question 5.2
		/*try {
			sites = vide.getAllSites(sites);
		} catch (DaoCallerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//sites.start();
		sites.addComponent("vide", Site.class);
		Site vide = (Site) sites.getComponent("vide");
		List<Site> liste = vide.getAllSites();
		System.out.println("Nombre de sites : " + liste.size());
		/*Iterator it = liste.iterator();
		while(it.hasNext()){
			Site s = (Site) it.next();
			System.out.println(s.getURL());
		}*/
		sites.remove(vide);
		sites.stop();
		for(Object site: sites.getComponents()) {
			sites.removeComponentByInstance(site);
		}
		
		int i=0;
		for(Site s:liste) {
			String name = "Site" + i;
			sites.addComponent(name, Site.class);
			Site temp = (Site) sites.getComponent(name);
			temp.setDescription(s.getDescription());
			temp.setURL(s.getURL());
			i++;
		}
		
		sites.start();
		
        
    }
	
	@SuppressWarnings("static-access")
	private void initSites() throws DaoCallerException {
		Site vide = new Site();
		vide.sc = this.sc;
		List<Site> liste = vide.getAllSites();
		System.out.println("Nombre de sites : " + liste.size());
		
		sites.stop();
		sites.clear();
		
		int i=0;
		for(Site s:liste) {
			String name = "Site" + i;
			sites.addComponent(name, Site.class);
			Site temp = (Site) sites.getComponent(name);
			temp.setDescription(s.getDescription());
			temp.setURL(s.getURL());
			i++;
		}
		
		sites.start();
		
        
    }

	@Override
	public void start() {
			// TODO Auto-generated method stub
			// initSites();
			try {
				initSites();
			} catch (DaoCallerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
