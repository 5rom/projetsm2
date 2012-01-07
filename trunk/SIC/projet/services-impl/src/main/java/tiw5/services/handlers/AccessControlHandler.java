package tiw5.services.handlers;

import java.util.logging.Logger;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.ws.ProtocolException;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Node;



/**
 * Classe AccessControlHandler
 * Intercepteur logique de controle d'acces pour les utilisateurs.
 * Vérifie que l'utilisateur (renseigné dans le contexte) a bien le droit de modifier un album.
 * La liste des utilisateurs avec leurs droits est stockée dans un fichier XML dont le chemin est parametrable ici.
 * @author David CRESCENCE <crescence.david@gmail.com> et Sébastien FAURE <sebastien.faure3@gmail.com>
 * UCBL M2TI 2011-2012 
 */
public class AccessControlHandler implements LogicalHandler<LogicalMessageContext> {

	/**
	 * Le logger
	 */
	private static Logger log = Logger.getLogger("AccessControlHandler");
	
	/**
	 * Transformer pour le message en XML
	 */
	private Transformer copy;

	/**
	 * Constructeur
	 */
	public AccessControlHandler() {
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
		log.warning("AccessControlHandler: Erreur rencontrée: "+getMainMessageElement(arg0));				
		// Le traitement continue
		return true;
	}


	/**
	 * Methode handleMessage
	 * Traitement d'un message : recuperation de l'utilisateur 
	 * du contexte et controle de son droit de modification d'albums
	 */
	@Override
	public boolean handleMessage(LogicalMessageContext arg0) {
		boolean sortant = (Boolean) arg0.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		
		// Si il y a bien un utilisateur dans le contexte
		if ((!sortant) && (getMainMessageElement(arg0).contains("addAlbumDescription")))
		{	
			// Récupération de l'utilisateur depuis le contexte
			String userValue=(String) arg0.get("user");
			String passwordValue=(String) arg0.get("password");
		/*	UtilisateurID currentUserID = new UtilisateurID(userValue, passwordValue);
		 
			// Contrôle d'accès pour cet utilisateur
			if (Utils.checkCanEditAlbumFromXMLFile("C:\\Users\\Seb\\eclipse-projects\\svn\\TIW5\\tp3\\projet\\listeIdentifiants.xml", currentUserID)){
				log.info("AccessControlHandler:L'utilisateur a le droit d'ajouter/modifier des albums!");
			} else {
				log.warning("AccessControlHandler:L'utilisateur n'a pas le droit d'ajouter/modifier des albums! Erreur!");
				throw new ProtocolException();
			}*/
		}
		
		// On continue de traiter les messages
		return true;
	}

}
