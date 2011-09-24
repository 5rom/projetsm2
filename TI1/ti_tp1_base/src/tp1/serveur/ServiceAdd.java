package tp1.serveur;

import java.util.ArrayList;
import java.util.HashMap;

import tp1.Site;
import tp1.SiteXMLDAO;

public class ServiceAdd extends AbstractAnnuaire{

	public ServiceAdd(ArrayList<Site> sites, SiteXMLDAO xdao) {
		super(sites, xdao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String process(String commande, HashMap<String, String> parametres) {
		addSite(parametres.get("desc"), parametres.get("url"));
		return "";
	}

    private void addSite(String desc, String url) {
        Site s = new Site(desc, url, dao);

        // ajout dans la liste
        try {
            s.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // ajout dans le support de persistance
        sites.add(s);
    }

	@Override
	public void start() {
			// Affichage des informations du serveur
			System.out.println("Service d'ajout de sites démarré. "+"Objet d'accès aux données: "+dao.toString());
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
