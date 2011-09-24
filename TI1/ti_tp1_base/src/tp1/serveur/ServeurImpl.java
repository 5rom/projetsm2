package tp1.serveur;
import java.util.ArrayList;
import java.util.HashMap;
import static org.picocontainer.Characteristics.SDI;
import static org.picocontainer.Characteristics.CACHE;

import org.picocontainer.*;

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
	public ServeurImpl(){
		
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
		pico.as(CACHE).addComponent(SiteXMLDAO.class);
		pico.addComponent(new String("test.xml"));
		
		
		// Création de l'annuaire
		//annu = pico.getComponent(Annuaire.class);
		
		//Nouvelle version
		serviceI= pico.getComponent(ServiceInitSites.class);		
		serviceA= pico.getComponent(ServiceAdd.class);
		serviceR= pico.getComponent(ServiceRemove.class);
		serviceL= pico.getComponent(ServiceListeSites.class);
	
		
		
		
		//Appel de la méthode start() de l'annuaire
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
	public String traiteRequete(String commande, HashMap<String, String> parametres){
		//return annu.process(commande, parametres);
		// Aiguillage
		return aiguilleRequete(commande, parametres);
	}
	
	public String aiguilleRequete(String commande, HashMap<String, String> parametres){
		if (commande!=null){
			if (commande.equals("addSite")){
				serviceA.process(commande,parametres);
			} else if (commande.equals("removeSite")){
				serviceR.process(commande,parametres);
			} else if (commande.equals("listSites")){
				serviceL.process(commande,parametres);
			} else if (commande.equals("initSites")){
				serviceI.process(commande,parametres);
				return "";
			}
		}
		return "";		
	}
	
}
