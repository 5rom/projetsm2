package tp1;

import java.util.ArrayList;

/**
 * Interface du siteDAO
 * @author D. CRESCENCE et S. FAURE
 *
 */

public interface SiteDAO {
    public void addSite(Site item);
    public void deleteSite(Site item);
    public ArrayList<Site> getAllSites();
    public String getInfos();
}
