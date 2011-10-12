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
 * 2011-10-12T22:26:01.232+02:00
 * Generated source version: 2.4.2
 * 
 */
@WebServiceClient(name = "AlbumDataService", 
                  wsdlLocation = "file:/C:/Users/Seb/eclipse-projects/svn/TIW5/tp2/projet/client/src/main/resources/album-data.wsdl",
                  targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services") 
public class AlbumDataService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services", "AlbumDataService");
    public final static QName AlbumDataPort = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services", "AlbumDataPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/Seb/eclipse-projects/svn/TIW5/tp2/projet/client/src/main/resources/album-data.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(AlbumDataService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/Users/Seb/eclipse-projects/svn/TIW5/tp2/projet/client/src/main/resources/album-data.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public AlbumDataService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public AlbumDataService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AlbumDataService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns AlbumDataPortType
     */
    @WebEndpoint(name = "AlbumDataPort")
    public AlbumDataPortType getAlbumDataPort() {
        return super.getPort(AlbumDataPort, AlbumDataPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AlbumDataPortType
     */
    @WebEndpoint(name = "AlbumDataPort")
    public AlbumDataPortType getAlbumDataPort(WebServiceFeature... features) {
        return super.getPort(AlbumDataPort, AlbumDataPortType.class, features);
    }

}
