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
		
		// Retir� volontairement � la partie 2 car dangereux
		// d'envoyer la reference de l'annuaire du serveur � un client!
		//cli.setServer(serv);
		
		
		// Interface = aucun champs.
		
		// Classe abstraite = classe non instanciable. Pour r�cup�rer des propri�t�s et m�thodes � l'h�ritage.
	}

}
