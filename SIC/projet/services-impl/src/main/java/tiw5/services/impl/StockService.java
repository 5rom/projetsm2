package tiw5.services.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.univ_lyon1.master_info.m2ti.tiw5.services.AlbumIDQte;
import fr.univ_lyon1.master_info.m2ti.tiw5.services.Stock;

/**
 * Classe StockService
 * Implémentation du service StockService
 * Ici sont implémentées les méthodes du service :
 * - disponible
 * - assureCapacite
 * - commande
 * @author David CRESCENCE <crescence.david@gmail.com> et Sébastien FAURE <sebastien.faure3@gmail.com>
 * UCBL M2TI 2011-2012 
 */
public class StockService implements Stock {
	/**
	 * Le logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(StockService.class);
	
	/**
	 * Methode disponible :
	 * indique si le stock contient encore des disques correspondant
	 * au numéro passé en argument
	 * Attention: si l'id n'est pas présent dans l'entrepot, cela ajoute
	 * ce dernier avec la quantité 0.
	 * @param l'id de l'album dont on veut connaitre la disponibilite
	 * @return true si l'album dont l'id est passé en paramètre en disponible
	 * false sinon
	 */
	@Override
	public Boolean disponible(long arg0) {
		Boolean dispo=false;
		EntityManager em = Persistence.createEntityManagerFactory("etudiant")
		.createEntityManager();
		tiw5.modele.Stock s=em.find(tiw5.modele.Stock.class, arg0);
		if ( s != null) {
			LOG.info("found {}",arg0);
			if (s.getQuantite()>0){
				dispo=true;
			}
		} else {
			LOG.info("did not found {}",arg0);
			em.getTransaction().begin();
			tiw5.modele.Stock nouvStock = new tiw5.modele.Stock(arg0, 0);
			em.persist(nouvStock);
			em.getTransaction().commit();
		}		
		em.close();
		return dispo;
	}

	/**
	 * Methode commande :
	 * Retire 1 à la quantité disponible de chaque album listé
	 * Si l'album n'est pas disponible (quantité à 0) cela ne fait rien
	 * Attention: si l'id n'est pas présent dans l'entrepot, cela ajoute
	 * ce dernier avec la quantité de 0.
	 * @param liste de numéros d'albums
	 */
	@Override
	public void commande(List<Long> arg0) {
		EntityManager em = Persistence.createEntityManagerFactory("etudiant")
		.createEntityManager();
		for (int i=0;i<arg0.size();i++){
			em.getTransaction().begin();	
			tiw5.modele.Stock s=em.find(tiw5.modele.Stock.class, arg0.get(i));
			if ( s != null) {
				LOG.info("found {}",arg0.get(i));
				if (s.getQuantite()>0){
					s.setQuantite(s.getQuantite()-1);
				}
				em.merge(s);
			} else {
				LOG.info("did not found {}",arg0.get(i));
				tiw5.modele.Stock nouvStock = new tiw5.modele.Stock(arg0.get(i), 0);
				em.persist(nouvStock);
			}
			em.getTransaction().commit();			
		}
		em.close();		
	}

	/**
	 * Methode assureCapacite :
	 * Assure que la quantité requise est disponible pour chaque album
	 * Attention: si l'id n'est pas présent dans l'entrepot, cela ajoute
	 * ce dernier avec la quantité donnée.
	 * @param liste de couples (numéro d'album, quantité)
	 */
	@Override
	public void assureCapacite(List<AlbumIDQte> arg0) {
		EntityManager em = Persistence.createEntityManagerFactory("etudiant")
		.createEntityManager();
		for (int i=0;i<arg0.size();i++){
			em.getTransaction().begin();	
			tiw5.modele.Stock s=em.find(tiw5.modele.Stock.class, arg0.get(i).getId());
			if ( s != null) {
				LOG.info("found {}",arg0.get(i).getId());
				if (s.getQuantite()<arg0.get(i).getQte()){
					s.setQuantite(arg0.get(i).getQte());
				}
				em.merge(s);
			} else {
				LOG.info("did not found {}",arg0.get(i).getId());
				tiw5.modele.Stock nouvStock = new tiw5.modele.Stock(arg0.get(i).getId(), arg0.get(i).getQte());
				em.persist(nouvStock);
			}
			
			em.getTransaction().commit();			
		}
		em.close();
	}



}
