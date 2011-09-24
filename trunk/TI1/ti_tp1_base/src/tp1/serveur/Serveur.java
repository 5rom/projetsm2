package tp1.serveur;

import java.util.HashMap;

/**
 * Interface Serveur
 * Permettra l'implementation future de la methode de
 * service (traitement des requetes client)
 * @author D. CRESCENCE et S. FAURE
 *
 */
public interface Serveur {
	public void traiteRequete(String commande, HashMap<String, String> parametres);
}
