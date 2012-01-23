
package fr.univ_lyon1.master_info.m2ti.tiw5.services_owl;

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
 *         &lt;element name="filepath1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="filepath2" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "filepath1",
    "filepath2"
})
@XmlRootElement(name = "mapOWL")
public class MapOWL {

    @XmlElement(required = true)
    protected String filepath1;
    @XmlElement(required = true)
    protected String filepath2;

    /**
     * Gets the value of the filepath1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilepath1() {
        return filepath1;
    }

    /**
     * Sets the value of the filepath1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilepath1(String value) {
        this.filepath1 = value;
    }

    /**
     * Gets the value of the filepath2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilepath2() {
        return filepath2;
    }

    /**
     * Sets the value of the filepath2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilepath2(String value) {
        this.filepath2 = value;
    }

}
