package fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.owl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.4.2
 * 2012-01-24T20:59:06.055+01:00
 * Generated source version: 2.4.2
 * 
 */
@WebService(targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_cao/owl", name = "CAOOWL")
@XmlSeeAlso({ObjectFactory.class})
public interface CAOOWL {

    @WebResult(name = "filepath_response", targetNamespace = "")
    @RequestWrapper(localName = "parseOWL", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_cao/owl", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.owl.ParseOWL")
    @WebMethod
    @ResponseWrapper(localName = "parseOWLResponse", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_cao/owl", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.owl.ParseOWLResponse")
    public java.lang.String parseOWL(
        @WebParam(name = "filepath", targetNamespace = "")
        java.lang.String filepath
    );
}
