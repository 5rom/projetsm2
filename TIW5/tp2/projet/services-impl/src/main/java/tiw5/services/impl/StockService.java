package tiw5.services.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tiw5.modele.Album;
import tiw5.modele.Artiste;

import fr.univ_lyon1.master_info.m2ti.tiw5.services.AlbumIDQte;
import fr.univ_lyon1.master_info.m2ti.tiw5.services.Stock;

public class StockService implements Stock {
	
	private static final Logger log = LoggerFactory.getLogger(StockService.class);
	
	@Override
	public Boolean disponible(long arg0) {
		Boolean dispo=false;
		EntityManager em = Persistence.createEntityManagerFactory("etudiant")
		.createEntityManager();
		tiw5.modele.Stock s=em.find(tiw5.modele.Stock.class, arg0);
		if ( s != null) {
			log.info("found {}",arg0);
			if (s.getQuantite()>0){
				dispo=true;
			}
		} else {
			log.info("did not found {}",arg0);
			em.getTransaction().begin();
			tiw5.modele.Stock nouvStock = new tiw5.modele.Stock(arg0, 0);
			em.persist(nouvStock);
			em.getTransaction().commit();
		}		
		em.close();
		return dispo;
	}

	@Override
	public void commande(List<Long> arg0) {
		EntityManager em = Persistence.createEntityManagerFactory("etudiant")
		.createEntityManager();
		for (int i=0;i<arg0.size();i++){
			em.getTransaction().begin();	
			tiw5.modele.Stock s=em.find(tiw5.modele.Stock.class, arg0.get(i));
			if ( s != null) {
				log.info("found {}",arg0.get(i));
				if (s.getQuantite()>0){
					s.setQuantite(s.getQuantite()-1);
				}
				em.merge(s);
			} else {
				log.info("did not found {}",arg0.get(i));
				tiw5.modele.Stock nouvStock = new tiw5.modele.Stock(arg0.get(i), 0);
				em.persist(nouvStock);
			}
			em.getTransaction().commit();			
		}
		em.close();		
	}

	@Override
	public void assureCapacite(List<AlbumIDQte> arg0) {
		EntityManager em = Persistence.createEntityManagerFactory("etudiant")
		.createEntityManager();
		for (int i=0;i<arg0.size();i++){
			em.getTransaction().begin();	
			tiw5.modele.Stock s=em.find(tiw5.modele.Stock.class, arg0.get(i).getId());
			if ( s != null) {
				log.info("found {}",arg0.get(i).getId());
				if (s.getQuantite()<arg0.get(i).getQte()){
					s.setQuantite(arg0.get(i).getQte());
				}
				em.merge(s);
			} else {
				log.info("did not found {}",arg0.get(i).getId());
				tiw5.modele.Stock nouvStock = new tiw5.modele.Stock(arg0.get(i).getId(), arg0.get(i).getQte());
				em.persist(nouvStock);
			}
			
			em.getTransaction().commit();			
		}
		em.close();
	}



}
