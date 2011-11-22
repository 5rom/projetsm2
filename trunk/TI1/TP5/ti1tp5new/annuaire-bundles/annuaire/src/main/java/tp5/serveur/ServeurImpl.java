package tp5.serveur;
import java.util.ArrayList;
import java.util.HashMap;


import tp5.Site;
import tp5.SiteXMLDAO;

/**
 * Classe Serveur
 * Implémentation du serveur
 * @author D. CRESCENCE et S. FAURE
 *
 */
public class ServeurImpl implements Serveur {

	// L'annuaire du serveur
	private ServiceAdd addSite;
	private ServiceRemove removeSite;
	private ServiceListeSites listSites;
	private ServiceInitSites initSites;
	
	// Constructeur
	public ServeurImpl(){		
		
		// Création de l'annuaire
		SiteXMLDAO xdao = new SiteXMLDAO("test.xml");
		ArrayList<Site> sites = new ArrayList<Site>();
		//annu = new Annuaire(sites, xdao);
		initSites = new ServiceInitSites(sites,xdao);
		listSites = new ServiceListeSites(sites,xdao);
		addSite = new ServiceAdd(sites,xdao);
		removeSite = new ServiceRemove(sites,xdao);
		
	}
	
	
	@Override
	public String traiteRequete(String commande, HashMap<String, String> parametres){
		return aiguilleRequete(commande, parametres);
	}
	
	public String aiguilleRequete(String commande, HashMap<String, String> parametres){
        if (commande!=null){
                if (commande.equals("addSite")){
                        addSite.process(commande,parametres);
                } else if (commande.equals("removeSite")){
                        removeSite.process(commande,parametres);
                } else if (commande.equals("listSites")){
                        return listSites.process(commande,parametres);
                } else if (commande.equals("initSites")){
                        initSites.process(commande,parametres);
                        return "";
                }
        }
        return "";              
	}
	
}
