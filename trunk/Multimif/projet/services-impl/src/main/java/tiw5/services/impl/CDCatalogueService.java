package tiw5.services.impl;
 
import java.util.ArrayList;
import java.util.List;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import tiw5.modele.Album;
import tiw5.modele.Artiste;
 
@WebService(targetNamespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services", 
            serviceName = "CDCatalogueService", 
            name = "CDCataloguePortType", 
            portName = "CDCataloguePort")
public class CDCatalogueService {
 
	private static final Logger log = LoggerFactory.getLogger(AlbumDataService.class);
 
	@WebMethod
	public Album getAlbumDescription(long albumId) {
		EntityManager em = Persistence.createEntityManagerFactory("etudiant")
				.createEntityManager();
		Album album = em.find(Album.class, albumId);
		return album;
	}
	
	@WebMethod
	public List<Album> getAlbumsFromCatalogue() {
		EntityManager em = Persistence.createEntityManagerFactory("etudiant")
				.createEntityManager();
		ArrayList<Album> list = (ArrayList<Album>) em.createQuery("SELECT a FROM Album a order by a.id").getResultList();
		return list;
	}
	
	@WebMethod
	public List<Album> getAlbumsFromCatalogueForArtist(String uri) {
		EntityManager em = Persistence.createEntityManagerFactory("etudiant")
				.createEntityManager();
		//ArrayList<Album> list = (ArrayList<Album>) em.createQuery("SELECT a FROM Album a, IN (a.artistes) b where b.uri='"+uri+"' order by a.id").getResultList();
		ArrayList<Album> list = (ArrayList<Album>) em.createQuery("SELECT a FROM Album a, IN (a.artistes) b where b.uri like '%"+uri+"%' order by a.id").getResultList();
		return list;
	}
	
	@WebMethod
	public List<Album> getAlbumsFromCatalogueForGenre(String genre) {
		EntityManager em = Persistence.createEntityManagerFactory("etudiant")
				.createEntityManager();
		ArrayList<Album> list = (ArrayList<Album>) em.createQuery("SELECT a FROM Album a where a.genre like '%"+genre+"%' order by a.id").getResultList();
		return list;
	}	
	
	@WebMethod
	public List<Album> getAlbumsFromCatalogueForTitle(String title) {
		EntityManager em = Persistence.createEntityManagerFactory("etudiant")
				.createEntityManager();
		ArrayList<Album> list = (ArrayList<Album>) em.createQuery("SELECT a FROM Album a where a.titre like '%"+title+"%' order by a.id").getResultList();
		return list;
	}		
 
	@Oneway
	@WebMethod
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
