package tp1.serveur;

import java.util.HashMap;

public interface AnnuaireInterface {

	/**
	 * Methode de service
	 * @param commande
	 * @param parametres
	 * @return
	 */
	String process(String commande, HashMap<String, String> parametres);

}
