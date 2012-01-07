package tiw5.services.handlers;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;

import java.util.Date;
import java.util.logging.Logger;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * Classe LogHandler
 * Intercepteur logique de log.
 * Affiche l'element principal des messages reçus et envoyes ainsi que la date.
 * @author David CRESCENCE <crescence.david@gmail.com> et Sébastien FAURE <sebastien.faure3@gmail.com>
 * UCBL M2TI 2011-2012 
 */
public class LogHandler implements LogicalHandler<LogicalMessageContext> {

	/**
	 * Logger
	 */
	private static Logger log = Logger.getLogger("LogHandler");

	/**
	 * Transformer pour le message en XML
	 */	
	private Transformer copy;

	/**
	 * Constructeur
	 */
	public LogHandler() {
		try {
			copy = TransformerFactory.newInstance().newTransformer();
		} catch (TransformerConfigurationException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Methode getMainMessageElement:
	 * Permet de recuperer l'element principal du message en XML
	 * @param arg0 Le contexte du message
	 * @return String Le nom de l'element principal du message
	 */
	public String getMainMessageElement(LogicalMessageContext arg0) {
			Source payload = arg0.getMessage().getPayload();
			DOMResult dom = new DOMResult();
			try {
				copy.transform(payload, dom);
			} catch (TransformerException e) {
				throw new RuntimeException(e);
			}
			Node root = dom.getNode();
			if (root.getNodeType() == Node.DOCUMENT_FRAGMENT_NODE) {
				root = ((DocumentFragment)root).getFirstChild();
			} else if (root.getNodeType() == Node.DOCUMENT_NODE) {
				root = ((Document)root).getDocumentElement();
			}
			return root.getNodeName();
		}	

	/**
	 * Methode close
	 * Appelee a la fin d'un echange de message
	 */
	@Override
	public void close(MessageContext arg0) {
	}

	/**
	 * Methode handleFault
	 * Appelee en cas d'erreur de traitement d'un message
	 */
	@Override
	public boolean handleFault(LogicalMessageContext arg0) {
		log.warning("LogHandler: Erreur rencontrée: " + getMainMessageElement(arg0));
		// Le traitement continue
		return true;
	}

	/**
	 * Methode handleMessage
	 * Traitement d'un message : affichage de la date et de l'element 
	 * principal du message (avec en plus le login de l'utilisateur 
	 * si c'est un message entrant)
	 */	
	@Override
	public boolean handleMessage(LogicalMessageContext arg0) {
		boolean sortant = (Boolean) arg0.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (sortant){
			// Message sortant
			log.info("LogHandler:"+new Date().toString()+" : Element principal du message sortant"+ ": "+ getMainMessageElement(arg0));	
		} else {
			// Message entrant : on affiche en plus le nom de l'utilisateur car il a été ajouté au contexte par le handler SOAP userHandler
			log.info("LogHandler:"+new Date().toString()+" : " +"User=\""+arg0.get("user")+"\""+" , Element principal du message entrant"+ ": "+ getMainMessageElement(arg0));
		}
		
		// On continue le traitement des messages
		return true;
	}

}
