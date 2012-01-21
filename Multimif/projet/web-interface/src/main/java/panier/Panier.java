package panier;
import java.util.HashMap;


public class Panier {

	// Liste de couples AlbumID, Qte
    HashMap<Long,Integer> listeAlbumsQte;


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
    
}