
package fr.univ_lyon1.master_info.m2ti.tiw5.services_fao;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="pnom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="composants" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
    "pnum",
    "pnom",
    "composants"
})
@XmlRootElement(name = "updateProduit")
public class UpdateProduit {

    @XmlElement(required = true)
    protected String pnum;
    @XmlElement(required = true)
    protected String pnom;
    protected List<String> composants;

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

    /**
     * Gets the value of the composants property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the composants property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComposants().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getComposants() {
        if (composants == null) {
            composants = new ArrayList<String>();
        }
        return this.composants;
    }

}
