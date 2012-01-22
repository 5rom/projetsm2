
package fr.univ_lyon1.master_info.m2ti.tiw5.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="pmajeur" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="pmineur" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
@XmlRootElement(name = "addComposition")
public class AddComposition {

    protected long pmajeur;
    protected long pmineur;

    /**
     * Gets the value of the pmajeur property.
     * 
     */
    public long getPmajeur() {
        return pmajeur;
    }

    /**
     * Sets the value of the pmajeur property.
     * 
     */
    public void setPmajeur(long value) {
        this.pmajeur = value;
    }

    /**
     * Gets the value of the pmineur property.
     * 
     */
    public long getPmineur() {
        return pmineur;
    }

    /**
     * Sets the value of the pmineur property.
     * 
     */
    public void setPmineur(long value) {
        this.pmineur = value;
    }

}
