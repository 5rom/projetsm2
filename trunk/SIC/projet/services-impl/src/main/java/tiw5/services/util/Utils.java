package tiw5.services.util;

import java.io.File;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import tiw5.modele.user.ListeUtilisateurID;
import tiw5.modele.user.UtilisateurID;

/**
 * Classe Utils.java
 * Contient les méthodes utilitaires pour les handlers 
 * d'authentification et controle d'acces
 * @author David CRESCENCE <crescence.david@gmail.com> et Sébastien FAURE <sebastien.faure3@gmail.com>
 * UCBL M2TI 2011-2012 
 */
public class Utils {
		
	/**
	 * Le logger
	 */
	private static Logger log = Logger.getLogger("Utils");
		
	/**
	 * Methode checkAuthenticationFromXMLFile : Controle l'authentification d'un utilisateur depuis une liste d'identifiants XML
	 * @param filePath : chemin du fichier XML d'identifiants
	 * @param userID : un utilisateur
	 * @return Booleen: vrai l'utilisateur s'est correctement authentifie, faux sinon
	 */
	public static Boolean checkAuthenticationFromXMLFile(String filePath, UtilisateurID userID){
		Boolean found=false;
		try {
        	 ListeUtilisateurID lUID=new ListeUtilisateurID();
        	
	        // Recuperation du contexte JAXB pour la liste d'UtilisateurID créée
	        JAXBContext context = JAXBContext.newInstance(ListeUtilisateurID.class);
	   
	        // Instanciation d' l'unmarshaller (objet désérialiseur construisant l'objet a partir de sa description en XML)
	        Unmarshaller un = context.createUnmarshaller();

	        // Désérialisation
	        lUID = (ListeUtilisateurID)un.unmarshal(new File(filePath));

        	// Controle d'authentification (login et password)
        	found=lUID.isCorrectUser(userID);

		} catch (Exception ex){
    		log.warning("Le fichier XML "+filePath+" n'existe pas ou est incorrect");
    	}
    	return found;
	}	
	
	/**
	 * Methode checkCanEditAlbumFromXMLFile : Controle le droit de modification des albums d'un utilisateur depuis une liste d'identifiants XML
	 * @param filePath : chemin du fichier XML d'identifiants
	 * @param userID : un utilisateur
	 * @return Booleen: vrai l'utilisateur dispose de ce droit, faux sinon
	 */	
	public static Boolean checkCanEditAlbumFromXMLFile(String filePath, UtilisateurID userID){
		Boolean canEdit=false;
		try {
        	 ListeUtilisateurID lUID=new ListeUtilisateurID();
        	 
 	        // Recuperation du contexte JAXB pour la liste d'UtilisateurID créée
        	 JAXBContext context = JAXBContext.newInstance(ListeUtilisateurID.class);
	   
	        // Instanciation d' l'unmarshaller (objet désérialiseur construisant l'objet a partir de sa description en XML)
	        Unmarshaller un = context.createUnmarshaller();

	        // Désérialisation
	        lUID = (ListeUtilisateurID)un.unmarshal(new File(filePath));

	        // Controle de droit de modification des albums
        	canEdit=lUID.canUserEditAlbum(userID);
        	
    	} catch (Exception ex){
    		log.warning("Le fichier XML "+filePath+" n'existe pas ou est incorrect");
    	}
    	return canEdit;
	}		
}
