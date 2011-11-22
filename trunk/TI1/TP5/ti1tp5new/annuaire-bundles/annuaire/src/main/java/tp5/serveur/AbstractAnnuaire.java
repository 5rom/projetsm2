package tp5.serveur;

import java.util.ArrayList;
import java.util.HashMap;

import org.picocontainer.Startable;

import tp5.Site;
import tp5.SiteDAO;
import tp5.SiteXMLDAO;

/**
 * Classe abstraite AbstractAnnuaire
 * Permettra d'impl�menter des services li� � l'annuaire c�t� serveur
 * (par ex. le service d'ajout d'un site � l'annuaire. Ex m�thode addSite...)
 * Implemente Startable pour pouvoir lance un service et AnnuaireInterface pour
 * disposer de la methode de service du serveur (traite les requetes des clients)
 * @author D. CRESCENCE et S. FAURE
 *
 */
public abstract class AbstractAnnuaire implements AnnuaireInterface{

	/**
     * La liste de sites
     */
	ArrayList<Site> sites;
	
	/**
	 * Le SiteDAO (DAO = Direct Access Object)
	 */
    SiteDAO dao;
   

    public AbstractAnnuaire(ArrayList<Site> sites, SiteXMLDAO xdao){
    	dao = xdao;
    	this.sites = sites;
    }        
    
	@Override
	// Methode d'appel de methode en fonction de la commande passee en parametre
	public abstract String process(String commande, HashMap<String, String> parametres);

}
