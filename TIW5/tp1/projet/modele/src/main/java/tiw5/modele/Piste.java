package tiw5.modele;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Classe représentant une piste. 
 * Remarque: on ne souhaite pas faire des pistes des entités à part entière.
 * @author Emmanuel Coquery <emmanuel.coquery@liris.cnrs.fr>
 */

/**
 * Pas une entite car simple groupe de valeurs
 */
@Embeddable
@XmlRootElement(name="piste")
public class Piste {

	/**
	 * Le titre de la piste.
	 */
	@XmlElement(name="titre")
	@Column (name="titre")
	private String titre;
	
	/**
	 * La durée de la piste en secondes.
	 */
	@XmlElement(name="duree")
	@Column(name="duree")
	private int duree;
	
	
	/**
	 * Créée une piste.
	 * @param titre le titre de la piste.
	 * @param duree la durée de la piste en secondes.
	 */
	public Piste(String titre, int duree) {
		this.titre = titre;
		this.duree = duree;
	}
	
	public Piste(){
		super();
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
