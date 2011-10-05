package tp1;

import java.util.ArrayList;

import org.picocontainer.Startable;
import org.picocontainer.annotations.Inject;

public class Site implements Startable{
	
	String description = null;
	String url = null;
    @Deprecated
	SiteDAO dao;
	@Inject
	static SiteContext sc;
	public static int COUNT;
	
	public Site () {
        //this.dao = dao;
		COUNT++;
	}
	
	public Site (String d, String u) {
		this.description = new String (d);
		this.url = new String (u);
		COUNT++;
		// Changement 3.2
        //this.dao = dao;
		// Changement 5.2
		//this.sc = sc;
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

    public void save() throws DaoCallerException {
        //dao.addSite(this);
    	//sc.getSiteDAO().addSite(this);
    	((SiteDAO) sc.getDAO("SiteDAO")).addSite(this);
    }

    public void delete() throws DaoCallerException {
        //dao.deleteSite(this);
    	//sc.getSiteDAO().deleteSite(this);
    	((SiteDAO) sc.getDAO("SiteDAO")).deleteSite(this);
    }

    public ArrayList<Site> getAllSites() throws DaoCallerException {
        //return dao.getAllSites(liste);
    	//return sc.getSiteDAO().getAllSites(liste);
    	return ((SiteDAO) sc.getDAO("SiteDAO")).getAllSites();
    }

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
}