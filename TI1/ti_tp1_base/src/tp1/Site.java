package tp1;

import java.util.ArrayList;

public class Site {
	
	String description = null;
	String url = null;
    //SiteDAO dao;
	SiteContext sc;
	
	public Site (SiteContext sc) {
        //this.dao = dao;
		this.sc = sc;
	}
	
	public Site (String d, String u, SiteContext sc) {
		this.description = new String (d);
		this.url = new String (u);
        //this.dao = dao;
		this.sc = sc;
	}

    public boolean equals(Site other) {
        return (this.description.equals(other.getDescription())) && (this.url.equals(other.getURL()));
    }

	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String d) {
		this.description = d;
	}
	
	public String getURL() {
		return this.url;
	}
	
	public void setURL(String u) {
		this.url = u;
	}

    public void save() {
        //dao.addSite(this);
    	sc.getSiteDAO().addSite(this);
    }

    public void delete() {
        //dao.deleteSite(this);
    	sc.getSiteDAO().deleteSite(this);
    }

    public ArrayList<Site> getAllSites(ArrayList<Site> liste) {
        //return dao.getAllSites(liste);
    	return sc.getSiteDAO().getAllSites(liste);
    }
}