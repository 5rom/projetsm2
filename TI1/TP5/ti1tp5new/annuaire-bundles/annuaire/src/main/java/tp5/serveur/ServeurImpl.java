package tp5.serveur;
import java.util.ArrayList;
import java.util.HashMap;


import tp5.Factory;
import tp5.Site;
import tp5.SiteInterface;

/**
 * Classe Serveur
 * Implémentation du serveur
 * @author D. CRESCENCE et S. FAURE
 *
 */
public class ServeurImpl implements Serveur {

	// L'annuaire du serveur
	public Factory factory;
	private ServiceAdd addSite;
	private ServiceRemove removeSite;
	private ServiceListeSites listSites;
	private ServiceInitSites initSites;
	
	// Constructeur
	public ServeurImpl(Factory factory){		
		
		// Création de l'annuaire
		this.factory = factory;
		ArrayList<SiteInterface> sites = new ArrayList<SiteInterface>();
		//annu = new Annuaire(sites, xdao);
		initSites = new ServiceInitSites(sites,factory);
		listSites = new ServiceListeSites(sites,factory);
		addSite = new ServiceAdd(sites,factory);
		removeSite = new ServiceRemove(sites,factory);
		
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
