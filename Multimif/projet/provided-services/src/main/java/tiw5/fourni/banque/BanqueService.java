package tiw5.fourni.banque;

import java.net.URL;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.Encoded;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Path("/banque")
@Produces("text/plain")
@Consumes({ "application/xml", "application/x-www-form-urlencoded" })
public class BanqueService {

	private static final String APP = "http://localhost:8085/provided-services";
	public static final String DEFAULT_CONFIRM_ADDRESS = 
		"<EndpointReference xmlns=\"http://www.w3.org/2005/08/addressing\">"
		+"<Address>"+APP+"/services/banque/DummyClient</Address>"
		+"</EndpointReference>";
	public static final String DEFAULT_REDIRECTION = APP+"/MessageActions";
	
	private static Logger log = LoggerFactory.getLogger(BanqueService.class);
	
	@PersistenceContext
	private EntityManager em;

	@POST
	@Path("/debit")
	@Produces("text/html")
	@Transactional
	public RedirectBean debit(@FormParam("numero") long numero,
			@FormParam("combien") double combien,
			@FormParam("redirectUrl") String redirectTo,
			@FormParam("confirmeA") String confirmeA) {
		if (confirmeA == null || "".equals(confirmeA)) {
			confirmeA = DEFAULT_CONFIRM_ADDRESS;
			log.warn("Pas d'adresse de confirmation pour la transaction, on utilise {}",DEFAULT_CONFIRM_ADDRESS);
		}
		if (redirectTo == null || "".equals(redirectTo)) {
			
		}
		Compte c = em.find(Compte.class, numero);
		if (c == null) {
			c = new Compte(numero);
			em.persist(c);
			log.info("Compte {} créé", numero);
		}
		c.setValeur(c.getValeur() - combien);
		MessageAction msgAction = new MessageAction(confirmeA, combien);
		em.persist(msgAction);
		log.info("Action {} ajoutée",msgAction.getId());
		log.info("Redirection vers {}",redirectTo);
		return new RedirectBean(redirectTo,c,msgAction.getTransactionId());
	}
}
