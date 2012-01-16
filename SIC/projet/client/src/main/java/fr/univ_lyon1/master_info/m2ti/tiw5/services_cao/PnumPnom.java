
package fr.univ_lyon1.master_info.m2ti.tiw5.services_cao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Pnum_Pnom complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Pnum_Pnom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pnum" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="pnom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Pnum_Pnom", propOrder = {
    "pnum",
    "pnom"
})
public class PnumPnom {

    protected long pnum;
    @XmlElement(required = true)
    protected String pnom;

    /**
     * Gets the value of the pnum property.
     * 
     */
    public long getPnum() {
        return pnum;
    }

    /**
     * Sets the value of the pnum property.
     * 
     */
    public void setPnum(long value) {
        this.pnum = value;
    }

    /**
     * Gets the value of the pnom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPnom() {
        return pnom;
    }

    /**
     * Sets the value of the pnom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPnom(String value) {
        this.pnom = value;
    }

}
