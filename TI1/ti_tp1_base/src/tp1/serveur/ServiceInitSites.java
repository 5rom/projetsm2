package tp1.serveur;

import java.util.HashMap;
import java.util.List;

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
	
	/**
	 * Constructeur prenant le gestionnaire d'entité et le contexte en argument.
	 * @param sites
	 * @param sc
	 */
	public ServiceInitSites(GestionnaireEntite sites, SiteContext sc) {
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
	
	/**
	 * Méthode d'intialisation des sits par rapport au DAO, l'accès peut renvoyer une erreur d'accès DAO.
	 * @throws DaoCallerException
	 */
	private void initSites() throws DaoCallerException {
		Site buffer = sites.getComponent(Site.class);
		List<Site> liste = buffer.getAllSites();
		System.out.println("Nombre de sites : " + liste.size());        
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
