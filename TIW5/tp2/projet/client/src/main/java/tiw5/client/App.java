package tiw5.client;

import fr.univ_lyon1.master_info.m2ti.tiw5.services.Album;
import fr.univ_lyon1.master_info.m2ti.tiw5.services.AlbumDataPortType;
import fr.univ_lyon1.master_info.m2ti.tiw5.services.AlbumDataService;


public class App 
{
	

	
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
        
        try {
        	Integer numAlbum = Integer.parseInt(args[0]);
        	AlbumDataService aDS=new AlbumDataService();
        	AlbumDataPortType aDPT=aDS.getAlbumDataPort();
        	Album a = aDPT.getAlbumDescription(numAlbum);
        		
        	
        } catch (Exception e) {
        	AlbumDataService aDS=new AlbumDataService();
        	AlbumDataPortType aDPT=aDS.getAlbumDataPort();
        	Album b=new Album();
        	aDPT.addAlbumDescription(b);
        	
        	e.printStackTrace();
        }
    }
}
