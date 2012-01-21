package panier;
import java.util.HashMap;

import fr.univ_lyon1.master_info.m2ti.tiw5.services.CDCataloguePortType;
import fr.univ_lyon1.master_info.m2ti.tiw5.services.CDCatalogueService;


public class Panier {

	// Liste de couples AlbumID, Qte
    private HashMap<Long,Integer> listeAlbumsQte;


	public Panier(HashMap<Long, Integer> listeAlbumsQte) {
		this.listeAlbumsQte = listeAlbumsQte;
	}

	public Panier() {
		this.listeAlbumsQte = new HashMap<Long, Integer>();
	}


	public HashMap<Long, Integer> getListeAlbumsQte() {
		return listeAlbumsQte;
	}

	public void setListeAlbumsQte(HashMap<Long, Integer> listeAlbumsQte) {
		this.listeAlbumsQte = listeAlbumsQte;
	}
	

	public void addAlbumPanier(long l, int qte){
		this.listeAlbumsQte.put(l, qte);
	}
	
	public void deleteAlbumPanier(long id, int qte){
		this.listeAlbumsQte.put(id, qte);
	}	
	
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