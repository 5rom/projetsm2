package serveur;
import java.util.ArrayList;

import org.picocontainer.*;

import annuaire.Annuaire;
import annuaire.SiteXMLDAO;

public class Serveur {

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
		

		
	}
}
