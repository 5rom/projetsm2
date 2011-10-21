package tiw5.modele;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class Stock {
	
	@Id
	private long albumID;
	
	private long quantite;
	
	public Stock(long album, long qte) {
		this.albumID = album;
		this.quantite = qte;
	}
	
	public Stock() {
	}	
	
	/**
	 * Modifieur de l'identifiant de l'album
	 * @param id
	 */
	public void setAlbumId(long id){
		this.albumID=id;
	}

	/**
	 * Accesseur de l'identifiant de l'album
	 */
	public long getAlbumId(){
		return albumID;
	}	
	
	/**
	 * Modifieur de la quantité de l'album
	 * @param qte
	 */
	public void setQuantite(long qte){
		this.quantite=qte;
	}	
	
	/**
	 * Accesseur de la quantité de l'album
	 */
	public long getQuantite(){
		return quantite;
	}		
	
}
