package tiw5.fourni.entrepot;

import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(targetNamespace = EntrepotService.NAMESPACE)
public interface EntrepotInterface {

	public static final String NAMESPACE = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services/entrepot";

	@RequestWrapper(localName = "disponibilite", targetNamespace = NAMESPACE)
	@ResponseWrapper(localName = "disponibiliteInfo", targetNamespace = NAMESPACE)
	@WebMethod(action = NAMESPACE + "/disponibilite")
	@WebResult(name = "cd", targetNamespace = NAMESPACE)
	public abstract Collection<DispoCd> disponibilite(
			@WebParam(targetNamespace = NAMESPACE, name = "cd") Collection<QuantiteCd> demande);

	@RequestWrapper(localName = "commande", targetNamespace = NAMESPACE)
	@WebMethod(action = NAMESPACE + "/commande")
	@ResponseWrapper(localName = "id", targetNamespace = NAMESPACE)
	public abstract String commande(
			@WebParam(targetNamespace = NAMESPACE, name = "cd") Collection<QuantiteCd> demande)
			throws NonDisponibleException;

	@RequestWrapper(localName = "getEmplacement", targetNamespace = NAMESPACE)
	@WebMethod(action = NAMESPACE + "/commande")
	@ResponseWrapper(localName = "emplacement", targetNamespace = NAMESPACE)
	@WebResult(name = "ville", targetNamespace = NAMESPACE)
	public abstract String emplacement();

}