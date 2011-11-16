package tp1;

import java.util.ArrayList;

public interface SiteDAO {
    public void addSite(Site item);
    public void deleteSite(Site item);
    public ArrayList<Site> getAllSites(ArrayList<Site> liste);
    public String getInfos();
}
