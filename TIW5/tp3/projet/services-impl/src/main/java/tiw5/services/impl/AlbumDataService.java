package tiw5.services.impl;
 
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import tiw5.modele.Album;
import tiw5.modele.Artiste;
 
@WebService(targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", 
            serviceName = "AlbumDataService", 
            name = "AlbumDataPortType", 
            portName = "AlbumDataPort")
@Path("/AlbumDataServiceRest")
@Produces("application/xml")
public class AlbumDataService {
 
	private static final Logger log = LoggerFactory.getLogger(AlbumDataService.class);
 
	@WebMethod
	@GET
	@Produces("application/xml")
	@Path("/getalbum/{id}")
	public Album getAlbumDescription(@PathParam("id") long albumId) {
		EntityManager em = Persistence.createEntityManagerFactory("etudiant")
				.createEntityManager();
		Album album = em.find(Album.class, albumId);
		return album;
	}
 
	@Oneway
	@WebMethod
	@PUT
	@Consumes("application/xml")
	@Path("addalbum/{album}")
	// Dans le corps du message il y a les parametres dans le header et l'album sera dans le corps.
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
