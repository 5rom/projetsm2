package tp5.serveur;

import java.util.HashMap;

public interface Serveur {
	public String traiteRequete(String commande, HashMap<String, String> parametres);
}
