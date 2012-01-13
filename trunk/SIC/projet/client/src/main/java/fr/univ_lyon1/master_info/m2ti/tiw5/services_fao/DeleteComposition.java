
package fr.univ_lyon1.master_info.m2ti.tiw5.services_fao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pmajeur" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pmineur" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pmajeur",
    "pmineur"
})
@XmlRootElement(name = "deleteComposition")
public class DeleteComposition {

    @XmlElement(required = true)
    protected String pmajeur;
    @XmlElement(required = true)
    protected String pmineur;

    /**
     * Gets the value of the pmajeur property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPmajeur() {
        return pmajeur;
    }

    /**
     * Sets the value of the pmajeur property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPmajeur(String value) {
        this.pmajeur = value;
    }

    /**
     * Gets the value of the pmineur property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPmineur() {
        return pmineur;
    }

    /**
     * Sets the value of the pmineur property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPmineur(String value) {
        this.pmineur = value;
    }

}
