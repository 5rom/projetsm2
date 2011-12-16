package tiw5.fourni.livraison;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.datatype.DatatypeConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import tiw5.fourni.livraison.Livraison.Etat;

public class LivraisonUtil {

	Logger log = LoggerFactory.getLogger(LivraisonUtil.class);

	@PersistenceContext
	private EntityManager em;

	public Livraison.Etat getEtat(long id) {
		return em.find(Livraison.class, id).getEtat();
	}

	@Transactional
	public Etat miseAJour(long id) throws DatatypeConfigurationException {
		try {
			Livraison l = em.find(Livraison.class, id);
			if (l.getEtat() == Etat.Livree) {
				em.remove(l);
				return Etat.Livree;
			} else {
				return l.miseAJour();
			}
		} catch (RuntimeException e) {
			log.error("Erreur dans la mise ˆ jour", e);
			throw e;
		}
	}

	public List<Livraison> getAllLivraisons() {
		Query query = em.createNamedQuery("findAllLivraisons");
		return query.getResultList();
	}
}
