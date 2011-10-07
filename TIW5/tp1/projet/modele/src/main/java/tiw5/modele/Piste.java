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
 * Pour les premieres question : pas une entite car simple groupe de valeurs
 * Ensuite (partie 3) : devient une entite car contient une liste d'Artistes (eux mêmes entités)
 */
//@Embeddable
@Entity
public class Piste {

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
	 * Le titre de la piste.
	 */
	@XmlElement(namespace="http://master-info.univ-lyon1.fr/M2TI/TIW5/ventecd")
	@Column (name="titre")
	private String titre;
	
	/**
	 * La durée de la piste en secondes.
	 */
	@XmlAttribute(name="duree")
	@Column(name="duree")
	private int duree;
	
	/**
	 * La liste des artistes de la piste.
	 */
	//@ElementCollection
	@XmlElement(name="artiste", namespace="http://master-info.univ-lyon1.fr/M2TI/TIW5/ventecd")
	@ManyToMany (cascade = { CascadeType.PERSIST })
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
