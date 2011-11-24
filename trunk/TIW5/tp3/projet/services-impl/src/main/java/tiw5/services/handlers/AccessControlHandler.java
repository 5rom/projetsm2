package tiw5.services.handlers;

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

import tiw5.user.UtilisateurID;
import tiw5.util.services.util.Utils;

public class AccessControlHandler implements LogicalHandler<LogicalMessageContext> {
	private Transformer copy;

	public AccessControlHandler() {
		try {
			copy = TransformerFactory.newInstance().newTransformer();
		} catch (TransformerConfigurationException e) {
			throw new RuntimeException(e);
		}
	}
	
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
	@Override
	public void close(MessageContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean handleFault(LogicalMessageContext arg0) {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean handleMessage(LogicalMessageContext arg0) {
		boolean sortant = (Boolean) arg0.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (!sortant){
			if (getMainMessageElement(arg0).contains("addAlbumDescription")){
				String userValue=(String) arg0.get("user");
				String passwordValue=(String) arg0.get("password");
				UtilisateurID currentUserID = new UtilisateurID(userValue, passwordValue);
				//Ici on regarde dans le xml s'il y est
				//S'il n'y est pas on genere l'exception ProtocolException 
				if (Utils.checkCanEditAlbumFromXMLFile("C:\\Users\\Seb\\eclipse-projects\\svn\\TIW5\\tp3\\projet\\listeIdentifiants.xml", currentUserID)){
					System.out.println("AccessControlHandler:L'utilisateur a le droit d'ajouter des albums!");
				} else {
					System.out.println("AccessControlHandler:L'utilisateur n'a pas le droit d'ajouter des albums! Erreur!");
					throw new ProtocolException();
				}
			}
		}
		return true;
	}

}
