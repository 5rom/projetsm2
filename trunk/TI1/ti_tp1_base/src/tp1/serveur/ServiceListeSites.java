package tp1.serveur;

import java.util.ArrayList;
import java.util.HashMap;

import tp1.Site;
import tp1.SiteXMLDAO;

public class ServiceListeSites extends AbstractAnnuaire {

	public ServiceListeSites(ArrayList<Site> sites, SiteXMLDAO xdao) {
		super(sites, xdao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String process(String commande, HashMap<String, String> parametres) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void start() {
		System.out.println("Service de listing des sites démarré. "+"Objet d'accès aux données: "+dao.toString());
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
