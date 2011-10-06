package tp1.serveur;

import java.util.ArrayList;
import java.util.Iterator;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.behaviors.Caching;
import tp1.DaoCallerException;
import tp1.Site;
import tp1.SiteContextImpl;
import tp1.SiteDAO;

/**
 * Gestionnaire d'entité, hérite de la classe conteneur
 * @author D. CRESCENCE et S. FAURE
 *
 */
public class GestionnaireEntite extends DefaultPicoContainer{

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Constructeur, le père est passé en paramètre pour créer ce fils.
	 * @param pico
	 */
	public GestionnaireEntite(DefaultPicoContainer pico){
		super(new Caching(),pico);
		pico.addComponent("description",String.class);
		pico.addComponent("url",String.class);
		this.addComponent(Site.class);
	}
	
	/**
	 * Retourne le site correspondant a l'url
	 * @param url
	 * @return
	 * @throws DaoCallerException 
	 */
	public Site find(String url) throws DaoCallerException{
		SiteDAO dao = ((SiteDAO)((SiteContextImpl)this.getParent().getComponent(SiteContextImpl.class)).getDAO("SiteDAO"));
		ArrayList<Site> liste = dao.getAllSites();
		Iterator<Site> it = liste.iterator();
		while(it.hasNext()){
			Site s = (Site) it.next();
			if(url.equals(s.getURL()))
				return s;
		}
		Site buffer = this.getComponent(Site.class);
		dao.addSite(buffer);
		return buffer;
		
	}
	
	/**
	 * Retourne tous les sites correspondant a une description
	 * @param description
	 * @return
	 * @throws DaoCallerException 
	 */
	public ArrayList<Site> getSitesByDescription(String description) throws DaoCallerException{
		SiteDAO dao = ((SiteDAO)((SiteContextImpl)this.getParent().getComponent(SiteContextImpl.class)).getDAO("SiteDAO"));
		ArrayList<Site> res = new ArrayList<Site>();
		ArrayList<Site> liste = dao.getAllSites();
		Iterator<Site> it = liste.iterator();
		Site buffer = this.getComponent(Site.class);
		while(it.hasNext()){
			buffer = (Site) it.next();
			if(description.equals(buffer.getDescription()))
				res.add(buffer);
		}
		return res;
	}
	
	/**
	 * Assure la persistence
	 * @param site
	 */
	public void persist(Site site){
		try {
			SiteDAO dao = ((SiteDAO)((SiteContextImpl)this.getParent().getComponent(SiteContextImpl.class)).getDAO("SiteDAO"));
			dao.addSite(site);
		} catch (DaoCallerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Supprime un site du conteneur et de la persistence 
	 * @param site
	 */
	public void remove(Site site){
		try {
			SiteDAO dao = ((SiteDAO)((SiteContextImpl)this.getParent().getComponent(SiteContextImpl.class)).getDAO("SiteDAO"));
			dao.deleteSite(site);
		} catch (DaoCallerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.detach(site);
	}
	
	/**
	 * Retire le site du contenaire
	 * @param site
	 */
	public void detach(Site site){
		this.removeComponentByInstance(site);
		
	}
	
	/**
	 * Supprime tous les sites du conteneur
	 */
	public void clear(){
		for(Object site: this.getComponents()) {
			this.removeComponentByInstance(site);
		}
	}

}
