package tp1.serveur;
import java.util.ArrayList;
import java.util.HashMap;

import org.picocontainer.*;

import tp1.Site;
import tp1.SiteXMLDAO;

/**
 * Classe Serveur
 * Implémentation du serveur
 * @author D. CRESCENCE et S. FAURE
 *
 */
public class ServeurImpl implements Serveur {

	// L'annuaire du serveur
	private Annuaire annu;
	
	// Constructeur
	public ServeurImpl(){
		
		// Instanciation du ContainerBuilder (DEPRECIE?)
		//ContainerBuilder cB = new ContainerBuilder();

		// Instanciation du picocontainer
		// DefaultPicoContainer pico = cB.build();
		DefaultPicoContainer pico = new DefaultPicoContainer();
		
		
		// Ajout de quatre composants ayants des dependances
		pico.addComponent(Annuaire.class);
		pico.addComponent(ArrayList.class);
		pico.addComponent(SiteXMLDAO.class);
		pico.addComponent(new String("test.xml"));
		
		
		// Création de l'annuaire
		//SiteXMLDAO xdao = new SiteXMLDAO("test.xml");
		//ArrayList<Site> sites = new ArrayList<Site>();
		annu = pico.getComponent(Annuaire.class);
		//Appel de la méthode start() de l'annuaire
		annu.start();
		
		// Ensuite l'arraylist sera remplacee par un sous conteneur.
	}
	
	@Override
	// Accesseur sur l'annuaire
	public Annuaire getAnnuaire(){
		return annu;
	}
	
	@Override
	public String traiteRequete(String commande, HashMap<String, String> parametres){
		return annu.process(commande, parametres);
	}
	
}
