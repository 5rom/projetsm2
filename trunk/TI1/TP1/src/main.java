import client.Client;
import serveur.Serveur;


public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Serveur serv = new Serveur();
		Client cli = new Client();
		
		// Retir� volontairement � la partie 2 car dangereux
		// d'envoyer la reference de l'annuaire du serveur � un client!
		//cli.setAnnuaire(serv.getAnnuaire());
	}

}
