package tiw5.fourni.banque;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.ws.WebServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

public class MessageActionDAO {

	private final static Logger log = LoggerFactory
			.getLogger(MessageActionDAO.class);

	@PersistenceContext
	private EntityManager em;

	public List<MessageAction> getAllActions() {
		Query query = em.createNamedQuery("findAllMessageActions");
		return query.getResultList();
	}

	public MessageAction getMessageAction(long id) {
		return em.find(MessageAction.class, id);
	}

	@Transactional
	public void sendMessageAction(long id) {
		MessageAction action = em.find(MessageAction.class, id);
		try {
			action.sendMessage();
		} catch (WebServiceException e) {
			log.error("erreur lors de l'envoi du message", e);
		}
		log.info("Message envoye a {}", action.getAdresseXMLAsString());
		em.remove(action);
	}

}
