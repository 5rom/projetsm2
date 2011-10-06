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
    private ServiceAdd serviceA;
    private ServiceRemove serviceR;
    private ServiceInitSites serviceI;
    private ServiceListeSites serviceL;	
	
	// Constructeur
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
		
		// Instanciation du ContainerBuilder (DEPRECIE?)
		//ContainerBuilder cB = new ContainerBuilder();

		// Instanciation du picocontainer
		// DefaultPicoContainer pico = cB.build();
		DefaultPicoContainer pico = new DefaultPicoContainer(new Caching());
		
		// Question 5.3
		/*pico.addComponent(SiteContextImpl.class);
		pico.getComponent(SiteContextImpl.class).setDAO("SiteDAO",listedao.get("SiteDAO"));
		// Injection par annotation
		PicoBuilder builder = new PicoBuilder(pico);
		MutablePicoContainer fils = (DefaultPicoContainer) builder.withAnnotatedFieldInjection().withCaching().build();*/
		SiteContextImpl sc = new SiteContextImpl();
		sc.setDAO("SiteDAO", listedao.get("SiteDAO"));
		pico.addComponent(sc);
		GestionnaireEntite fils = new GestionnaireEntite(pico,sc);
		pico.addComponent(fils);
		
		
		
		// Ajout des quatre composants de service ayants des dependances
		pico.addComponent(ServiceAdd.class);
		pico.addComponent(ServiceRemove.class);
		pico.addComponent(ServiceListeSites.class);
		pico.addComponent(ServiceInitSites.class);	
		
		// Ajout de composants dependants de l'annuaire
		//Annuaire retire car eclate en 4 classes
		//pico.addComponent(Annuaire.class);
		//pico.as(CACHE).addComponent(ArrayList.class);
		//pico.as(CACHE).addComponent(SiteXMLDAO.class);
		//pico.addComponent(new String("test.xml"));
		
		
		
		
		
		
		
		
		// Création de l'annuaire
		//annu = pico.getComponent(Annuaire.class);
		
		// Nouvelle version
		serviceI= pico.getComponent(ServiceInitSites.class);
		serviceA= pico.getComponent(ServiceAdd.class);
		serviceR= pico.getComponent(ServiceRemove.class);
		serviceL= pico.getComponent(ServiceListeSites.class);
		
		// Demarrage des services
		pico.start();
		
		
		
		// Ensuite l'arraylist sera remplacee par un sous conteneur.
	}
	
	//@Override
	// Accesseur sur l'annuaire
	//public Annuaire getAnnuaire(){
	//	return annu;
	//}
	
	@Override
	public void traiteRequete(String commande, HashMap<String, String> parametres){
		//return annu.process(commande, parametres);
		// Aiguillage
		aiguilleRequete(commande, parametres);
	}
	
	public void aiguilleRequete(String commande, HashMap<String, String> parametres){
		if (commande!=null){
			if (commande.equals("addSite")){
				serviceA.process(commande,parametres);
			} else if (commande.equals("removeSite")){
				serviceR.process(commande,parametres);
			} else if (commande.equals("listSites")){
				serviceL.process(commande,parametres);
			} else if (commande.equals("initSites")){
				serviceI.process(commande,parametres);
			}
		}	
	}
	
}
