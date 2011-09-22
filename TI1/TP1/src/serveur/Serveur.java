package serveur;
import java.util.ArrayList;
import java.util.HashMap;

import org.picocontainer.*;

import annuaire.Annuaire;
import annuaire.Site;
import annuaire.SiteXMLDAO;

/**
 * Classe Serveur
 * Implémentation du serveur
 * @author D. CRESCENCE et S. FAURE
 *
 */
public class Serveur {

	// L'annuaire du serveur
	private Annuaire annu;
	
	// Constructeur
	public Serveur(){
		
		// Instanciation du ContainerBuilder (DEPRECIE?)
		//ContainerBuilder cB = new ContainerBuilder();

		// Instanciation du picocontainer
		// DefaultPicoContainer pico = cB.build();
		DefaultPicoContainer pico = new DefaultPicoContainer();
		
		
		// Ajout de quatre composants ayants des dependances
		pico.addComponent(Annuaire.class);
		pico.addComponent(ArrayList.class);
		pico.addComponent(SiteXMLDAO.class);
		pico.addComponent(String.class);
		
		
		// Création de l'annuaire
		SiteXMLDAO xdao = new SiteXMLDAO("test.xml");
		ArrayList<Site> sites = new ArrayList<Site>();
		annu = new Annuaire(sites,xdao);
		
		//Appel de la méthode start() de l'annuaire
		annu.start();
	}
	
	// Accesseur sur l'annuaire
	public Annuaire getAnnuaire(){
		return annu;
	}
	
	public String traiteRequete(String commande, HashMap<String, String> parametres){
		return null;
	}
	
}
