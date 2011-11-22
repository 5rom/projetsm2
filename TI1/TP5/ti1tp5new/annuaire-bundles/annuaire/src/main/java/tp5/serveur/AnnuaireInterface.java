package tp5.serveur;

import java.util.HashMap;

/**
 * Interface de l'annuaire
 * Contient la declaration de la methode de traitement propre aux services
 * @author D. CRESCENCE et S. FAURE
 *
 */
public interface AnnuaireInterface{

	/**
	 * Methode de service
	 * @param commande
	 * @param parametres
	 * @return
	 */
	String process(String commande, HashMap<String, String> parametres);

}
