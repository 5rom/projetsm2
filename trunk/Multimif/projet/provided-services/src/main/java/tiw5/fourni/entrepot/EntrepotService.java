package tiw5.fourni.entrepot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebService(endpointInterface="tiw5.fourni.entrepot.EntrepotInterface")
public class EntrepotService implements EntrepotInterface {

	private final static Logger log = LoggerFactory
			.getLogger(EntrepotService.class);

	private String ville;

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tiw5.fourni.entrepot.EntrepotInterface#disponibilite(java.util.Collection
	 * )
	 */
	public Collection<DispoCd> disponibilite(Collection<QuantiteCd> demande) {
		log.debug("debut disponibilite");
		ArrayList<DispoCd> resultat = new ArrayList<DispoCd>();
		for (QuantiteCd qte : demande) {
			Date d = new Date();
			if (qte.quantite % 2 == 0) {
				d.setTime(d.getTime() + 1000 * 60 * 60 * 24 * 5); // + 5 jours
			}
			resultat.add(new DispoCd(Math.min(qte.quantite, 10), qte.idCd, d));
		}
		log.debug("fin disponibilite");
		return resultat;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tiw5.fourni.entrepot.EntrepotInterface#commande(java.util.Collection)
	 */
	public String commande(Collection<QuantiteCd> demande)
			throws NonDisponibleException {
		for (QuantiteCd qte : demande) {
			if (qte.quantite > 10) {
				log.warn("cd {} indisponible en quantite {}", qte.idCd,
						qte.quantite);
				throw new NonDisponibleException(qte);
			}
		}
		log.debug("reservation ok");
		return Double.toHexString(Math.random());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tiw5.fourni.entrepot.EntrepotInterface#emplacement()
	 */
	public String emplacement() {
		return ville;
	}

}
