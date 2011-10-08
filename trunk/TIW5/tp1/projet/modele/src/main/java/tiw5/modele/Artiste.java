package tiw5.modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Classe représentant un artiste (musique). Un artiste participe à un album
 * Un artiste participe a une ou plusieurs pistes d'un album
 * @author David CRESCENCE et Sebastien FAURE (M2TI)
 * UCBL1 2011-2012 
 */
/**
 * On fait de l'Artiste une entité (@Entity) car :
 * - on veut le différencier par un id qu'on ne veut pas gérer nous même (et l'artiste peut être associé à plusieurs pistes et albums donc on a besoin d'un identifiant unique qui ne bouge pas pour l'artiste)
 */
@Entity
public class Artiste {
	/**
	 * Compteur d'instance pour avoir un id cohérent pour le test de la partie 2 (au lieu d'avoir des id à 0 partout).
	 */
	private static long instanceCount = 0;	
	
	/**
	 * Identifiant de l'artiste
	 */
	@Id							// Car c'est l'identifiant unique de l'entité (équivalent clé primaire en relationnel)
	@Column(name="ID")			// Le nom de la colonne dans la table sera "ID"
	@GeneratedValue() 			// La valeur de la clé sera gérée et auto incrémentée en base (on ne s'en occupe donc pas ici en java)
	@XmlAttribute(name="id")	// Information pour le marshalling : on veut que id soit un attribut de l'élément artiste
	private long id;
	
	/**
	 * Prénom de l'artiste
	 */
	@Column (name="prenom")		// Le nom de la colonne dans la table sera "prenom"
	@XmlElement(name="prenom", namespace="http://master-info.univ-lyon1.fr/M2TI/TIW5/ventecd") 	// Le nom de l'élément XML sera "prenom" et on donne bien le namespace
	private String prenom;		
	
	/**
	 * Nom de l'artiste
	 */
	@Column (name="nom")		// Le nom de la colonne dans la table sera "nom"
	@XmlElement(name="nom", namespace="http://master-info.univ-lyon1.fr/M2TI/TIW5/ventecd")		// Le nom de l'élément XML sera "nom" et on donne bien le namespace
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
