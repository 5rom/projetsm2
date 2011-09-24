package tp1.serveur;

import java.util.ArrayList;
import java.util.HashMap;

import org.picocontainer.Startable;
import tp1.Site;
import tp1.SiteDAO;
import tp1.SiteXMLDAO;

public abstract class AbstractAnnuaire implements Startable , AnnuaireInterface{
    /**
     * La liste de sites
     */
	ArrayList<Site> sites;
	
	/**
	 * Le SiteDAO (DAO = Direct Access Object)
	 */
    SiteDAO dao;
   

    public AbstractAnnuaire(ArrayList<Site> sites, SiteXMLDAO xdao) {
    	dao = xdao;
    	this.sites = sites;
    }        
    
	@Override
	public abstract void start();

	@Override
	public abstract void stop();
	
	@Override
	// Methode d'appel de methode en fonction de la commande passee en parametre
	public abstract String process(String commande, HashMap<String, String> parametres);//{
		/*if (commande!=null){
			if (commande.equals("addSite")){
				addSite(parametres.get("desc"), parametres.get("url"));
			} else if (commande.equals("removeSite")){
				removeSite(parametres.get("desc"), parametres.get("url"));
			} else if (commande.equals("listSites")){
				return listSites();
			} else if (commande.equals("initSites")){
				initSites();
				return "";
			}
		}
		return "";
	}    	*/
}
