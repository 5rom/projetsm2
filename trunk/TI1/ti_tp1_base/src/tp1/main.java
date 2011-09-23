package tp1;
import tp1.client.Client;
import tp1.serveur.ServeurImpl;


public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServeurImpl serv = new ServeurImpl();
		Client cli = new Client(serv);
		
		// Retiré volontairement à la partie 2 car dangereux
		// d'envoyer la reference de l'annuaire du serveur à un client!
		//cli.setServer(serv);
		
		
		// Interface = aucun champs.
		
		// Classe abstraite = classe non instanciable. Pour récupérer des propriétés et méthodes à l'héritage.
	}

}
