package tiw5.modele.user;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;




/**
 * <p>Java class for utilisateurID complex type.
 * The user authentication information
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="utilisateurID">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://master-info.univ-lyon1.fr/M2TI/TIW5/services}user" minOccurs="0"/>
 *         &lt;element ref="{http://master-info.univ-lyon1.fr/M2TI/TIW5/services}password" minOccurs="0"/> *         
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

/**
 * Classe UtilisateurID
 * Représente les information d'authentification d'un utilisateur (identifiants et droit à modification d'albums)
 * @author David CRESCENCE <crescence.david@gmail.com> et Sébastien FAURE <sebastien.faure3@gmail.com>
 * UCBL M2TI 2011-2012 
 */
@XmlRootElement(name = "utilisateurID", namespace = ServicesImplConstants.NS)
public class UtilisateurID {

    @XmlElement(namespace = ServicesImplConstants.NS , name="user")
	private String user; // Login
    @XmlElement(namespace = ServicesImplConstants.NS, name="password")
	private String password; // Password
    @XmlElement(namespace = ServicesImplConstants.NS, name="canEditAlbum")
	private Boolean canEditAlbum; // Cet utilisateur a, ou non, le droit de modifier un album    
    
    /**
     * Constructeur
     * @param userValue
     * @param passwordValue
     */
    public UtilisateurID(String userValue, String passwordValue) {
    	user=userValue;
    	password=passwordValue;
	}
    
    /**
     * Constructeur vide pour JAXB
     */
    public UtilisateurID() {

	}    

	/**
     * Accesseur du login user
     * 
     * @return String Le login
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Modifieur de la valeur du login user.
     * 
     * @param value Le nouveau login
     *     
     */
    @XmlTransient
    public void setUser(String value) {
        this.user = value;
    }

	/**
     * Accesseur du mot de passe password
     * 
     * @return String Le mot de passe
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Modifieur de la valeur du mot de passe password
     * 
     * @param value Le nouveau mot de passe
     *     
     */
    @XmlTransient
    public void setPassword(String value) {
        this.password = value;
    }
    
	/**
     * Accesseur de la propriété canEditAlbum,
     * droit de modification des albums pour un
     * utilisateur
     * 
     * @return Boolean Le droit (vrai pour oui, faux pour non)
     *     
     */
    public boolean getCanEditAlbum() {
        return canEditAlbum;
    }

    /**
     * Modifieur de la propriété canEditAlbum,
     * droit de modification des albums pour un
     * utilisateur
     * 
     * @param value Le nouveau droit (vrai pour oui, faux pour non)
     *     
     */
    @XmlTransient
    public void setCanEditAlbum(Boolean value) {
        this.canEditAlbum = value;
    }

    /**
     * Redéfinition de la méthode equals pour pouvoir comparer 
     * deux UtilisateurID pour savoir si on parle bien du même 
     * utilisateur.
     */
    @Override
    public boolean equals(Object obj) {
        // Vérification de l'égalité des références
        if (obj==this) {
            return true;
        }
        
        // Vérification du type du paramètre
        if (obj instanceof UtilisateurID) {
            // Vérification des valeurs des attributs
        	UtilisateurID other = (UtilisateurID) obj;
            
			if ((this.getUser().equals(other.getUser())) && (this.getPassword().equals(other.getPassword()))){
				// On parle du même UtilisateurID
				return true;
			}
        }
        
        // Pas d'égalité
        return false;
    }
    
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (getUser().hashCode()+getPassword().hashCode());
	}
    
}
