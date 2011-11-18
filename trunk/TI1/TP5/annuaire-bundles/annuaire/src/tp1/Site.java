package tp1;

import java.util.ArrayList;

public class Site {
	
	String description = null;
	String url = null;
    SiteDAO dao;
	
	public Site (SiteDAO dao) {
        this.dao = dao;
	}
	
	public Site (String d, String u, SiteDAO dao) {
		this.description = new String (d);
		this.url = new String (u);
        this.dao = dao;
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
        dao.addSite(this);
    }

    public void delete() {
        dao.deleteSite(this);
    }

    public ArrayList<Site> getAllSites(ArrayList<Site> liste) {
        return dao.getAllSites(liste);
    }
}