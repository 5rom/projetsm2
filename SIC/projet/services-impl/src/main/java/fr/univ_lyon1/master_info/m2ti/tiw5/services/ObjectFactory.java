
package fr.univ_lyon1.master_info.m2ti.tiw5.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.univ_lyon1.master_info.m2ti.tiw5.services package. 
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

    private final static QName _AddProduitResponse_QNAME = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services", "addProduitResponse");
    private final static QName _GetProduitListResponse_QNAME = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services", "getProduitListResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.univ_lyon1.master_info.m2ti.tiw5.services
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetProduitListResponse }
     * 
     */
    public GetProduitListResponse createGetProduitListResponse() {
        return new GetProduitListResponse();
    }

    /**
     * Create an instance of {@link GetProduitList }
     * 
     */
    public GetProduitList createGetProduitList() {
        return new GetProduitList();
    }

    /**
     * Create an instance of {@link AddProduitResponse }
     * 
     */
    public AddProduitResponse createAddProduitResponse() {
        return new AddProduitResponse();
    }

    /**
     * Create an instance of {@link AddProduit }
     * 
     */
    public AddProduit createAddProduit() {
        return new AddProduit();
    }

    /**
     * Create an instance of {@link PnumPnom }
     * 
     */
    public PnumPnom createPnumPnom() {
        return new PnumPnom();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddProduitResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", name = "addProduitResponse")
    public JAXBElement<AddProduitResponse> createAddProduitResponse(AddProduitResponse value) {
        return new JAXBElement<AddProduitResponse>(_AddProduitResponse_QNAME, AddProduitResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProduitListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", name = "getProduitListResponse")
    public JAXBElement<GetProduitListResponse> createGetProduitListResponse(GetProduitListResponse value) {
        return new JAXBElement<GetProduitListResponse>(_GetProduitListResponse_QNAME, GetProduitListResponse.class, null, value);
    }

}
