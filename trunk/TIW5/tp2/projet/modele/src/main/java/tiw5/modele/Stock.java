package tiw5.modele;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Classe Stock
 * Classe ajoutée au modèle pour représenter un stock d'albums et montrer
 * que l'ajout du nouveau service de gestion d'un entrepôt d'albums fonctionne
 * Dans un souci de simplicité, on n'ajoute pas de dépendance vers
 * la classe Album ce qui fait que les tables Album et Stock ne sont pas liées
 * par une relation. Donc l'id d'album de l'entité stock bien que représentant
 * un identifiant d'album tel que le champ id de la table album n'est pas une
 * clé étrangère au sens propre (pas de contrainte).
 * @author David CRESCENCE <crescence.david@gmail.com> et Sébastien FAURE <sebastien.faure3@gmail.com>
 * UCBL M2TI 2011-2012 
 */
@Entity
public class Stock {
	
	/**
	 * Identifiant de l'album dont on veut connaitre la quantité
	 */
	@Id
	private long albumID;
	
	/**
	 * Quantité de l'album ayant pour id album id
	 */
	private long quantite;
	
	/**
	 * Constructeur à utiliser
	 * @param album L'id de l'album a stocker
	 * @param qte La quantité de cd de cet album a stocker
	 */
	public Stock(long album, long qte) {
		this.albumID = album;
		this.quantite = qte;
	}
	
	/**
	 * Constructeur par défaut : vide
	 */
	public Stock() {
	}	
	
	/**
	 * Modifieur de l'identifiant de l'album
	 * @param id Le nouvel id
	 */
	public void setAlbumId(long id){
		this.albumID=id;
	}

	/**
	 * Accesseur de l'identifiant de l'album
	 * @return L'id d'album stocké
	 */
	public long getAlbumId(){
		return albumID;
	}	
	
	/**
	 * Modifieur de la quantité de cd en stock pour cet album
	 * @param qte La nouveau nombre de cd en stock pour cet album
	 */
	public void setQuantite(long qte){
		this.quantite=qte;
	}	
	
	/**
	 * Accesseur de la quantité de l'album
	 * @return La nombre de cd en stock pour cet album
	 */
	public long getQuantite(){
		return quantite;
	}		
	
}
