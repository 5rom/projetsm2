package tiw5.modele;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Représente un cd avec un titre et un ensemble de pistes.
 * @author Emmanuel Coquery <emmanuel.coquery@liris.cnrs.fr>
 */
@XmlRootElement
@Entity
@XmlType(name = "", propOrder = {
        "titre",
        "artistes",
        "pistes"
    })
public class Album {
	

	/**
	 * Compteur d'instance pour avoir un id cohérent pour le test de la partie 2 (au lieu d'avoir des id à 0 partout).
	 */
	@XmlTransient
	private static long instanceCount = 0;

	/**
	 * Un identifiant unique pour chaque cd
	 */
	@Id
	@Column(name="ID")
	@GeneratedValue()
	@XmlAttribute(name="id")
	private long id;
	
	
	/**
	 * Le titre du cd
	 */
	@Column (name="titre")
	@XmlElement(name="titre")
	private String titre;
	

	/**
	 * La liste des plages du cd.
	 */
	//@ElementCollection
	@OneToMany(cascade = { CascadeType.PERSIST })
	@XmlElement(name="piste")
	private List<Piste> pistes;

	/**
	 * La liste des artistes de l'album.
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST })
	@XmlElement(name="artiste")
	private List<Artiste> artistes;	
	
	/**
	 * Créée un nouveau cd ayant pour titre l'argument.
	 * @param titre Le titre du cd
	 */
	public Album(String titre) {
		this.titre = titre;
		instanceCount++;
		//setId(instanceCount);
		this.pistes = new ArrayList<Piste>();
		this.artistes = new ArrayList<Artiste>();
	}
	
	/**
	 * Constructeur par défaut
	 * @param titre Le titre du cd
	 */
	public Album(){
		super();
		instanceCount++;
		//setId(instanceCount);
	}
	
	/**
	 * Constructeur specifiant egalement l'id. Ne pas utiliser pour Persistence mais pour JAXB.
	 * @param titre
	 * @param generateID true or false (pour differencier des autres constructeurs)
	 */
	public Album(String titre, boolean generateID) {
		// TODO Auto-generated constructor stub
		this.titre = titre;
		setId(instanceCount);
		instanceCount++;
		this.pistes = new ArrayList<Piste>();
		this.artistes = new ArrayList<Artiste>();		
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
	@XmlTransient
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
	@XmlTransient
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
	
	/**
	 * Ajoute un artiste au cd.
	 * @param artiste l'artiste à ajouter
	 */
	public void addArtiste(Artiste artiste) {
		artistes.add(artiste);
	}
	
	/**
	 * Le ieme artiste du cd
	 * @param index le numéro de l'artiste sur le cd
	 * @return l'artiste
	 * @throws IndexOutOfBoundsException si le numéro ne correspond pas à 
	 * un artiste.
	 */
	public Artiste getArtiste(int index) {
		return artistes.get(index);
	}	
	
	/**
	 * Supprime un artiste de la liste des artistes
	 * @param artiste
	 */
	public void removeArtiste(Artiste artiste) {
		artistes.remove(artiste);
	}

	/**
	 * Le nombre d'artistes du cd
	 * @return le nombre d'artistes du cd
	 */
	public int getNbArtistes() {
		return artistes.size();
	}	
	
}
