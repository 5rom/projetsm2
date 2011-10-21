package tiw5.client;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.univ_lyon1.master_info.m2ti.tiw5.services.Album;
import fr.univ_lyon1.master_info.m2ti.tiw5.services.AlbumDataPortType;
import fr.univ_lyon1.master_info.m2ti.tiw5.services.AlbumDataService;

public class App 
{
	
	private static final Logger log = LoggerFactory.getLogger(App.class);
	
    public static void main( String[] args )
    {

        /**
         * Ici faire:
         * Utiliser les classes Java générées pour réaliser un client en ligne de
         * commande pour le service précédent. Si l'argument en ligne de commande
         * est un numéro, alors le client récupérera la description XML de
         * l'album correspondant, sinon, et argument sera un nom de fichier
         * xml contenant un descriptif d'album à insérer dans les données.
         */
        
    	if (args.length==1){
		        try {
		        	
		        	/*
		        	 * Si c'est un nombre : alors on veut recuperer un album
		        	 */
		        	Integer numAlbum = Integer.parseInt(args[0]);
		        	AlbumDataService aDS=new AlbumDataService();
		        	AlbumDataPortType aDPT=aDS.getAlbumDataPort();
		        	Album a=aDPT.getAlbumDescription(numAlbum);
		        	
		        	AlbumDescription aD=new AlbumDescription(a);
			
			        // Recuperation du contexte JAXB pour l'album créé
		            log.info("Création du contexte JAXB...");
			        JAXBContext context = JAXBContext.newInstance(aD.getClass());
			   
			        // Instanciation du marshaller (objet faisant la generation du XML en fonction de nos annotations dans les classes metiers)
			        log.info("Création du marshaller JAXB...");
			        Marshaller marshaller = context.createMarshaller();
			   
			        // Formatage de la sortie
			        log.info("Formatage de la sortie XML...");
			        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			        StringWriter sw = new StringWriter();
		
			        // Marshalling de l'objet en XML
			        log.info("Lancement du marshalling JAXB...");
			        marshaller.marshal(aD, sw);
			   
			        // Affichage du XML produit
			        log.info("Description XML de l'album "+numAlbum+":");
			        log.info(sw.toString());        	
	        	
	        } catch (Exception e) {
	        	if (e.getClass().getName()=="java.lang.NumberFormatException"){
		        	/*
		        	 * Sinon c'est un chemin de fichier xml : alors on veut ajouter l'album qui y est décrit
		        	 */
		        	try {
			        	AlbumDataService aDS=new AlbumDataService();
			        	AlbumDataPortType aDPT=aDS.getAlbumDataPort();
			        	
				        // Recuperation du contexte JAXB pour l'album créé
			            log.info("Création du contexte JAXB...");
				        JAXBContext context = JAXBContext.newInstance(AlbumDescription.class);
				   
				        // Instanciation du marshaller (objet faisant la generation du XML en fonction de nos annotations dans les classes metiers)
				        log.info("Création du unmarshaller JAXB...");
				        Unmarshaller un = context.createUnmarshaller();
		
				        log.info("Désérialisation...");
			        	AlbumDescription aD = (AlbumDescription)un.unmarshal(new File(args[0]));
		
			        	log.info("Ajout de l'album créé dans la base...");
			        	aDPT.addAlbumDescription(aD.getAlbum());
		        	
		        	} catch (Exception ex){
		        		log.error("Erreur d'ajout d'album depuis un fichier xml!");
		        		ex.printStackTrace();
		        	}
	        	} else {
	        		log.error("Usage: java App [idAlbum|xmlAlbumDescriptionFilePath]");
	        	}
	        }
    	} else {
    		log.error("Usage: java App [idAlbum|xmlAlbumDescriptionFilePath]");
    	}
    }
}
