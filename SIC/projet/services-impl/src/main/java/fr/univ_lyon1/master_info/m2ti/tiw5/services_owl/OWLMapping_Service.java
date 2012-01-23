package fr.univ_lyon1.master_info.m2ti.tiw5.services_owl;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.4.2
 * 2012-01-23T23:45:42.186+01:00
 * Generated source version: 2.4.2
 * 
 */
@WebServiceClient(name = "OWLMapping", 
                  wsdlLocation = "file:/Users/padawanchichi/Documents/divers/Cours/TIW3/projet/services-impl/src/main/resources/OWLMappingService.wsdl",
                  targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_owl") 
public class OWLMapping_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services_owl", "OWLMapping");
    public final static QName OWLMappingSOAP = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services_owl", "OWLMappingSOAP");
    static {
        URL url = null;
        try {
            url = new URL("file:/Users/padawanchichi/Documents/divers/Cours/TIW3/projet/services-impl/src/main/resources/OWLMappingService.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(OWLMapping_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/Users/padawanchichi/Documents/divers/Cours/TIW3/projet/services-impl/src/main/resources/OWLMappingService.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public OWLMapping_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public OWLMapping_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public OWLMapping_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns OWLMapping
     */
    @WebEndpoint(name = "OWLMappingSOAP")
    public OWLMapping getOWLMappingSOAP() {
        return super.getPort(OWLMappingSOAP, OWLMapping.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OWLMapping
     */
    @WebEndpoint(name = "OWLMappingSOAP")
    public OWLMapping getOWLMappingSOAP(WebServiceFeature... features) {
        return super.getPort(OWLMappingSOAP, OWLMapping.class, features);
    }

}
