package tp1.serveur;
import java.util.HashMap;
import org.picocontainer.*;
import org.picocontainer.behaviors.Caching;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import tp1.DOMUtil;
import tp1.SiteContextImpl;
import tp1.SiteDAO;
import tp1.SiteXMLDAO;

/**
 * Classe Serveur
 * Implémentation du serveur
 * @author D. CRESCENCE et S. FAURE
 *
 */
public class ServeurImpl implements Serveur {

	// L'annuaire du serveur
	//private Annuaire annu;
	
    /**
     * Dépendances vers les classes de service
     */
    private DefaultPicoContainer pico;
	

	/**
	 * Constructeur qui prend en paramète un fichier de configuration pour l'instanciation des DAO.
	 * @param fichier
	 */
	public ServeurImpl(String fichier){
		// Instanciation des DAO via le fichier de conf
		HashMap<String,Object> listedao = new HashMap<String,Object>();
		Document doc = DOMUtil.getDocumentFromFile(fichier);
		doc.getDocumentElement().normalize();
		NodeList nl = doc.getElementsByTagName("object");
		// On instancie a partir du xml avec des objets génériques pour les dao inconnus et avec un sitedao si un element sitedao est précisé dans le xml
		System.out.println("Instanciation des objets issus de "+fichier+"\n");
		for(int i=0;i<nl.getLength();i++){
			Element object = (Element) nl.item(i);
			Object dao = new Object();
			String name = object.getChildNodes().item(0).getTextContent();
			System.out.println("Objet " + ((Integer) i).toString() + " : " + name);
			if(new String("SiteDAO").equals(name)){
				SiteDAO sitedao = new SiteXMLDAO(object.getChildNodes().item(1).getTextContent());
				dao = sitedao;
			}
			// Si il y avait une gestion des objets autre que siteDAO, il faudrait ici mettre un case et instancier les objets avec leur constructeur via le xml
			listedao.put(name, dao);
		}
		System.out.println("\n");

		pico = new DefaultPicoContainer(new Caching());
		SiteContextImpl sc = new SiteContextImpl();
		sc.setDAO("SiteDAO", listedao.get("SiteDAO"));
		pico.addComponent(sc);
		GestionnaireEntite fils = new GestionnaireEntite(pico);
		pico.addComponent(fils);
		
		
		// Ajout des quatre composants de service ayants des dependances
		pico.addComponent("addSite",ServiceAdd.class);
		pico.addComponent("removeSite",ServiceRemove.class);
		pico.addComponent("listSites",ServiceListeSites.class);
		pico.addComponent("initSites",ServiceInitSites.class);			
		
		// Demarrage des services
		pico.start();
		
		
		
		// Ensuite l'arraylist sera remplacee par un sous conteneur.
	}
	
	@Override
	public void traiteRequete(String commande, HashMap<String, String> parametres){
		// Aiguillage
		aiguilleRequete(commande, parametres);
	}
	
	/**
	 * Aiguillage d'une requête arrivant pour le serveur.
	 * @param commande
	 * @param parametres
	 */
	public void aiguilleRequete(String commande, HashMap<String, String> parametres){
		if (commande!=null){
			((AnnuaireInterface)pico.getComponent(commande)).process(commande, parametres);
		}	
	}
	
}
