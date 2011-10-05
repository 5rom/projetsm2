package tp1.serveur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.picocontainer.MutablePicoContainer;

import tp1.Site;
import tp1.SiteContext;
import tp1.SiteXMLDAO;

/**
 * Implementation d'un service de suppression de sites a l'annuaire
 * @author D. CRESCENCE et S. FAURE
 *
 */
public class ServiceRemove extends AbstractAnnuaire {

	public ServiceRemove(MutablePicoContainer sites, SiteContext sc) {
		//super(sites, xdao);
		super(sites, sc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String process(String commande, HashMap<String, String> parametres) {
		removeSite(parametres.get("desc"), parametres.get("url"));
		return "";
	}
	
	@Deprecated
    private void removeSiteOld(String desc, String url) {
    	// Question 3.2
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
	
	private int removeSite(String desc, String url){
		Site s = new Site(desc, url);
		int result = 0;

		List<java.lang.Object> siteslist = sites.getComponents();
		Site temp;
		int i=0;

		sites.stop();
		try {
			for(Iterator<java.lang.Object> iter = siteslist.iterator(); iter.hasNext() ;) {
				temp = (Site) iter.next();
				if (s.equals(temp)) {
					temp.delete();
					sites.removeComponent("Site" + i);
					//On coupe le lien entre le conteneur et le Site
					// Plus aucune référence n'existe sur cet objet
					// On attend que le garbage collecting passe...
					sites.removeComponentByInstance(temp);
					result++;
				} else {
					//il faut changer les noms de référence des composants suivant ceux qu'on a enlevés,
					//sans quoi on aura un problème pour en rajouter d'autres...
					if (result>0) {
						sites.addComponent("Site" + (i-result), sites.getComponent("Site"+i));
						sites.removeComponent("Site"+i);
					}
				}
		       i++;
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		    result = -1;
		}
		sites.start();
		return result;
	}

	@Override
	public void start() {
		//System.out.println("Service de suppression de sites démarré. "+"Objet d'accès aux données: "+dao.toString());
		System.out.println("Service de suppression de sites démarré. "+"Objet d'accès aux données: "+sc.toString());
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
}
