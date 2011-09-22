package client;

import java.util.HashMap;
import java.util.Iterator;

import serveur.Serveur;

import annuaire.Annuaire;
import annuaire.Site;

public class Client {

	// La reference du serveur (voir si necessaire de le garder)
	Serveur serv;
	
	public Client(){
		
	}
	
	/**
	 * Enlevé car le client ne doit pas avoir
	 * accès à la référence de l'annuaire du serveur,
	 * dangereux!
	 * @param annuaire
	 */
	//Annuaire annu;
	// Recupération de l'annuaire
	//public void setAnnuaire(Annuaire annuaire){
	//	this.annu=annuaire;
	//}
	
	
	/**
	 * Desormais le client utilise l'API du serveur
	 * pour manipuler l'annuaire.
	 */
    private void addSite(String desc, String url) {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("desc", desc);
		hm.put("url", url);	
        serv.traiteRequete("addSite", hm);
    }

    private void removeSite(String desc, String url) {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("desc", desc);
		hm.put("url", url);	
        serv.traiteRequete("removeSite", hm);  	
    }

    private String listSites() {
        return serv.traiteRequete("listSites", null);
    }

}
