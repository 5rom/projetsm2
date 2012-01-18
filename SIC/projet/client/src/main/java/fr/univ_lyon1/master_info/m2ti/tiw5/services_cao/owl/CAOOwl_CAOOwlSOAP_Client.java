
package fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.owl;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.4.2
 * 2012-01-16T21:04:46.003+01:00
 * Generated source version: 2.4.2
 * 
 */
public final class CAOOwl_CAOOwlSOAP_Client {

    private static final QName SERVICE_NAME = new QName("http://master-info.univ-lyon1.fr/M2TI/TIW5/services_cao/owl", "CAOOwl");

    private CAOOwl_CAOOwlSOAP_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = CAOOwl_Service.WSDL_LOCATION;
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
      
        CAOOwl_Service ss = new CAOOwl_Service(wsdlURL, SERVICE_NAME);
        CAOOwl port = ss.getCAOOwlSOAP();  
        
        {
        System.out.println("Invoking parseOWL...");
        java.lang.String _parseOWL_filepath = "";
        java.lang.String _parseOWL__return = port.parseOWL(_parseOWL_filepath);
        System.out.println("parseOWL.result=" + _parseOWL__return);


        }

        System.exit(0);
    }

}