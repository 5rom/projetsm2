package tiw5.modele;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;

/**
 * Représente un cd avec un titre et un ensemble de pistes.
 * @author Emmanuel Coquery <emmanuel.coquery@liris.cnrs.fr>
 */

@Entity
public class Album {

	/** 
	 * Un identifiant unique pour chaque cd
	 */
	@Id
	@GeneratedValue
	private long id;
	
	
	/**
	 * Le titre du cd
	 */
	@Column (name="TITRE")
	private String titre;
	

	/**
	 * La liste des plages du cd.
	 */
	//ArrayList plutot que liste?
	  @ElementCollection
	  @CollectionTable(
	        name="PISTE",
	        joinColumns=@JoinColumn(name="ALBUM_ID")
	  )
	private List<Piste> pistes;
	
	/**
	 * Créée un nouveau cd ayant pour titre l'argument.
	 * @param titre Le titre du cd
	 */
	public Album(String titre) {
		this.titre = titre;
		this.pistes = new ArrayList<Piste>();
	}
	
	/**
	 * Le titre du cd.
	 * @return Le titre du cd.
	 */
	public String getTitre() {
		return titre;
	}
	
	/**
	 * Change le titre du cd
	 * @param titre 
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	/**
	 * L'identifiant du cd.
	 * @return
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Change l'identifiant du cd. Ne devrait être changé que si le cd n'avait
	 * pas d'identifiant auparavant.
	 * @param id l'identifiant du cd
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * La ieme piste du cd
	 * @param index le numéro de la piste sur le cd
	 * @return la piste
	 * @throws IndexOutOfBoundsException si le numéro ne correspond pas à 
	 * une piste.
	 */
	public Piste getPiste(int index) {
		return pistes.get(index);
	}
	
	/**
	 * Ajoute une piste à la fin du cd.
	 * @param piste la piste à ajouter
	 */
	public void addPiste(Piste piste) {
		pistes.add(piste);
	}
	
	/**
	 * Insère la piste à l'index spécifié. 
	 * Les pistes apparaissant plus loin voient leur numéro décalé.
	 * @param piste la piste à insérer.
	 * @param index le numéro que prendra la piste un fois insérée.
	 */
	public void insertPiste(Piste piste, int index) {
		pistes.add(index, piste);
	}
	
	/**
	 * Supprime une piste de la liste des pistes
	 * @param piste
	 */
	public void removePiste(Piste piste) {
		pistes.remove(piste);
	}
	
	/**
	 * Supprime une piste de la liste des pistes
	 * @param index le numéro de la piste à supprimer.
	 */
	public void removePiste(int index) {
		pistes.remove(index);
	}
	
	/**
	 * Le nombre de pistes du cd
	 * @return le nombre de pistes du cd
	 */
	public int getNbPistes() {
		return pistes.size();
	}
	
}
