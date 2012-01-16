
package fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.owl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.owl package. 
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

    private final static QName _ParseOWLResponse_QNAME = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services_cao/owl", "parseOWLResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.owl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ParseOWLResponse }
     * 
     */
    public ParseOWLResponse createParseOWLResponse() {
        return new ParseOWLResponse();
    }

    /**
     * Create an instance of {@link ParseOWL }
     * 
     */
    public ParseOWL createParseOWL() {
        return new ParseOWL();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseOWLResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_cao/owl", name = "parseOWLResponse")
    public JAXBElement<ParseOWLResponse> createParseOWLResponse(ParseOWLResponse value) {
        return new JAXBElement<ParseOWLResponse>(_ParseOWLResponse_QNAME, ParseOWLResponse.class, null, value);
    }

}
