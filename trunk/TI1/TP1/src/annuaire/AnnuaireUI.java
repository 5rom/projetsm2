package annuaire;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AnnuaireUI {

	//Variable globale (singleton)
	static Annuaire annuaire;
	
	public static void main (String argv[]) {
		//annuaire = new Annuaire("test.xml");
		SiteXMLDAO xdao = new SiteXMLDAO("test.xml");
		ArrayList<Site> sites = new ArrayList<Site>();
		annuaire = new Annuaire(sites,xdao);
        annuaire.initSites();

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
				
				annuaire.addSite(description, url);
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
				
				annuaire.removeSite(description, url);
				break;
				
			case 3:
				System.out.println("Sites actuellement connus :\n\n");
				System.out.println(annuaire.listSites());
				break;

			case 4:
				annuaire.initSites();
				System.out.println("Liste des sites reinitialisee par rapport au DAO.\n\n");
				break;

			case 5:
				return;
			}

		}
		
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
//   Code trouv� � l'URL : http://www.wellho.net/resources/ex.php4?item=j703/WellHouseInput.java
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

}
