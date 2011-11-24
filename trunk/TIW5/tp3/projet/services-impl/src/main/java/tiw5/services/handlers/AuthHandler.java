package tiw5.services.handlers;

import java.util.Date;

import javax.xml.ws.ProtocolException;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;

import tiw5.user.UtilisateurID;
import tiw5.util.services.util.Utils;

public class AuthHandler  implements LogicalHandler<LogicalMessageContext> {

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
		// TODO Auto-generated method stub
		boolean sortant = (Boolean) arg0.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (!sortant){
			System.out.println("AuthHandler:"+new Date().toString()+" : " +"User=\""+arg0.get("user")+"\" + Password=\""+arg0.get("password")+"\"");
			
			String userValue=(String) arg0.get("user");
			String passwordValue=(String) arg0.get("password");
			System.out.println("Nouvel utilisateur :"+userValue+" - "+passwordValue);
			UtilisateurID currentUserID = new UtilisateurID(userValue, passwordValue);
			//Ici on regarde dans le xml s'il y est
			//S'il n'y est pas on genere l'exception ProtocolException 
			if (Utils.checkAuthenticationFromXMLFile("C:\\Users\\Seb\\eclipse-projects\\svn\\TIW5\\tp3\\projet\\listeIdentifiants.xml", currentUserID)){
				System.out.println("AuthHandler: Utilisateur trouvé! Authentification OK!");
			} else {
				System.out.println("AuthHandler: Utilisateur non trouvé! Authentification echouée!");
				throw new ProtocolException();
			}
		}
		return true;
	}

}
