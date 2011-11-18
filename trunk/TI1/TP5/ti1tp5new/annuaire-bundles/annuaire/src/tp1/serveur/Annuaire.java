package tp1.serveur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


import tp1.Site;
import tp1.SiteDAO;
import tp1.SiteXMLDAO;

public class Annuaire {

    ArrayList<Site> sites = new ArrayList<Site>();
    SiteDAO dao;

    public Annuaire(ArrayList<Site> sites, SiteXMLDAO xdao) {
    	dao = xdao;
    	this.sites = sites;
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

    private void removeSite(String desc, String url) {
        Site s = new Site(desc, url, dao);

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

    private String listSites() {
        String ls = new String();
        for (Iterator<Site> i = sites.iterator(); i.hasNext();) {
            Site s = (Site) i.next();
            ls += "Description :\t" + s.getDescription() + "\n";
            ls += "URL :\t" + s.getURL() + "\n";
        }
        return ls;
    }

    public void initSites() {
        // synchronisation de la liste et du support de persistance
        Site temp = new Site(dao);
        sites = temp.getAllSites(sites);
    }
    
	
	
	
	// Methode d'appel de methode en fonction de la commande passee en parametre
	public String process(String commande, HashMap<String, String> parametres){
		if (commande!=null){
			if (commande.equals("addSite")){
				addSite(parametres.get("desc"), parametres.get("url"));
			} else if (commande.equals("removeSite")){
				removeSite(parametres.get("desc"), parametres.get("url"));
			} else if (commande.equals("listSites")){
				return listSites();
			} else if (commande.equals("initSites")){
				initSites();
				return "";
			}
		}
		return "";
	}    
    
}