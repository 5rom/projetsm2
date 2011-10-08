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

/**
 * On fait de l'album une entité (@Entity) dès la première question car :
 * - c'est plus(+) qu'un ensemble de valeurs simples
 * - contient des listes d'instances d'autres classes (pistes puis artistes)
 * - possède un id
 * 
 * @XmlRootElement car ce sera la racine de notre fichier xml (sérialisation)
 * On fixe également le namespace car sinon le marshaller ne trouvera pas
 * l'élément album dans le XSD
 * 
 * @XmlType : On l'utilise pour modifier l'ordre des sous éléments d'album
 * dans le XML, d'abord titre puis artistes puis pistes.
 */
@XmlRootElement(namespace="http://master-info.univ-lyon1.fr/M2TI/TIW5/ventecd")
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
	@XmlTransient // Comme pour les autres @XmlTransient : Pour ne pas considérer cet attribut ou méthode lors du marshalling (=Sérialisation) JAXB.
	private static long instanceCount = 0;

	/**
	 * Un identifiant unique pour chaque cd
	 */
	@Id 						// Car c'est l'identifiant unique de l'entité (équivalent clé primaire en relationnel)
	@Column(name="ID") 			// Le nom de la colonne dans la table sera "ID"
	@GeneratedValue() 			// La valeur de la clé sera gérée et auto incrémentée en base (on ne s'en occupe donc pas ici en java)
	@XmlAttribute(name="id") 	// Information pour le marshalling : on veut que id soit un attribut de l'élément album
	private long id;
	
	/**
	 * Le titre du cd
	 */
	@Column (name="titre")		// Le nom de la colonne dans la table sera "titre"
	@XmlElement(name="titre", namespace="http://master-info.univ-lyon1.fr/M2TI/TIW5/ventecd") // Le nom de l'élément XML sera "titre" et on donne bien le namespace
	private String titre;
	

	/**
	 * La liste des plages du cd.
	 */
	//@ElementCollection // Pour les deux premières questions on utilise @ElementCollection car les pistes ne sont pas une entités (car simple ensemble de valeurs de types simples : titre, duree). Les pistes sont donc "embarquées" comme une collection dans Album
	@OneToMany(cascade = { CascadeType.PERSIST }) // Pour la suite comme piste devient une entité (cf. raisons dans Piste.java), alors on ne peut plus utiliser @ElementCollection. C'est désormais une relation de type OneToMany car un album peut posseder plusieurs pistes. On part du principe qu'une piste appartient à un et un seul album.CascadeType.PERSIST est ajouté pour rendre les objets persistants en cascade : il suffit alors d'appeler persist sur l'album (et donc pas sur piste et artiste).
	@XmlElement(name="piste", namespace="http://master-info.univ-lyon1.fr/M2TI/TIW5/ventecd") // Le nom des éléments XML pistes  sera "piste" (pour convenir au premier XSD) et on donne bien le namespace
	private List<Piste> pistes;

	/**
	 * La liste des artistes de l'album.
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST }) // Partie 3 du TP : Ajout des artistes. Artiste est une entité (cf. raisons dans Artiste.java). On choisit une relation de type ManyToMany car un album peut avoir été fait par plusieurs artistes et les artistes peuvent faire plusieurs albums.
	@XmlElement(name="artiste", namespace="http://master-info.univ-lyon1.fr/M2TI/TIW5/ventecd") // Le nom des éléments XML artistes  sera "artiste" (pour convenir au nouveau XSD) et on donne bien le namespace
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
	 * Constructeur specifiant egalement l'id. Ne pas utiliser pour Persistence mais pour le test partie 2 JAXB (sinon tous les id sont à 0).
	 * @param titre
	 * @param generateID true or false (pour differencier des autres constructeurs)
	 */
	public Album(String titre, boolean generateID) {
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
