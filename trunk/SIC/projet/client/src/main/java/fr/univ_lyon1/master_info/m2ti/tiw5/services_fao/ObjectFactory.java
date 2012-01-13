
package fr.univ_lyon1.master_info.m2ti.tiw5.services_fao;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.univ_lyon1.master_info.m2ti.tiw5.services_fao package. 
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

    private final static QName _AddProduitResponse_QNAME = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services_fao", "addProduitResponse");
    private final static QName _GetProduitListResponse_QNAME = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services_fao", "getProduitListResponse");
    private final static QName _UpdateProduitResponse_QNAME = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services_fao", "updateProduitResponse");
    private final static QName _DeleteCompositionResponse_QNAME = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services_fao", "deleteCompositionResponse");
    private final static QName _DeleteProduitResponse_QNAME = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services_fao", "deleteProduitResponse");
    private final static QName _GetCompositionListResponse_QNAME = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services_fao", "getCompositionListResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.univ_lyon1.master_info.m2ti.tiw5.services_fao
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UpdateProduit }
     * 
     */
    public UpdateProduit createUpdateProduit() {
        return new UpdateProduit();
    }

    /**
     * Create an instance of {@link DeleteComposition }
     * 
     */
    public DeleteComposition createDeleteComposition() {
        return new DeleteComposition();
    }

    /**
     * Create an instance of {@link AddProduitResponse }
     * 
     */
    public AddProduitResponse createAddProduitResponse() {
        return new AddProduitResponse();
    }

    /**
     * Create an instance of {@link GetProduitListResponse }
     * 
     */
    public GetProduitListResponse createGetProduitListResponse() {
        return new GetProduitListResponse();
    }

    /**
     * Create an instance of {@link DeleteProduitResponse }
     * 
     */
    public DeleteProduitResponse createDeleteProduitResponse() {
        return new DeleteProduitResponse();
    }

    /**
     * Create an instance of {@link GetCompositionListResponse }
     * 
     */
    public GetCompositionListResponse createGetCompositionListResponse() {
        return new GetCompositionListResponse();
    }

    /**
     * Create an instance of {@link PnumPnom }
     * 
     */
    public PnumPnom createPnumPnom() {
        return new PnumPnom();
    }

    /**
     * Create an instance of {@link AddProduit }
     * 
     */
    public AddProduit createAddProduit() {
        return new AddProduit();
    }

    /**
     * Create an instance of {@link UpdateProduitResponse }
     * 
     */
    public UpdateProduitResponse createUpdateProduitResponse() {
        return new UpdateProduitResponse();
    }

    /**
     * Create an instance of {@link DeleteCompositionResponse }
     * 
     */
    public DeleteCompositionResponse createDeleteCompositionResponse() {
        return new DeleteCompositionResponse();
    }

    /**
     * Create an instance of {@link DeleteProduit }
     * 
     */
    public DeleteProduit createDeleteProduit() {
        return new DeleteProduit();
    }

    /**
     * Create an instance of {@link PmajeurPmineur }
     * 
     */
    public PmajeurPmineur createPmajeurPmineur() {
        return new PmajeurPmineur();
    }

    /**
     * Create an instance of {@link GetProduitList }
     * 
     */
    public GetProduitList createGetProduitList() {
        return new GetProduitList();
    }

    /**
     * Create an instance of {@link GetCompositionList }
     * 
     */
    public GetCompositionList createGetCompositionList() {
        return new GetCompositionList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddProduitResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_fao", name = "addProduitResponse")
    public JAXBElement<AddProduitResponse> createAddProduitResponse(AddProduitResponse value) {
        return new JAXBElement<AddProduitResponse>(_AddProduitResponse_QNAME, AddProduitResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProduitListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_fao", name = "getProduitListResponse")
    public JAXBElement<GetProduitListResponse> createGetProduitListResponse(GetProduitListResponse value) {
        return new JAXBElement<GetProduitListResponse>(_GetProduitListResponse_QNAME, GetProduitListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateProduitResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_fao", name = "updateProduitResponse")
    public JAXBElement<UpdateProduitResponse> createUpdateProduitResponse(UpdateProduitResponse value) {
        return new JAXBElement<UpdateProduitResponse>(_UpdateProduitResponse_QNAME, UpdateProduitResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteCompositionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_fao", name = "deleteCompositionResponse")
    public JAXBElement<DeleteCompositionResponse> createDeleteCompositionResponse(DeleteCompositionResponse value) {
        return new JAXBElement<DeleteCompositionResponse>(_DeleteCompositionResponse_QNAME, DeleteCompositionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteProduitResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_fao", name = "deleteProduitResponse")
    public JAXBElement<DeleteProduitResponse> createDeleteProduitResponse(DeleteProduitResponse value) {
        return new JAXBElement<DeleteProduitResponse>(_DeleteProduitResponse_QNAME, DeleteProduitResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCompositionListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_fao", name = "getCompositionListResponse")
    public JAXBElement<GetCompositionListResponse> createGetCompositionListResponse(GetCompositionListResponse value) {
        return new JAXBElement<GetCompositionListResponse>(_GetCompositionListResponse_QNAME, GetCompositionListResponse.class, null, value);
    }

}
