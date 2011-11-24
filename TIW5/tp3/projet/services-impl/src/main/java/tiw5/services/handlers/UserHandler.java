package tiw5.services.handlers;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class UserHandler implements SOAPHandler<SOAPMessageContext>{

	@Override
	public void close(MessageContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		//for response message only, true for outbound messages, false for inbound
		if(!isRequest){

			try{
				SOAPMessage soapMsg = context.getMessage();
				SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
				SOAPHeader soapHeader = soapEnv.getHeader();

				//if no header, add one
				if (soapHeader == null){
					soapHeader = soapEnv.addHeader();
				} else {
					// s'il y en a un on cherche s'il y'a user et password
					if (soapHeader.getElementsByTagNameNS("http://master-info.univ-lyon1.fr/M2TI/TIW5/services", "user").getLength()>0){
						// Element user present
						//System.out.println("user present:"+soapHeader.getElementsByTagNameNS("http://master-info.univ-lyon1.fr/M2TI/TIW5/services", "user").item(0).getTextContent());
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

		//continue other handler chain
		return true;
	}

	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}
	




}
