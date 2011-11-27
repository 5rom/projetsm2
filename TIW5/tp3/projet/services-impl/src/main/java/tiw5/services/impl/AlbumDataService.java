package tiw5.services.impl;
 
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tiw5.modele.Album;
import tiw5.modele.Artiste;

/**
 * Classe d'implementation sur service ALbumDataService
 * Créée par Emmanuel Coquery, ajout des annotations REST
 * par Sébastien Faure et David Crescence pour publication
 * du service AlbumDataService en REST.
 * @author Emmanuel Coquery <emmanuel.coquery@liris.cnrs.fr>, David CRESCENCE <crescence.david@gmail.com> et Sébastien FAURE <sebastien.faure3@gmail.com>
 * UCBL M2TI 2011-2012 
 */
@WebService(targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", 
            serviceName = "AlbumDataService", 
            name = "AlbumDataPortType", 
            portName = "AlbumDataPort")
@Path("/AlbumDataServiceRest") // L'uri du service AlbumDataServiceRest
public class AlbumDataService {
 
	/**
	 * Le logger
	 */
	private static final Logger log = LoggerFactory.getLogger(AlbumDataService.class);
 
	/**
	 * Récuperation d'un album a partir de son id
	 * Ajout d'annotations REST:
	 * -Path("getalbum/{id}"): pour donner l'uri de la méthode avec en plus le paramètre "id". 
	 * -PathParam("id"): pour lié le paramètre "id" de l'uri à celui de la méthode "albumId"). 
	 * -GET: pour récupérer une ressource qui existe déjà, un album. 
	 * -Produces("application/xml"): l'album renvoyé est de type MIME XML car en sous forme XML. 
	 * @param albumId L'id de l'album
	 * @return L'album
	 */
	@WebMethod
	@GET
	@Produces("application/xml")
	@Path("getalbum/{id}")
	public Album getAlbumDescription(@PathParam("id") long albumId) {
		EntityManager em = Persistence.createEntityManagerFactory("etudiant")
				.createEntityManager();
		return em.find(Album.class, albumId);
	}
 
	/**
	 * Ajout d'un album a la base
	 * Ajout d'annotations REST:
	 * -PUT: pour la creation ou la mise a jour d'une instance d'album. 
	 * -Consumes("application/xml"): pour signifier que la methode prend un album sous forme xml. 
	 * -Path("addalbum/"): l'uri de la methode d'ajout. 
	 * @param album L'album
	 */
	@Oneway
	@WebMethod
	@PUT
	@Consumes("application/xml")
	@Path("addalbum")
	public void addAlbumDescription(Album album) {
		EntityManager em = Persistence.createEntityManagerFactory("etudiant")
				.createEntityManager();
		em.getTransaction().begin();
		if (em.find(Album.class, album.getId()) != null) {
			log.info("found {}",album.getId());
			em.merge(album);
		} else {
			log.info("did not found {}",album.getId());
			em.persist(album);
		}
		for(Artiste a : album.getArtistes()) {
			if (em.find(Artiste.class, a.getUri()) != null) {
				em.merge(a);
			} else {
				em.persist(a);
			}
		}
		em.getTransaction().commit();
	}
 
}
