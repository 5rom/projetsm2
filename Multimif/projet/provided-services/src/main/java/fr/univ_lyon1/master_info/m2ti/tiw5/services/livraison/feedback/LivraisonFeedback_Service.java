package fr.univ_lyon1.master_info.m2ti.tiw5.services.livraison.feedback;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.5.0
 * 2011-12-16T11:04:06.205+01:00
 * Generated source version: 2.5.0
 * 
 */
@WebServiceClient(name = "LivraisonFeedback", 
                  wsdlLocation = "file:/C:/Users/Seb/eclipse-projects/svn/Multimif/projet/provided-services/src/main/resources/wsdl/LivraisonFeedback.wsdl",
                  targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services/livraison/feedback") 
public class LivraisonFeedback_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services/livraison/feedback", "LivraisonFeedback");
    public final static QName LivraisonFeedbackSOAP = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services/livraison/feedback", "LivraisonFeedbackSOAP");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/Seb/eclipse-projects/svn/Multimif/projet/provided-services/src/main/resources/wsdl/LivraisonFeedback.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(LivraisonFeedback_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/Users/Seb/eclipse-projects/svn/Multimif/projet/provided-services/src/main/resources/wsdl/LivraisonFeedback.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public LivraisonFeedback_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public LivraisonFeedback_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public LivraisonFeedback_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns LivraisonFeedback
     */
    @WebEndpoint(name = "LivraisonFeedbackSOAP")
    public LivraisonFeedback getLivraisonFeedbackSOAP() {
        return super.getPort(LivraisonFeedbackSOAP, LivraisonFeedback.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns LivraisonFeedback
     */
    @WebEndpoint(name = "LivraisonFeedbackSOAP")
    public LivraisonFeedback getLivraisonFeedbackSOAP(WebServiceFeature... features) {
        return super.getPort(LivraisonFeedbackSOAP, LivraisonFeedback.class, features);
    }

}