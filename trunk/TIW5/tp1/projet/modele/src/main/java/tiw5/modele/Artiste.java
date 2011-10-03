package tiw5.modele;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class Artiste {

	private static long instanceCount = 0;	
	
	/**
	 * Identifiant de l'artiste
	 */
	@Id
	@Column(name="ID")
	@GeneratedValue()
	@XmlAttribute(name="id")
	private long id;
	
	/**
	 * Prénom de l'artiste
	 */
	@Column (name="prenom")
	@XmlElement(name="prenom")
	private String prenom;		
	
	/**
	 * Nom de l'artiste
	 */
	@Column (name="nom")
	@XmlElement(name="nom")
	private String nom;
	
	
		
	
	/**
	 * Constructeur par défaut
	 */
	public Artiste(){
		super();
		instanceCount++;
		//setId(instanceCount);		
	}	
	
	/**
	 * Constructeur
	 * @param p prénom
	 * @param n nom
	 */
	public Artiste(String p, String n){
		prenom=p;
		nom=n;
		instanceCount++;
		//setId(instanceCount);		
	}
	
	/**
	 * Constructeur pour forcer la generation d'un identifiant (ne pas utiliser pour Persistence mais JAXB).
	 * @param p
	 * @param n
	 * @param generateID
	 */
	public Artiste(String p, String n, boolean generateID) {
		// TODO Auto-generated constructor stub
		prenom=p;
		nom=n;
		setId(instanceCount);
		instanceCount++;
	}

	/**
	 * @return l'identifiant
	 */
	public long getId() {
		return id;
	}

	/**
	 * Change l'identifiant.
	 * @param identifiant le nouvel identifiant.
	 */
	@XmlTransient
	public void setId(long identifiant) {
		this.id = identifiant;
	}
	
	/**
	 * @return le nom.
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Change le nom.
	 * @param inom le nom.
	 */
	@XmlTransient
	public void setNom(String inom) {
		this.nom = inom;
	}	
	
	/**
	 * @return le prenom.
	 */
	public String getPrenom() {
		return prenom;
	}	
	
	/**
	 * Change le prenom.
	 * @param inom le prenom.
	 */
	@XmlTransient
	public void setPrenom(String iprenom) {
		this.prenom = iprenom;
	}	
	
}
