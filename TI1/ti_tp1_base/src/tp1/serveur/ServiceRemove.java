package tp1.serveur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import tp1.DaoCallerException;
import tp1.Site;
import tp1.SiteContext;

/**
 * Implementation d'un service de suppression de sites a l'annuaire
 * @author D. CRESCENCE et S. FAURE
 *
 */
public class ServiceRemove extends AbstractAnnuaire {

	/**
	 * Constructeur prenant le gestionnaire d'entité et le contexte en argument.
	 * @param sites
	 * @param sc
	 */
	public ServiceRemove(GestionnaireEntite sites, SiteContext sc) {
		super(sites, sc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String process(String commande, HashMap<String, String> parametres) {
		try {
			removeSite(parametres.get("desc"), parametres.get("url"));
		} catch (DaoCallerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	
	/**
	 * Méthode pour retirer un site, l'appel au DAO pour la presistence peut causer une erreur d'appel DAO.
	 * @param desc
	 * @param url
	 * @throws DaoCallerException
	 */
	private void removeSite(String desc, String url) throws DaoCallerException{
		sites.stop();
		ArrayList<Site> liste = sites.getSitesByDescription(desc);
		Iterator<Site> it = liste.iterator();
		while(it.hasNext()){
			Site s = (Site) it.next();
			if(url.equals(s.getURL()))
				sites.remove(s);
		}
		sites.start();
	}

	@Override
	public void start() {
		System.out.println("Service de suppression de sites démarré. "+"Objet d'accès aux données: "+sc.toString());
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
}
