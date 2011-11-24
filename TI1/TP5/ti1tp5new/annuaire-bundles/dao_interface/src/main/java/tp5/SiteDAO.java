package tp5;

import java.util.ArrayList;

public interface SiteDAO {
    public void addSite(SiteInterface item);
    public void deleteSite(SiteInterface item);
    public ArrayList<SiteInterface> getAllSites(ArrayList<SiteInterface> liste);
    public String getInfos();
}
