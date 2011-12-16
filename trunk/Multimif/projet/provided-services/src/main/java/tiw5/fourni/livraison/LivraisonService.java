package tiw5.fourni.livraison;

import java.io.StringWriter;
import java.util.Date;

import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.EndpointReference;
import javax.xml.ws.wsaddressing.W3CEndpointReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@WebService(endpointInterface="tiw5.fourni.livraison.LivraisonInterface")
public class LivraisonService implements LivraisonInterface {

	private static final Logger log = LoggerFactory.getLogger(LivraisonService.class);
	
	private String ville;
	
	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@PersistenceContext
	private EntityManager em;
	
	/* (non-Javadoc)
	 * @see tiw5.fourni.livraison.LivraisonInterface#tarif(java.lang.String, java.lang.String, int)
	 */
	public Double tarif(String depuis, String vers, int quantite) {
		double base = 2.0;
		if (ville.equals(depuis) || ville.equals(vers)) {
			base /= 2;
		}
		return Math.max(base, base * quantite / 10.0);
	}
	
	/* (non-Javadoc)
	 * @see tiw5.fourni.livraison.LivraisonInterface#demandeLivraison(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional
	public Date demandeLivraison(W3CEndpointReference epr, String depuis, String vers, String id) {
		StringWriter sw = new StringWriter();
		StreamResult sr = new StreamResult(sw);
		epr.writeTo(sr);
		Livraison livraison = new Livraison(sw.toString(), depuis, vers, id);
		em.persist(livraison);
		log.info("livraison programmee");
		return livraison.getDate();
	}
	
}
