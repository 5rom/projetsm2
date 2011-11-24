package tiw5.user;

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

@XmlRootElement(name = "utilisateurID", namespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services")
public class UtilisateurID {

    @XmlElement(namespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services" , name="user")
	private String user; // Login
    @XmlElement(namespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", name="password")
	private String password; // Password
    @XmlElement(namespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", name="canEditAlbum")
	private Boolean canEditAlbum; // Cet utilisateur a, ou non, le droit de modifier un album    
    
    public UtilisateurID(String userValue, String passwordValue) {
    	user=userValue;
    	password=passwordValue;
	}
    
    public UtilisateurID() {

	}    

	/**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @XmlTransient
    public void setUser(String value) {
        this.user = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @XmlTransient
    public void setPassword(String value) {
        this.password = value;
    }    
    
    /**
     * Gets the value of the canEditAlbum property.
     * 
     * @return
     *     possible object is
     *     {@link boolean }
     *     
     */
    public boolean getCanEditAlbum() {
        return canEditAlbum;
    }

    /**
     * Sets the value of the canEditALbum property.
     * 
     * @param value
     *     allowed object is
     *     {@link boolean }
     *     
     */
    @XmlTransient
    public void setCanEditAlbum(Boolean value) {
        this.canEditAlbum = value;
    }
    
}
