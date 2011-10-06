package tp1;

import java.util.ArrayList;

import org.picocontainer.Startable;
import org.picocontainer.annotations.Inject;

/**
 * Classe Site qui gère la description des sites et leur mÈéthodes associÈées.
 * @author D. CRESCENCE et S. FAURE
 *
 */

public class Site implements Startable{
	
	String description;
	String url;
	@Inject
	public static SiteContext sc;
	public static int COUNT;
	
	public Site () {
		COUNT++;
	}
	
	public Site (String d, String u) {
		this.description = new String (d);
		this.url = new String (u);
		COUNT++;
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
        ((SiteDAO) sc.getDAO("SiteDAO")).addSite(this);
    }

    public void delete() throws DaoCallerException {
        ((SiteDAO) sc.getDAO("SiteDAO")).deleteSite(this);
    }

    public ArrayList<Site> getAllSites() throws DaoCallerException {
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