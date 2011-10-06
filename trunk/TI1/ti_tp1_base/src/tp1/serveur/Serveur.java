package tp1.serveur;

import java.util.HashMap;

/**
 * Interface Serveur
 * Permettra l'implementation future de la méthode de
 * service (traitement des requetes client)
 * @author D. CRESCENCE et S. FAURE
 *
 */
public interface Serveur {
	/**
	 * Traine une comande envoyée au serveur via l'aiguillage de requete vers l'annuaire
	 * @param commande
	 * @param parametres
	 */
	public void traiteRequete(String commande, HashMap<String, String> parametres);
}
