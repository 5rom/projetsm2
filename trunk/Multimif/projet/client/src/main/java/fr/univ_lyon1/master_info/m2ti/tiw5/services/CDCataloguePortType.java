package fr.univ_lyon1.master_info.m2ti.tiw5.services;

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
 * 2012-01-15T16:02:11.059+01:00
 * Generated source version: 2.4.2
 * 
 */
@WebService(targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", name = "CDCataloguePortType")
@XmlSeeAlso({fr.univ_lyon1.master_info.m2ti.tiw5.ventecd.ObjectFactory.class, ObjectFactory.class})
public interface CDCataloguePortType {

    @Oneway
    @RequestWrapper(localName = "addAlbumDescription", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services.AddAlbumDescription")
    @WebMethod
    public void addAlbumDescription(
        @WebParam(name = "arg0", targetNamespace = "")
        fr.univ_lyon1.master_info.m2ti.tiw5.services.Album arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getAlbumsFromCatalogue", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services.GetAlbumsFromCatalogue")
    @WebMethod
    @ResponseWrapper(localName = "getAlbumsFromCatalogueResponse", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services.GetAlbumsFromCatalogueResponse")
    public java.util.List<fr.univ_lyon1.master_info.m2ti.tiw5.services.Album> getAlbumsFromCatalogue();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getAlbumsFromCatalogueForArtist", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services.GetAlbumsFromCatalogueForArtist")
    @WebMethod
    @ResponseWrapper(localName = "getAlbumsFromCatalogueForArtistResponse", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services.GetAlbumsFromCatalogueForArtistResponse")
    public java.util.List<fr.univ_lyon1.master_info.m2ti.tiw5.services.Album> getAlbumsFromCatalogueForArtist(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getAlbumDescription", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services.GetAlbumDescription")
    @WebMethod
    @ResponseWrapper(localName = "getAlbumDescriptionResponse", targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", className = "fr.univ_lyon1.master_info.m2ti.tiw5.services.GetAlbumDescriptionResponse")
    public fr.univ_lyon1.master_info.m2ti.tiw5.services.Album getAlbumDescription(
        @WebParam(name = "arg0", targetNamespace = "")
        long arg0
    );
}
