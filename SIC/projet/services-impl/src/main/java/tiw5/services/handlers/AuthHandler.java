package tiw5.services.handlers;

import java.util.logging.Logger;

import javax.xml.ws.ProtocolException;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;



/**
 * Classe AuthHandler
 * Intercepteur logique d'authentification pour les utilisateurs.
 * Vérifie que le couple user/password fait bien partie des logins autorises.
 * La liste des utilisateurs autorisés est stockée dans un fichier XML
 * dont le chemin est paramétrable ici 
 * @author David CRESCENCE <crescence.david@gmail.com> et Sébastien FAURE <sebastien.faure3@gmail.com>
 * UCBL M2TI 2011-2012 
 */
public class AuthHandler  implements LogicalHandler<LogicalMessageContext> {

	/**
	 * Le logger
	 */
	private static Logger log = Logger.getLogger("AuthHandler");
	
	/**
	 * Methode close
	 * Appelee a la fin d'un echange de message
	 */
	@Override
	public void close(MessageContext arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Methode handleFault
	 * Appelee en cas d'erreur de traitement d'un message
	 */
	@Override
	public boolean handleFault(LogicalMessageContext arg0) {
		log.warning("AuthHandler: Erreur rencontrée...");				
		// Le traitement continue
		return true;
	}


	/**
	 * Methode handleMessage
	 * Traitement d'un message: recuperation de l'utilisateur 
	 * du contexte et controle de ses identifiants pour authentification
	 * 
	 */
	@Override
	public boolean handleMessage(LogicalMessageContext arg0) {
		boolean sortant = (Boolean) arg0.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (!sortant){
			String userValue=(String) arg0.get("user");
			String passwordValue=(String) arg0.get("password");
	/*		UtilisateurID currentUserID = new UtilisateurID(userValue, passwordValue);
			//Ici on regarde dans le xml s'il y est
			//S'il n'y est pas on genere l'exception ProtocolException 
			if (Utils.checkAuthenticationFromXMLFile("C:\\Users\\Seb\\eclipse-projects\\svn\\TIW5\\tp3\\projet\\listeIdentifiants.xml", currentUserID)){
				log.info("AuthHandler: Utilisateur trouvé! Authentification OK!");
			} else {
				log.warning("AuthHandler: Utilisateur non trouvé! Authentification echouée!");
				throw new ProtocolException();
			}*/
		}
		
		// On continue le traitement des messages
		return true;
	}

}
