
package fr.univ_lyon1.master_info.m2ti.tiw5.services_cao;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getCompositionListResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getCompositionListResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="compositionList" type="{http://master-info.univ-lyon1.fr/M2TI/TIW5/services_cao}Pmajeur_Pmineur" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCompositionListResponse", propOrder = {
    "compositionList"
})
public class GetCompositionListResponse {

    protected List<PmajeurPmineur> compositionList;

    /**
     * Gets the value of the compositionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the compositionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCompositionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PmajeurPmineur }
     * 
     * 
     */
    public List<PmajeurPmineur> getCompositionList() {
        if (compositionList == null) {
            compositionList = new ArrayList<PmajeurPmineur>();
        }
        return this.compositionList;
    }

}
