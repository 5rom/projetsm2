package tp5;

import java.util.ArrayList;


public class Site implements SiteInterface {
	
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

    /* (non-Javadoc)
	 * @see tp5.implement.SiteInterface#equals(tp5.implement.SiteInterface)
	 */
    public boolean equals(Site other) {
        return (this.description.equals(other.getDescription())) && (this.url.equals(other.getURL()));
    }

	/* (non-Javadoc)
	 * @see tp5.implement.SiteInterface#getDescription()
	 */
	public String getDescription() {
		return this.description;
	}
	
	/* (non-Javadoc)
	 * @see tp5.implement.SiteInterface#setDescription(java.lang.String)
	 */
	public void setDescription(String d) {
		this.description = d;
	}
	
	/* (non-Javadoc)
	 * @see tp5.implement.SiteInterface#getURL()
	 */
	public String getURL() {
		return this.url;
	}
	
	/* (non-Javadoc)
	 * @see tp5.implement.SiteInterface#setURL(java.lang.String)
	 */
	public void setURL(String u) {
		this.url = u;
	}

    /* (non-Javadoc)
	 * @see tp5.implement.SiteInterface#save()
	 */
    public void save() {
        dao.addSite(this);
    }

    public void delete() {
        dao.deleteSite(this);
    }

    public ArrayList<SiteInterface> getAllSites(ArrayList<SiteInterface> liste) {
        return dao.getAllSites(liste);
    }
}