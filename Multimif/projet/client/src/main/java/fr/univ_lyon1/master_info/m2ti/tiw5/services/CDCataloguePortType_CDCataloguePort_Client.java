
package fr.univ_lyon1.master_info.m2ti.tiw5.services;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.4.2
 * 2012-01-23T13:31:09.055+01:00
 * Generated source version: 2.4.2
 * 
 */
public final class CDCataloguePortType_CDCataloguePort_Client {

    private static final QName SERVICE_NAME = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services", "CDCatalogueService");

    private CDCataloguePortType_CDCataloguePort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = CDCatalogueService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        CDCatalogueService ss = new CDCatalogueService(wsdlURL, SERVICE_NAME);
        CDCataloguePortType port = ss.getCDCataloguePort();  
        
        {
        System.out.println("Invoking addAlbumDescription...");
        fr.univ_lyon1.master_info.m2ti.tiw5.services.Album _addAlbumDescription_arg0 = null;
        port.addAlbumDescription(_addAlbumDescription_arg0);


        }
        {
        System.out.println("Invoking getAlbumsFromCatalogueForTitle...");
        java.lang.String _getAlbumsFromCatalogueForTitle_arg0 = "";
        java.util.List<fr.univ_lyon1.master_info.m2ti.tiw5.services.Album> _getAlbumsFromCatalogueForTitle__return = port.getAlbumsFromCatalogueForTitle(_getAlbumsFromCatalogueForTitle_arg0);
        System.out.println("getAlbumsFromCatalogueForTitle.result=" + _getAlbumsFromCatalogueForTitle__return);


        }
        {
        System.out.println("Invoking getAlbumsFromCatalogueForGenre...");
        java.lang.String _getAlbumsFromCatalogueForGenre_arg0 = "";
        java.util.List<fr.univ_lyon1.master_info.m2ti.tiw5.services.Album> _getAlbumsFromCatalogueForGenre__return = port.getAlbumsFromCatalogueForGenre(_getAlbumsFromCatalogueForGenre_arg0);
        System.out.println("getAlbumsFromCatalogueForGenre.result=" + _getAlbumsFromCatalogueForGenre__return);


        }
        {
        System.out.println("Invoking getAlbumsFromCatalogue...");
        java.util.List<fr.univ_lyon1.master_info.m2ti.tiw5.services.Album> _getAlbumsFromCatalogue__return = port.getAlbumsFromCatalogue();
        System.out.println("getAlbumsFromCatalogue.result=" + _getAlbumsFromCatalogue__return);


        }
        {
        System.out.println("Invoking getAlbumsFromCatalogueForArtist...");
        java.lang.String _getAlbumsFromCatalogueForArtist_arg0 = "";
        java.util.List<fr.univ_lyon1.master_info.m2ti.tiw5.services.Album> _getAlbumsFromCatalogueForArtist__return = port.getAlbumsFromCatalogueForArtist(_getAlbumsFromCatalogueForArtist_arg0);
        System.out.println("getAlbumsFromCatalogueForArtist.result=" + _getAlbumsFromCatalogueForArtist__return);


        }
        {
        System.out.println("Invoking getAlbumDescription...");
        long _getAlbumDescription_arg0 = 0;
        fr.univ_lyon1.master_info.m2ti.tiw5.services.Album _getAlbumDescription__return = port.getAlbumDescription(_getAlbumDescription_arg0);
        System.out.println("getAlbumDescription.result=" + _getAlbumDescription__return);


        }

        System.exit(0);
    }

}
