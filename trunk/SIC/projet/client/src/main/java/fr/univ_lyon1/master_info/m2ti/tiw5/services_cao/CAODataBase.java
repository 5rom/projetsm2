package fr.univ_lyon1.master_info.m2ti.tiw5.services_cao;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.4.2
 * 2012-01-24T20:59:06.877+01:00
 * Generated source version: 2.4.2
 * 
 */
@WebService(targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_cao", name = "CAODataBase")
@XmlSeeAlso({ObjectFactory.class})
public interface CAODataBase {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "deleteProduit", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_cao", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.DeleteProduit")
    @WebMethod
    @ResponseWrapper(localName = "deleteProduitResponse", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_cao", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.DeleteProduitResponse")
    public java.lang.Boolean deleteProduit(
        @WebParam(name = "pnum", targetNamespace = "")
        long pnum
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "deleteComposition", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_cao", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.DeleteComposition")
    @WebMethod
    @ResponseWrapper(localName = "deleteCompositionResponse", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_cao", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.DeleteCompositionResponse")
    public java.lang.Boolean deleteComposition(
        @WebParam(name = "pmajeur", targetNamespace = "")
        long pmajeur,
        @WebParam(name = "pmineur", targetNamespace = "")
        long pmineur
    );

    @WebResult(name = "produitList", targetNamespace = "")
    @RequestWrapper(localName = "getProduitList", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_cao", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.GetProduitList")
    @WebMethod
    @ResponseWrapper(localName = "getProduitListResponse", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_cao", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.GetProduitListResponse")
    public java.util.List<fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.PnumPnom> getProduitList();

    @WebResult(name = "compositionList", targetNamespace = "")
    @RequestWrapper(localName = "getCompositionList", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_cao", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.GetCompositionList")
    @WebMethod
    @ResponseWrapper(localName = "getCompositionListResponse", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_cao", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.GetCompositionListResponse")
    public java.util.List<fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.PmajeurPmineur> getCompositionList();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "addComposition", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_cao", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.AddComposition")
    @WebMethod
    @ResponseWrapper(localName = "addCompositionResponse", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_cao", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.AddCompositionResponse")
    public java.lang.Boolean addComposition(
        @WebParam(name = "pmajeur", targetNamespace = "")
        long pmajeur,
        @WebParam(name = "pmineur", targetNamespace = "")
        long pmineur
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "updateProduit", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_cao", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.UpdateProduit")
    @WebMethod
    @ResponseWrapper(localName = "updateProduitResponse", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_cao", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.UpdateProduitResponse")
    public java.lang.Boolean updateProduit(
        @WebParam(name = "pnum", targetNamespace = "")
        long pnum,
        @WebParam(name = "pnom", targetNamespace = "")
        java.lang.String pnom
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "addProduit", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_cao", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.AddProduit")
    @WebMethod
    @ResponseWrapper(localName = "addProduitResponse", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services_cao", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.AddProduitResponse")
    public java.lang.Boolean addProduit(
        @WebParam(name = "pnum", targetNamespace = "")
        long pnum,
        @WebParam(name = "pnom", targetNamespace = "")
        java.lang.String pnom
    );
}
