
package fr.univ_lyon1.master_info.m2ti.tiw5.services_fao;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getProduitListResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getProduitListResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="produitList" type="{http://master-info.univ-lyon1.fr/M2TI/TIW5/services_fao}Pnum_Pnom" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getProduitListResponse", propOrder = {
    "produitList"
})
public class GetProduitListResponse {

    protected List<PnumPnom> produitList;

    /**
     * Gets the value of the produitList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the produitList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProduitList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PnumPnom }
     * 
     * 
     */
    public List<PnumPnom> getProduitList() {
        if (produitList == null) {
            produitList = new ArrayList<PnumPnom>();
        }
        return this.produitList;
    }

}
