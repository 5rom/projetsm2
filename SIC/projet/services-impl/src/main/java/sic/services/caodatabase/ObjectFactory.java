
package sic.services.caodatabase;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the sic.services.caodatabase package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddProduitResponse_QNAME = new QName("caodatabase.services.sic", "addProduitResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: sic.services.caodatabase
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddProduit }
     * 
     */
    public AddProduit createAddProduit() {
        return new AddProduit();
    }

    /**
     * Create an instance of {@link AddProduitResponse }
     * 
     */
    public AddProduitResponse createAddProduitResponse() {
        return new AddProduitResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddProduitResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "caodatabase.services.sic", name = "addProduitResponse")
    public JAXBElement<AddProduitResponse> createAddProduitResponse(AddProduitResponse value) {
        return new JAXBElement<AddProduitResponse>(_AddProduitResponse_QNAME, AddProduitResponse.class, null, value);
    }

}
