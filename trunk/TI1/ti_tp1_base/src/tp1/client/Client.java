package tp1.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

//import tp1.serveur.Annuaire;
import tp1.serveur.Serveur;

/**
 * Classe Client
 * IHM cote client permettant l'envoi de requetes au serveur
 * @author D. CRESCENCE et S. FAURE
 *
 */
public class Client {	
	
	// La reference du serveur (interface seulement : donc API)
	Serveur serv;
	
	public Client(Serveur servI){
        this.setServer(servI);
		String description = null;
		String url = null;
		
		for (;;) {
			menu();
			int c = new String (readLine()).charAt(0) - '0';
			switch (c){
			case 1:
				try {
					System.out.print("Description : ");
					description = readLine();
					//System.in.read(d);
					System.out.print("URL : ");
					url = readLine();
					//System.in.read(u);
				} catch (Exception e) {
					e.printStackTrace();
				}
				//annuaire.addSite(description, url);
				HashMap<String, String> hm = new HashMap<String, String>();
				hm.put("desc", description);
				hm.put("url", url);
				System.out.println("Ajout du site :"+description+" "+url);
				serv.traiteRequete("addSite", hm);	
				break;

			case 2:
				try {
					System.out.print("Description : ");
					description = readLine();
					//System.in.read(d);
					System.out.print("URL : ");
					url = readLine();
					//System.in.read(u);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//annuaire.removeSite(description, url);
				hm = new HashMap<String, String>();
				hm.put("desc", description);
				hm.put("url", url);
				serv.traiteRequete("removeSite", hm);
				break;
				
			case 3:
				System.out.println("Sites actuellement connus :\n\n");
				//System.out.println(annuaire.listSites());
				serv.traiteRequete("listSites", null);
				break;

			case 4:
				serv.traiteRequete("initSites",null);
				System.out.println("Liste des sites reinitialisee par rapport au DAO.\n\n");
				break;

			case 5:
				return;
			}

		}
		
	}
	
	
	/**
	 * Desormais le client utilise l'API du serveur
	 * pour manipuler l'annuaire.
	 */
	
    @SuppressWarnings("unused")
	private void addSite(String desc, String url) {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("desc", desc);
		hm.put("url", url);	
        serv.traiteRequete("addSite", hm);
    }
    
    @SuppressWarnings("unused")
    private void removeSite(String desc, String url) {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("desc", desc);
		hm.put("url", url);	
        serv.traiteRequete("removeSite", hm);  	
    }
    
    @SuppressWarnings("unused")
    private void listSites() {
        serv.traiteRequete("listSites", null);
    }
    

	public static void menu() {
		System.out.println("Menu\n\n");
		System.out.println("1)\tRajouter un site\n");
		System.out.println("2)\tSupprimer un site un site\n");
		System.out.println("3)\tLister les sites\n");
        System.out.println("4)\tReinitialiser les sites\n");
        System.out.println("5)\tQuitter\n");
	}

//	 ---------------------------------------------
//   Code trouvé à l'URL : http://www.wellho.net/resources/ex.php4?item=j703/WellHouseInput.java
	   public static String readLine()
	      {
	      BufferedReader standard = new BufferedReader(
	           new InputStreamReader(System.in));
	      try{
	         String inline = standard.readLine();
	         return inline;
	         }
	      catch (Exception e)
	         {
	         return ("data entry error");
	         }
	      }

	public void setServer(Serveur servI) {
		// TODO Auto-generated method stub
		serv=servI;
	}

}
