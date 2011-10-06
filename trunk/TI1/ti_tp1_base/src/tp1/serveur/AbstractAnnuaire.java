package tp1.serveur;

import java.util.HashMap;

import org.picocontainer.Startable;
import tp1.SiteContext;

/**
 * Classe abstraite AbstractAnnuaire
 * Permettra d'implémenter des services lié à l'annuaire côté serveur
 * (par ex. le service d'ajout d'un site à l'annuaire. Ex méthode addSite...)
 * Implemente Startable pour pouvoir lance un service et AnnuaireInterface pour
 * disposer de la methode de service du serveur (traite les requetes des clients)
 * @author D. CRESCENCE et S. FAURE
 *
 */
public abstract class AbstractAnnuaire implements Startable , AnnuaireInterface{
    
	public GestionnaireEntite sites;
	
	/**
	 * Pattern context pour la communication avec les DAO
	 */
	public SiteContext  sc;
   

    /**
     * Constructeur de la classe abstraite.
     * @param sites
     * @param sc
     */
    protected AbstractAnnuaire(GestionnaireEntite sites, SiteContext sc) {
    	this.sc = sc;
    	this.sites = sites;
    }        
    
	@Override
	public abstract void start();

	@Override
	public abstract void stop();
	
	@Override
	// Methode d'appel de methode en fonction de la commande passee en parametre
	public abstract String process(String commande, HashMap<String, String> parametres);

}
