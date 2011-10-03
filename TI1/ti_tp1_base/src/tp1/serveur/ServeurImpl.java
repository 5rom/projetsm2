package tp1.serveur;
import java.util.ArrayList;
import java.util.HashMap;
import static org.picocontainer.Characteristics.CACHE;

import org.picocontainer.*;

import tp1.SiteContextImpl;
import tp1.SiteDAO;
import tp1.SiteXMLDAO;

/**
 * Classe Serveur
 * Impl�mentation du serveur
 * @author D. CRESCENCE et S. FAURE
 *
 */
public class ServeurImpl implements Serveur {

	// L'annuaire du serveur
	//private Annuaire annu;
	
    /**
     * D�pendances vers les classes de service
     */
    private ServiceAdd serviceA;
    private ServiceRemove serviceR;
    private ServiceInitSites serviceI;
    private ServiceListeSites serviceL;	
	
	// Constructeur
	public ServeurImpl(HashMap<String,Object> listedao){
		
		// Instanciation du ContainerBuilder (DEPRECIE?)
		//ContainerBuilder cB = new ContainerBuilder();

		// Instanciation du picocontainer
		// DefaultPicoContainer pico = cB.build();
		DefaultPicoContainer pico = new DefaultPicoContainer();
		
		
		// Ajout des quatre composants de service ayants des dependances
		
		pico.addComponent(ServiceAdd.class);
		pico.addComponent(ServiceRemove.class);
		pico.addComponent(ServiceListeSites.class);
		pico.addComponent(ServiceInitSites.class);		
		
		// Ajout de composants dependants de l'annuaire
		//Annuaire retire car eclate en 4 classes
		//pico.addComponent(Annuaire.class);
		pico.as(CACHE).addComponent(ArrayList.class);
		//pico.as(CACHE).addComponent(SiteXMLDAO.class);
		pico.as(CACHE).addComponent(SiteContextImpl.class);
		
		//pico.addComponent(new String("test.xml"));
		
		
		// Cr�ation de l'annuaire
		//annu = pico.getComponent(Annuaire.class);
		
		//Nouvelle version
		serviceI= pico.getComponent(ServiceInitSites.class);		
		serviceA= pico.getComponent(ServiceAdd.class);
		serviceR= pico.getComponent(ServiceRemove.class);
		serviceL= pico.getComponent(ServiceListeSites.class);
	
		
		//initialisation de la variable dao
		/*/serviceI.sc.setSiteDAO(dao);
		serviceA.sc.setSiteDAO(dao);
		serviceR.sc.setSiteDAO(dao);
		serviceL.sc.setSiteDAO(dao);//*/
		
		/**/serviceI.sc.setDAO("SiteDAO",listedao.get("SiteDAO"));
		serviceA.sc.setDAO("SiteDAO",listedao.get("SiteDAO"));
		serviceR.sc.setDAO("SiteDAO",listedao.get("SiteDAO"));
		serviceL.sc.setDAO("SiteDAO",listedao.get("SiteDAO"));//*/
		
		
		//Appel de la m�thode start() de l'annuaire
		serviceI.start();
		serviceA.start();
		serviceR.start();
		serviceL.start();
		
		
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
