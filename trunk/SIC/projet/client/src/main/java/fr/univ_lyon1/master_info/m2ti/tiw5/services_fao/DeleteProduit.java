
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
 *         &lt;element name="pnum" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "pnum"
})
@XmlRootElement(name = "deleteProduit")
public class DeleteProduit {

    @XmlElement(required = true)
    protected String pnum;

    /**
     * Gets the value of the pnum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPnum() {
        return pnum;
    }

    /**
     * Sets the value of the pnum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPnum(String value) {
        this.pnum = value;
    }

}
