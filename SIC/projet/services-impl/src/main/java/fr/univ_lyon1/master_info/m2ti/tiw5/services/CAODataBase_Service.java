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
 * 2012-01-10T11:47:48.021+01:00
 * Generated source version: 2.4.2
 * 
 */
@WebServiceClient(name = "CAODataBase", 
                  wsdlLocation = "file:/C:/Users/Seb/eclipse-projects/svn/SIC/projet/services-impl/src/main/resources/CAODataBaseService.wsdl",
                  targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services") 
public class CAODataBase_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services", "CAODataBase");
    public final static QName CAODataBaseSOAP = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services", "CAODataBaseSOAP");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/Seb/eclipse-projects/svn/SIC/projet/services-impl/src/main/resources/CAODataBaseService.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(CAODataBase_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/Users/Seb/eclipse-projects/svn/SIC/projet/services-impl/src/main/resources/CAODataBaseService.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public CAODataBase_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public CAODataBase_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CAODataBase_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns CAODataBase
     */
    @WebEndpoint(name = "CAODataBaseSOAP")
    public CAODataBase getCAODataBaseSOAP() {
        return super.getPort(CAODataBaseSOAP, CAODataBase.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CAODataBase
     */
    @WebEndpoint(name = "CAODataBaseSOAP")
    public CAODataBase getCAODataBaseSOAP(WebServiceFeature... features) {
        return super.getPort(CAODataBaseSOAP, CAODataBase.class, features);
    }

}
