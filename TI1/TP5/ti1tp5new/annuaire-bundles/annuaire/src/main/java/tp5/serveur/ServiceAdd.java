package tp5.serveur;

import java.util.ArrayList;
import java.util.HashMap;

import tp5.Site;
import tp5.SiteXMLDAO;

/**
 * Implementation d'un service d'ajout de sites a l'annuaire
 * @author D. CRESCENCE et S. FAURE
 *
 */
public class ServiceAdd extends AbstractAnnuaire {


	public ServiceAdd(ArrayList<Site> sites, SiteXMLDAO xdao){
		super(sites, xdao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String process(String commande, HashMap<String, String> parametres) {
		addSite(parametres.get("desc"), parametres.get("url"));
		return "";
	}

    private void addSite(String desc, String url) {
        Site s = new Site(desc, url, dao);
        // ajout dans la liste
        try {
            s.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // ajout dans le support de persistance
        sites.add(s);
    }

}
