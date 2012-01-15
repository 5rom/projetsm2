
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

    private final static QName _GetAlbumsFromCatalogueResponse_QNAME = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services", "getAlbumsFromCatalogueResponse");
    private final static QName _GetAlbumDescription_QNAME = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services", "getAlbumDescription");
    private final static QName _GetAlbumDescriptionResponse_QNAME = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services", "getAlbumDescriptionResponse");
    private final static QName _GetAlbumsFromCatalogueForArtistResponse_QNAME = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services", "getAlbumsFromCatalogueForArtistResponse");
    private final static QName _GetAlbumsFromCatalogueForArtist_QNAME = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services", "getAlbumsFromCatalogueForArtist");
    private final static QName _GetAlbumsFromCatalogue_QNAME = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services", "getAlbumsFromCatalogue");
    private final static QName _AddAlbumDescription_QNAME = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services", "addAlbumDescription");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.univ_lyon1.master_info.m2ti.tiw5.services
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddAlbumDescription }
     * 
     */
    public AddAlbumDescription createAddAlbumDescription() {
        return new AddAlbumDescription();
    }

    /**
     * Create an instance of {@link GetAlbumsFromCatalogue }
     * 
     */
    public GetAlbumsFromCatalogue createGetAlbumsFromCatalogue() {
        return new GetAlbumsFromCatalogue();
    }

    /**
     * Create an instance of {@link Album }
     * 
     */
    public Album createAlbum() {
        return new Album();
    }

    /**
     * Create an instance of {@link GetAlbumDescription }
     * 
     */
    public GetAlbumDescription createGetAlbumDescription() {
        return new GetAlbumDescription();
    }

    /**
     * Create an instance of {@link TestResponse }
     * 
     */
    public TestResponse createTestResponse() {
        return new TestResponse();
    }

    /**
     * Create an instance of {@link GetAlbumsFromCatalogueForArtist }
     * 
     */
    public GetAlbumsFromCatalogueForArtist createGetAlbumsFromCatalogueForArtist() {
        return new GetAlbumsFromCatalogueForArtist();
    }

    /**
     * Create an instance of {@link ArtisteRef }
     * 
     */
    public ArtisteRef createArtisteRef() {
        return new ArtisteRef();
    }

    /**
     * Create an instance of {@link GetAlbumsFromCatalogueResponse }
     * 
     */
    public GetAlbumsFromCatalogueResponse createGetAlbumsFromCatalogueResponse() {
        return new GetAlbumsFromCatalogueResponse();
    }

    /**
     * Create an instance of {@link Test }
     * 
     */
    public Test createTest() {
        return new Test();
    }

    /**
     * Create an instance of {@link GetAlbumDescriptionResponse }
     * 
     */
    public GetAlbumDescriptionResponse createGetAlbumDescriptionResponse() {
        return new GetAlbumDescriptionResponse();
    }

    /**
     * Create an instance of {@link GetAlbumsFromCatalogueForArtistResponse }
     * 
     */
    public GetAlbumsFromCatalogueForArtistResponse createGetAlbumsFromCatalogueForArtistResponse() {
        return new GetAlbumsFromCatalogueForArtistResponse();
    }

    /**
     * Create an instance of {@link Piste }
     * 
     */
    public Piste createPiste() {
        return new Piste();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAlbumsFromCatalogueResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", name = "getAlbumsFromCatalogueResponse")
    public JAXBElement<GetAlbumsFromCatalogueResponse> createGetAlbumsFromCatalogueResponse(GetAlbumsFromCatalogueResponse value) {
        return new JAXBElement<GetAlbumsFromCatalogueResponse>(_GetAlbumsFromCatalogueResponse_QNAME, GetAlbumsFromCatalogueResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAlbumDescription }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", name = "getAlbumDescription")
    public JAXBElement<GetAlbumDescription> createGetAlbumDescription(GetAlbumDescription value) {
        return new JAXBElement<GetAlbumDescription>(_GetAlbumDescription_QNAME, GetAlbumDescription.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAlbumDescriptionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", name = "getAlbumDescriptionResponse")
    public JAXBElement<GetAlbumDescriptionResponse> createGetAlbumDescriptionResponse(GetAlbumDescriptionResponse value) {
        return new JAXBElement<GetAlbumDescriptionResponse>(_GetAlbumDescriptionResponse_QNAME, GetAlbumDescriptionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAlbumsFromCatalogueForArtistResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", name = "getAlbumsFromCatalogueForArtistResponse")
    public JAXBElement<GetAlbumsFromCatalogueForArtistResponse> createGetAlbumsFromCatalogueForArtistResponse(GetAlbumsFromCatalogueForArtistResponse value) {
        return new JAXBElement<GetAlbumsFromCatalogueForArtistResponse>(_GetAlbumsFromCatalogueForArtistResponse_QNAME, GetAlbumsFromCatalogueForArtistResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAlbumsFromCatalogueForArtist }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", name = "getAlbumsFromCatalogueForArtist")
    public JAXBElement<GetAlbumsFromCatalogueForArtist> createGetAlbumsFromCatalogueForArtist(GetAlbumsFromCatalogueForArtist value) {
        return new JAXBElement<GetAlbumsFromCatalogueForArtist>(_GetAlbumsFromCatalogueForArtist_QNAME, GetAlbumsFromCatalogueForArtist.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAlbumsFromCatalogue }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", name = "getAlbumsFromCatalogue")
    public JAXBElement<GetAlbumsFromCatalogue> createGetAlbumsFromCatalogue(GetAlbumsFromCatalogue value) {
        return new JAXBElement<GetAlbumsFromCatalogue>(_GetAlbumsFromCatalogue_QNAME, GetAlbumsFromCatalogue.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAlbumDescription }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", name = "addAlbumDescription")
    public JAXBElement<AddAlbumDescription> createAddAlbumDescription(AddAlbumDescription value) {
        return new JAXBElement<AddAlbumDescription>(_AddAlbumDescription_QNAME, AddAlbumDescription.class, null, value);
    }

}
