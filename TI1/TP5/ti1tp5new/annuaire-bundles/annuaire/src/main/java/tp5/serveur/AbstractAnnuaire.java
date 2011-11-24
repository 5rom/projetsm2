package tp5.serveur;

import java.util.ArrayList;
import java.util.HashMap;


import tp5.Factory;
import tp5.Site;
import tp5.SiteInterface;

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
	ArrayList<SiteInterface> sites;
    
    Factory factory;
   

    public AbstractAnnuaire(ArrayList<SiteInterface> sites, Factory factory){
    	this.factory = factory;
    	this.sites = sites;
    }        
    
	@Override
	// Methode d'appel de methode en fonction de la commande passee en parametre
	public abstract String process(String commande, HashMap<String, String> parametres);

}
