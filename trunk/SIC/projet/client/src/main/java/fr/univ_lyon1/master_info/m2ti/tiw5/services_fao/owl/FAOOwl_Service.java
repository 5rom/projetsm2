package fr.univ_lyon1.master_info.m2ti.tiw5.services_fao.owl;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.4.2
 * 2012-01-24T15:04:19.338+01:00
 * Generated source version: 2.4.2
 * 
 */
@WebServiceClient(name = "FAOOwl", 
                  wsdlLocation = "file:/C:/Users/Seb/eclipse-projects/svn/SIC/projet/client/src/main/resources/FAOOwlService.wsdl",
                  targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_fao/owl") 
public class FAOOwl_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services_fao/owl", "FAOOwl");
    public final static QName FAOOwlSOAP = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services_fao/owl", "FAOOwlSOAP");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/Seb/eclipse-projects/svn/SIC/projet/client/src/main/resources/FAOOwlService.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(FAOOwl_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/Users/Seb/eclipse-projects/svn/SIC/projet/client/src/main/resources/FAOOwlService.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public FAOOwl_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public FAOOwl_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public FAOOwl_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns FAOOwl
     */
    @WebEndpoint(name = "FAOOwlSOAP")
    public FAOOwl getFAOOwlSOAP() {
        return super.getPort(FAOOwlSOAP, FAOOwl.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns FAOOwl
     */
    @WebEndpoint(name = "FAOOwlSOAP")
    public FAOOwl getFAOOwlSOAP(WebServiceFeature... features) {
        return super.getPort(FAOOwlSOAP, FAOOwl.class, features);
    }

}