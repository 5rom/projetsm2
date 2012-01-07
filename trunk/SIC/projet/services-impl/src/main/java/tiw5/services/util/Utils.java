package tiw5.services.util;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;





public class Utils {/*
	public static Boolean checkAuthenticationFromXMLFile(String filePath, UtilisateurID userID){
		Boolean found=false;
		try {
        	 ListeUtilisateurID lUID=new ListeUtilisateurID();
        	
	        // Recuperation du contexte JAXB pour l'album créé
	        JAXBContext context = JAXBContext.newInstance(ListeUtilisateurID.class);
	   
	        // Instanciation d' l'unmarshaller (objet désérialiseur construisant l'objet a partir de sa description en XML)
	        Unmarshaller un = context.createUnmarshaller();

	        // Désérialisation
	        lUID = (ListeUtilisateurID)un.unmarshal(new File(filePath));

        	// Controle
        	found=lUID.isCorrectUser(userID);

		} catch (Exception ex){
    		// Le fichier n'existe pas ou est incorrect
    		ex.printStackTrace();
    	}
    	return found;
	}	
	
	public static Boolean checkCanEditAlbumFromXMLFile(String filePath, UtilisateurID userID){
		Boolean canEdit=false;
		try {
        	 ListeUtilisateurID lUID=new ListeUtilisateurID();
        	
        	 JAXBContext context = JAXBContext.newInstance(ListeUtilisateurID.class);
	   
	        // Instanciation d' l'unmarshaller (objet désérialiseur construisant l'objet a partir de sa description en XML)
	        Unmarshaller un = context.createUnmarshaller();

	        // Désérialisation
	        lUID = (ListeUtilisateurID)un.unmarshal(new File(filePath));

	        // Controle
        	canEdit=lUID.canUserEditAlbum(userID);
        	
    	} catch (Exception ex){
    		// Le fichier n'existe pas ou est incorrect
    		ex.printStackTrace();
    	}
    	return canEdit;
	}		*/
}
