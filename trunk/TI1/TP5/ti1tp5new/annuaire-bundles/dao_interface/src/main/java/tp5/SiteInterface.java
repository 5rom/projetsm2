package tp5;

import java.util.ArrayList;


public interface SiteInterface {


	public abstract String getDescription();

	public abstract void setDescription(String d);

	public abstract String getURL();

	public abstract void setURL(String u);

	public abstract void save();

	public abstract void delete();

	public ArrayList<SiteInterface> getAllSites(ArrayList<SiteInterface> liste);

}