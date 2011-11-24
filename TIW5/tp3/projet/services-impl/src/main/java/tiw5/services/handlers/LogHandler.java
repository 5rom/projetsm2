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

public class LogHandler implements LogicalHandler<LogicalMessageContext> {

	private static Logger log = Logger.getLogger("IntercepteurDeLog");

	private Transformer copy;

	public LogHandler() {
		try {
			copy = TransformerFactory.newInstance().newTransformer();
		} catch (TransformerConfigurationException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getOp(LogicalMessageContext arg0) {
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
		log.warning("Erreur: " + getOp(arg0));
		return true; // Le traitement continue
	}

	@Override
	public boolean handleMessage(LogicalMessageContext arg0) {
		boolean sortant = (Boolean) arg0
				.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		log.info(new Date().toString()+" : " +"User=\""+arg0.get("user")+"\" +Message " + (sortant ? "sortant" : "entrant") + ": "
				+ getOp(arg0));
		return true;
	}

}
