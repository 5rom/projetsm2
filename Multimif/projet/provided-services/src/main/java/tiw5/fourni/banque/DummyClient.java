package tiw5.fourni.banque;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.univ_lyon1.master_info.m2ti.tiw5.services.banque.BanqueClient;

@WebService(endpointInterface="fr.univ_lyon1.master_info.m2ti.tiw5.services.banque.BanqueClient")
public class DummyClient implements BanqueClient {

	private static final Logger log = LoggerFactory.getLogger(DummyClient.class);
	
	public void confirmOp(String transactionId, double valeur) {
		log.info("Confirmation recue par le faux client: {} (valeur: {})", transactionId, valeur);
	}

}
