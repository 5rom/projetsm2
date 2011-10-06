package tp1.serveur;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.annotations.Inject;
import org.picocontainer.behaviors.Caching;
import org.picocontainer.injectors.AnnotatedFieldInjection;

import tp1.DaoCallerException;
import tp1.Site;
import tp1.SiteContext;
import tp1.SiteContextImpl;
import tp1.SiteDAO;

public class GestionnaireEntite extends DefaultPicoContainer{

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	
	
	public GestionnaireEntite(DefaultPicoContainer pico,SiteContextImpl sc){
		super(new Caching(),pico);
	}
	
	/**
	 * Retourne le site correspondant a l'url
	 * @param url
	 * @return
	 */
	public Site find(String url){
		List<Object> liste;
		liste = this.getComponents();
		Iterator<Object> it = liste.iterator();
		while(it.hasNext()){
			Site s = (Site) it.next();
			if(url.equals(s.getURL()))
				return s;
		}
		this.addComponent("vide",Site.class);
		Site vide = (Site) this.getComponent("vide");
		try {
			((SiteDAO) vide.sc.getDAO("SiteDAO")).addSite(vide);
		} catch (DaoCallerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vide;
		
	}
	
	/**
	 * Retourne tous les sites correspondant a une description
	 * @param description
	 * @return
	 */
	public ArrayList<Site> getSitesByDescription(String description){
		ArrayList<Site> res = new ArrayList<Site>();
		List<Object> liste;
		liste = this.getComponents();
		Iterator<Object> it = liste.iterator();
		while(it.hasNext()){
			Site s = (Site) it.next();
			if(description.equals(s.getDescription()))
				res.add(s);
		}
		return res;
	}
	
	/**
	 * Assure la persistence
	 * @param site
	 */
	public void persist(Site site){
		try {
			((SiteDAO)site.sc.getDAO("SiteDAO")).addSite(site);
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
			((SiteDAO) site.sc.getDAO("SiteDAO")).deleteSite(site);
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
		this.removeComponentByInstance(Site.class);
	}

}
