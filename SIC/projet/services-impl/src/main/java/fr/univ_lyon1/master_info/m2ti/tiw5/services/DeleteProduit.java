
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
 *         &lt;element name="pnum" type="{http://www.w3.org/2001/XMLSchema}long"/>
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

    protected long pnum;

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

}
