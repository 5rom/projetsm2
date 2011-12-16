package tiw5.fourni.livraison;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.EndpointReference;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import javax.xml.ws.wsaddressing.W3CEndpointReference;

import org.springframework.transaction.annotation.Transactional;

@WebService(targetNamespace = LivraisonInterface.NAMESPACE)
public interface LivraisonInterface {

	public static final String NAMESPACE = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services/livraison";

	@WebMethod(action = NAMESPACE + "/tarif")
	@RequestWrapper(localName = "tarif", targetNamespace = NAMESPACE)
	@ResponseWrapper(localName = "tarifInfo", targetNamespace = NAMESPACE)
	@WebResult(name = "valeur", targetNamespace = NAMESPACE)
	public abstract Double tarif(
			@WebParam(name = "depuis", targetNamespace = NAMESPACE) String depuis,
			@WebParam(name = "vers", targetNamespace = NAMESPACE) String vers,
			@WebParam(name = "quantite", targetNamespace = NAMESPACE) int quantite);

	@WebMethod(action = NAMESPACE + "/demande")
	@RequestWrapper(localName = "demande", targetNamespace = NAMESPACE)
	@ResponseWrapper(localName = "infosLivraison", targetNamespace = NAMESPACE)
	@WebResult(name = "datePrevue", targetNamespace = NAMESPACE)
	@Transactional
	public abstract Date demandeLivraison(
			@WebParam(name = "adresseFeedback", targetNamespace = NAMESPACE) W3CEndpointReference epr,
			@WebParam(name = "depuis", targetNamespace = NAMESPACE) String depuis,
			@WebParam(name = "vers", targetNamespace = NAMESPACE) String vers,
			@WebParam(name = "idCommande", targetNamespace = NAMESPACE) String id);

}