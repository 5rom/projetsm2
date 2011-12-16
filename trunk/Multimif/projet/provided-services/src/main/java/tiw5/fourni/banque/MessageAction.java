package tiw5.fourni.banque;

import java.io.StringReader;
import java.io.StringWriter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.EndpointReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.univ_lyon1.master_info.m2ti.tiw5.services.banque.BanqueClient;

@Entity
@NamedQueries(@NamedQuery(name = "findAllMessageActions", query = "SELECT a FROM MessageAction a"))
public class MessageAction {

	private static final Logger log = LoggerFactory
			.getLogger(MessageAction.class);

	@Id
	@GeneratedValue
	private long id;

	private double valeur;

	@Column(name = "adresse")
	private String adresseXML;

	@Transient
	private EndpointReference adresse;

	private String getAdresseXML() {
		return adresseXML;
	}

	public String getAdresseXMLAsString() {
		return getAdresseXML();
	}

	private void setAdresseXML(String adresseXML) {
		this.adresseXML = adresseXML;
		StreamSource ss = new StreamSource(new StringReader(adresseXML));
		adresse = EndpointReference.readFrom(ss);
	}

	public MessageAction() {
	}

	public MessageAction(String adresse, double valeur) {
		this.valeur = valeur;
		setAdresseXML(adresse);
	}

	public EndpointReference getAdresse() {
		if (adresse == null && adresseXML != null) {
			setAdresseXML(adresseXML);
		}
		return adresse;
	}

	public void setAdresse(EndpointReference adresse) {
		this.adresse = adresse;
		StringWriter w = new StringWriter();
		StreamResult sr = new StreamResult(w);
		adresse.writeTo(sr);
		adresseXML = w.toString();
	}

	public long getId() {
		return id;
	}

	@Transient
	public String getTransactionId() {
		return "banque" + getId();
	}

	public void setValeur(double valeur) {
		this.valeur = valeur;
	}

	public double getValeur() {
		return valeur;
	}

	public void sendMessage() {
		try {
			BanqueClient eShop = getAdresse().getPort(BanqueClient.class);
			eShop.confirmOp(getTransactionId(), getValeur());
		} catch (RuntimeException e) {
			log.error("Erreur dans sendMessage", e);
			throw e;
		}
	}

}
