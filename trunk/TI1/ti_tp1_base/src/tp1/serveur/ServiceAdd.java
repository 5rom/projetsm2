package tp1.serveur;

import java.util.HashMap;

import tp1.Site;
import tp1.SiteContext;

/**
 * Implementation d'un service d'ajout de sites a l'annuaire
 * @author D. CRESCENCE et S. FAURE
 *
 */
public class ServiceAdd extends AbstractAnnuaire{

	/**
	 * Constructeur prenant le gestionnaire d'entité et le contexte en argument.
	 * @param sites
	 * @param sc
	 */
	public ServiceAdd(GestionnaireEntite sites, SiteContext sc) {
		super(sites, sc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String process(String commande, HashMap<String, String> parametres) {
		addSite(parametres.get("desc"), parametres.get("url"));
		return "";
	}
	
	
	/**
	 * Méthode d'ajout de site.
	 * @param desc
	 * @param url
	 */
	private void addSite(String desc, String url) {
        Site buffer = sites.getComponent(Site.class);
        buffer.setDescription(desc);
        buffer.setURL(url);
        sites.persist(buffer);
        buffer.setDescription("");
        buffer.setURL("");
    }

	@Override
	public void start() {
		// Affichage des informations du serveur
		System.out.println("Service d'ajout de sites démarré. "+"Objet d'accès aux données: "+sc.toString());
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
