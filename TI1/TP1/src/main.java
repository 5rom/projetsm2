import client.Client;
import serveur.Serveur;


public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Serveur serv = new Serveur();
		Client cli = new Client(serv.getAnnuaire());
	}

}
