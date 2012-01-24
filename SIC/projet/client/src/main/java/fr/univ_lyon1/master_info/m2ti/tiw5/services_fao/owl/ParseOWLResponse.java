
package fr.univ_lyon1.master_info.m2ti.tiw5.services_fao.owl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for parseOWLResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="parseOWLResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="filepath_response" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parseOWLResponse", propOrder = {
    "filepathResponse"
})
public class ParseOWLResponse {

    @XmlElement(name = "filepath_response", required = true)
    protected String filepathResponse;

    /**
     * Gets the value of the filepathResponse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilepathResponse() {
        return filepathResponse;
    }

    /**
     * Sets the value of the filepathResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilepathResponse(String value) {
        this.filepathResponse = value;
    }

}
