package tiw5.modele;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Classe représentant une piste. 
 * Remarque: on ne souhaite pas faire des pistes des entités à part entière.
 * @author Emmanuel Coquery <emmanuel.coquery@liris.cnrs.fr>
 */

/**
 * Pour les deux premières parties du TP : pas une entite car simple groupe de valeurs de types simples (string)
 * - @Embedabble : car les pistes qui ne sont pas entités sont "embarquées" dans un Album lui même Entité, dans une ElementCollection.
 * On fait de Piste une entité (@Entity) pour la 3ème partie du TP car :
 * - Une piste contient une liste d'artistes qui sont eux mêmes des entités
 * 
 * Comme on a fait le choix de faire de piste une entité dans la partie 3 du TP, on ajoute un "ID" pour la clé primaire.
 */
//@Embeddable
@Entity
public class Piste {

	/**
	 * Compteur d'instance pour avoir un id cohérent pour le test de la partie 2 (au lieu d'avoir des id à 0 partout).
	 */
	private static long instanceCount = 0;

	
	/** 
	 * Un identifiant unique pour chaque cd
	 */
	@Id							// Car c'est l'identifiant unique de l'entité (équivalent clé primaire en relationnel)
	@Column(name="ID")			// Le nom de la colonne dans la table sera "ID"
	@GeneratedValue()			// La valeur de la clé sera gérée et auto incrémentée en base (on ne s'en occupe donc pas ici en java)
	@XmlAttribute(name="id")	// Information pour le marshalling : on veut que id soit un attribut de l'élément piste
	private long id;
	
	/**
	 * Le titre de la piste.
	 */
	@XmlElement(namespace="http://master-info.univ-lyon1.fr/M2TI/TIW5/ventecd")  // On donne bien le namespace comme dans le XSD
	@Column (name="titre")		// Le nom de la colonne dans la table sera "titre"
	private String titre;
	
	/**
	 * La durée de la piste en secondes.
	 */
	@XmlAttribute(name="duree") // On fait de la duree en minutes un attribut de l'élément piste (coincide avec notre nouveau XSD)
	@Column(name="duree")		// Le nom de la colonne dans la table sera "duree"
	private int duree;
	
	/**
	 * La liste des artistes de la piste.
	 */
	@XmlElement(name="artiste", namespace="http://master-info.univ-lyon1.fr/M2TI/TIW5/ventecd")  // Le nom des éléments XML artistes sera "artiste" (pour convenir au nouveau XSD) et on donne bien le namespace
	@ManyToMany (cascade = { CascadeType.PERSIST }) // Partie 3 du TP : Ajout des artistes. Artiste est une entité (cf. raisons dans Artiste.java). On choisit une relation de type ManyToMany car une piste peut avoir été fait par plusieurs artistes et les artistes peuvent faire plusieurs pistes (ce qui est logique sur un album en général!).
	private List<Artiste> artistes;
	
	
	/**
	 * Créée une piste.
	 * @param titre le titre de la piste.
	 * @param duree la durée de la piste en secondes.
	 */
	public Piste(String titre, int duree) {
		this.titre = titre;
		this.duree = duree;
		instanceCount++;
		//setId(instanceCount);		
		this.artistes = new ArrayList<Artiste>();
	}
	
	public Piste(){
		super();
		instanceCount++;
		//setId(instanceCount);
		this.artistes = new ArrayList<Artiste>();
	}

	/**
	 * Constructeur specifiant egalement l'id. Ne pas utiliser pour Persistence mais pour JAXB.
	 * Force la génération d'un id.
	 * @param titre
	 * @param duree
	 * @param boolean
	 */
	public Piste(String titre, int duree,  boolean generateID) {

		this.titre = titre;
		this.duree = duree;
		setId(instanceCount);
		instanceCount++;
		this.artistes = new ArrayList<Artiste>();
	}

	/**
	 * L'identifiant du cd.
	 * @return
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Change l'identifiant de la piste. Ne devrait être changé que si la piste n'avait
	 * pas d'identifiant auparavant.
	 * @param id l'identifiant de la piste
	 */
	@XmlTransient
	public void setId(long id) {
		this.id = id;
	}	
	
	/**
	 * @return le titre.
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * Change le titre de la piste.
	 * @param titre le nouveau titre.
	 */
	@XmlTransient
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return la durée.
	 */
	public int getDuree() {
		return duree;
	}

	/**
	 * Change la durée de la piste.
	 * @param duree la nouvelle durée.
	 */
	@XmlTransient
	public void setDuree(int duree) {
		this.duree = duree;
	}
	
	/**
	 * Ajoute un artiste au cd.
	 * @param artiste l'artiste à ajouter
	 */
	public void addArtiste(Artiste artiste) {
		artistes.add(artiste);
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + duree;
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piste other = (Piste) obj;
		if (duree != other.duree)
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}
	
	
}
