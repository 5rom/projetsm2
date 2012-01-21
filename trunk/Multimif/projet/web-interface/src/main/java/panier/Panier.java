package panier;
import java.util.HashMap;


public class Panier {

	// Liste de couples AlbumID, Qte
    HashMap<Integer,Integer> listeAlbumsQte;


	public Panier(HashMap<Integer, Integer> listeAlbumsQte) {
		this.listeAlbumsQte = listeAlbumsQte;
	}

	public Panier() {
		this.listeAlbumsQte = new HashMap<Integer, Integer>();
	}


	public HashMap<Integer, Integer> getListeAlbumsQte() {
		return listeAlbumsQte;
	}

	public void setListeAlbumsQte(HashMap<Integer, Integer> listeAlbumsQte) {
		this.listeAlbumsQte = listeAlbumsQte;
	}
	

	public void addAlbumPanier(int id, int qte){
		this.listeAlbumsQte.put(id, qte);
	}
	
	public void deleteAlbumPanier(int id, int qte){
		this.listeAlbumsQte.put(id, qte);
	}	
	
	public void emptyPanier(){
		this.listeAlbumsQte.clear();
	}		
    
}