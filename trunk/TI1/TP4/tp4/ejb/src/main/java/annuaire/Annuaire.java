package annuaire;

import java.util.ArrayList;
import java.util.Iterator;

public class Annuaire {

    ArrayList<Site> sites = new ArrayList<Site>();
    SiteDAO dao;

    public Annuaire(String d) {
        dao = new SiteXMLDAO(d);
    }

    public void addSite(String desc, String url) {
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

    public void removeSite(String desc, String url) {
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

    public String listSites() {
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
}