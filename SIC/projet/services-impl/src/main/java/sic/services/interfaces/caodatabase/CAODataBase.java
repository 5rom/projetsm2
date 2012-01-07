package sic.services.interfaces.caodatabase;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.4.2
 * 2012-01-06T22:27:27.013+01:00
 * Generated source version: 2.4.2
 * 
 */
@WebService(targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", name = "CAODataBase")
@XmlSeeAlso({ObjectFactory.class})
public interface CAODataBase {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "addProduit", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services.AddProduit")
    @WebMethod
    @ResponseWrapper(localName = "addProduitResponse", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services.AddProduitResponse")
    public java.lang.Boolean addProduit(
        @WebParam(name = "pnum", targetNamespace = "")
        long pnum,
        @WebParam(name = "pnom", targetNamespace = "")
        java.lang.String pnom
    );
}
