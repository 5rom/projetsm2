package tiw5.services.handlers;

import java.util.Set;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 * Classe UserHandler
 * Intercepteur SOAP d'enrichissement du contexte par les 
 * identifiants d'un utilisateur passés dans le header de la requète reçue.
 * @author David CRESCENCE <crescence.david@gmail.com> et Sébastien FAURE <sebastien.faure3@gmail.com>
 * UCBL M2TI 2011-2012 
 */
public class UserHandler implements SOAPHandler<SOAPMessageContext>{

	/**
	 * Logger
	 */
	private static Logger log = Logger.getLogger("LogHandler");
	
	
	/**
	 * Methode close
	 * Appelee a la fin d'un echange de message
	 */
	@Override
	public void close(MessageContext context) {
	}

	/**
	 * Methode handleFault
	 * Appelee en cas d'erreur de traitement d'un message
	 */
	@Override
	public boolean handleFault(SOAPMessageContext context) {
		log.warning("UserHandler: Erreur rencontrée...");				
		// Le traitement continue		
		return true;
	}

	/**
	 * Methode handleMessage
	 * Traitement d'un message : recuperation de l'utilisateur 
	 * du contexte et controle de son droit de modification d'albums
	 */	
	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		Boolean sortant = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		// Seulement pour les messages entrants
		if(!sortant){

			try{
				// Récupération du header
				SOAPMessage soapMsg = context.getMessage();
				SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
				SOAPHeader soapHeader = soapEnv.getHeader();

				// S'il n'y a pas d'header on en ajoute un
				if (soapHeader == null){
					soapHeader = soapEnv.addHeader();
				} else {
					// s'il y en a un on cherche s'il y'a user et password
					if (soapHeader.getElementsByTagNameNS("http://master-info.univ-lyon1.fr/M2TI/TIW5/services", "user").getLength()>0){
						// Element user present
						context.put("user", (soapHeader.getElementsByTagNameNS("http://master-info.univ-lyon1.fr/M2TI/TIW5/services", "user").item(0).getTextContent()));
					}
					if (soapHeader.getElementsByTagNameNS("http://master-info.univ-lyon1.fr/M2TI/TIW5/services", "password").getLength()>0){
						// Element password present
						context.put("password", (soapHeader.getElementsByTagNameNS("http://master-info.univ-lyon1.fr/M2TI/TIW5/services", "password").item(0).getTextContent()));
					}		    	 
				}
			}catch(SOAPException e){
				System.err.println(e);
			}
		}

		// On continue le traitement des messages
		return true;
	}

	/**
	 * Methode getHeaders():
	 * Renvoie les headers
	 */
	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}
	




}
