package tp1.serveur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import tp1.Site;
import tp1.SiteContext;
import tp1.SiteXMLDAO;

/**
 * Implementation d'un service de suppression de sites a l'annuaire
 * @author D. CRESCENCE et S. FAURE
 *
 */
public class ServiceRemove extends AbstractAnnuaire {

	public ServiceRemove(ArrayList<Site> sites, SiteContext sc) {
		//super(sites, xdao);
		super(sites, sc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String process(String commande, HashMap<String, String> parametres) {
		removeSite(parametres.get("desc"), parametres.get("url"));
		return "";
	}

    private void removeSite(String desc, String url) {
        //Site s = new Site(desc, url, dao);
    	Site s = new Site(desc, url, sc);
        // suppression dans la liste
        for (Iterator<Site> i = sites.iterator(); i.hasNext();) {
            Site temp = (Site) i.next();
            if (temp.equals(s)) {
                sites.remove(temp);
                removeSite(desc, url);
                return;
            }
        }
        // suppression dans le support de persistance
        try {
            s.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public void start() {
		//System.out.println("Service de suppression de sites d�marr�. "+"Objet d'acc�s aux donn�es: "+dao.toString());
		System.out.println("Service de suppression de sites d�marr�. "+"Objet d'acc�s aux donn�es: "+sc.getSiteDAO().toString());
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
}