package tp5.serveur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import tp5.Factory;
import tp5.Site;
import tp5.SiteInterface;

/**
 * Implementation d'un service de suppression de sites a l'annuaire
 * @author D. CRESCENCE et S. FAURE
 *
 */
public class ServiceRemove extends AbstractAnnuaire {


	public ServiceRemove(ArrayList<SiteInterface> sites, Factory factory){
		super(sites, factory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String process(String commande, HashMap<String, String> parametres) {
		removeSite(parametres.get("desc"), parametres.get("url"));
		return "";
	}

    private void removeSite(String desc, String url) {
        Site s = this.factory.getSite(desc, url);
        // suppression dans la liste
        for (Iterator<SiteInterface> i = sites.iterator(); i.hasNext();) {
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

		
}
