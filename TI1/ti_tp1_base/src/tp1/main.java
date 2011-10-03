package tp1;
import java.util.HashMap;

import tp1.client.Client;
import tp1.serveur.ServeurImpl;


public class main {

	/**
	 * Le main du projet : Instancie Serveur et Client
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SiteDAO dao = new SiteXMLDAO("test.xml");
		HashMap<String, Object> listedao = new HashMap<String,Object>(); 
		listedao.put("SiteDAO", dao);
		ServeurImpl serv = new ServeurImpl(listedao);

		
		// Retiré volontairement à la partie 2 car dangereux
		// d'envoyer la reference de l'annuaire du serveur à un client!
		//cli.setServer(serv);
		
		// Au lieu de cela on passe le ServeurImpl serv au client
		// en sachant que le client, lui, ne connait que l'API
		// du Serveur car il attend une interace. Donc il ne pourra appeler
		// aux méthodes de l'interface.
		new Client(serv);
		

	}

}
