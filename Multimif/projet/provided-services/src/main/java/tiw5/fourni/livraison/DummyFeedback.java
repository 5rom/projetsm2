package tiw5.fourni.livraison;

import java.text.DateFormat;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.univ_lyon1.master_info.m2ti.tiw5.services.livraison.feedback.InformePriseEnCompte;
import fr.univ_lyon1.master_info.m2ti.tiw5.services.livraison.feedback.LivraisonEffectuee;
import fr.univ_lyon1.master_info.m2ti.tiw5.services.livraison.feedback.LivraisonFeedback;

@WebService(endpointInterface = "fr.univ_lyon1.master_info.m2ti.tiw5.services.livraison.feedback.LivraisonFeedback")
public class DummyFeedback implements LivraisonFeedback {

	private final static Logger log = LoggerFactory
			.getLogger(DummyFeedback.class);

	public void informeLivraisonOp(LivraisonEffectuee parameters) {
		log.info("livraison {} effectuée le {}",
				new Object[] { parameters.getIdCommande(),
						parameters.getDate().toString() });
	}

	public void informePriseEnCompteOp(InformePriseEnCompte parameters) {
		log.info("livraison {} prise en compte et prévue le {}", new Object[] {
				parameters.getIdCommande(), parameters.getDate().toString() });
	}
}
