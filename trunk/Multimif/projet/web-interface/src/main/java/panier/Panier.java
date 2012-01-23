package panier;
import java.util.HashMap;

import fr.univ_lyon1.master_info.m2ti.tiw5.services.CDCataloguePortType;
import fr.univ_lyon1.master_info.m2ti.tiw5.services.CDCatalogueService;

/**
 * Classe Panier
 * Represente le panier d'un client
 * @author Sebastien FAURE, David CRESCENCE, William VIVET-GROS, Thien Phuc Doan
 */
public class Panier {

	/**
	 * Le panier contient une liste d'albums avec leur quantite
	 */
    private HashMap<Long,Integer> listeAlbumsQte;


	/**
	 * Constructeur panier plein
	 */
	public Panier(HashMap<Long, Integer> listeAlbumsQte) {
		this.listeAlbumsQte = listeAlbumsQte;
	}

	/**
	 * Constructeur panier vide
	 */
	public Panier() {
		this.listeAlbumsQte = new HashMap<Long, Integer>();
	}

	/**
	 * Getter liste d'albums du panier
	 * @return
	 */
	public HashMap<Long, Integer> getListeAlbumsQte() {
		return listeAlbumsQte;
	}

	/**
	 * Setter de la liste
	 * @param listeAlbumsQte
	 */
	public void setListeAlbumsQte(HashMap<Long, Integer> listeAlbumsQte) {
		this.listeAlbumsQte = listeAlbumsQte;
	}
	

	/**
	 * Ajouter un album au panier
	 * @param l id de l'album
	 * @param qte quantite à ajouter
	 */
	public void addAlbumPanier(long l, int qte){
		this.listeAlbumsQte.put(l, qte);
	}
	
	/**
	 * Supprimer un album du panier
	 * @param id
	 */
	public void deleteAlbumPanier(long id){
		this.listeAlbumsQte.remove(id);
	}
	
	/**
	 * Vider le panier
	 */
	public void emptyPanier(){
		this.listeAlbumsQte.clear();
	}		
	
	/**
	 * Prix total du panier
	 * @return
	 */
	public double getPricePanier(){
		double total=0;
		for (long mapKey : listeAlbumsQte.keySet()) {
			CDCatalogueService cDCS= new CDCatalogueService();
			CDCataloguePortType cDCPT = cDCS.getCDCataloguePort();
			total=total+(cDCPT.getAlbumDescription(mapKey).getPrix()*listeAlbumsQte.get(mapKey));
		}
		return total;
	}
    
}