package fr.univ_lyon1.master_info.m2ti.tiw5.services;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.4.2
 * 2012-01-21T17:30:01.387+01:00
 * Generated source version: 2.4.2
 * 
 */
@WebServiceClient(name = "CDCatalogueService", 
                  wsdlLocation = "file:/C:/Users/Seb/eclipse-projects/svn/Multimif/projet/client/src/main/resources/CDCatalogueService.wsdl",
                  targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services") 
public class CDCatalogueService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services", "CDCatalogueService");
    public final static QName CDCataloguePort = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services", "CDCataloguePort");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/Seb/eclipse-projects/svn/Multimif/projet/client/src/main/resources/CDCatalogueService.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(CDCatalogueService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/Users/Seb/eclipse-projects/svn/Multimif/projet/client/src/main/resources/CDCatalogueService.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public CDCatalogueService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public CDCatalogueService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CDCatalogueService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns CDCataloguePortType
     */
    @WebEndpoint(name = "CDCataloguePort")
    public CDCataloguePortType getCDCataloguePort() {
        return super.getPort(CDCataloguePort, CDCataloguePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CDCataloguePortType
     */
    @WebEndpoint(name = "CDCataloguePort")
    public CDCataloguePortType getCDCataloguePort(WebServiceFeature... features) {
        return super.getPort(CDCataloguePort, CDCataloguePortType.class, features);
    }

}
