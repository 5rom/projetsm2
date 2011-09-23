package tp1.serveur;

import java.util.HashMap;

public interface Serveur {
	public String traiteRequete(String commande, HashMap<String, String> parametres);
	public Annuaire getAnnuaire();
}
