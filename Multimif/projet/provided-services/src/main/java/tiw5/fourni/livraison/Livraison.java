package tiw5.fourni.livraison;

import java.io.StringReader;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.EndpointReference;
import javax.xml.ws.WebServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.univ_lyon1.master_info.m2ti.tiw5.services.livraison.feedback.InformePriseEnCompte;
import fr.univ_lyon1.master_info.m2ti.tiw5.services.livraison.feedback.LivraisonEffectuee;
import fr.univ_lyon1.master_info.m2ti.tiw5.services.livraison.feedback.LivraisonFeedback;

@Entity
@NamedQueries(@NamedQuery(name = "findAllLivraisons", query = "SELECT l from Livraison l"))
public class Livraison {

	private static final Logger log = LoggerFactory.getLogger(Livraison.class);

	public static enum Etat {
		Demandee, EnAttente, EnCours, Livree
	}

	@Id
	@GeneratedValue
	private long id;

	private String feedbackEpr;

	private String depuis;

	private String vers;

	private String idCmd;

	@Temporal(TemporalType.DATE)
	private Date date;

	private Etat etat;

	@SuppressWarnings("unused")
	private Livraison() {
	}

	public Livraison(String feedbackEpr, String depuis, String vers,
			String idCmd) {
		this.feedbackEpr = feedbackEpr;
		this.depuis = depuis;
		this.vers = vers;
		this.idCmd = idCmd;
		this.date = new Date();
		this.etat = Etat.Demandee;
	}

	public long getId() {
		return id;
	}

	public String getFeedbackEpr() {
		return feedbackEpr;
	}

	public String getDepuis() {
		return depuis;
	}

	public String getVers() {
		return vers;
	}

	public String getIdCmd() {
		return idCmd;
	}

	public Date getDate() {
		return date;
	}

	public Etat getEtat() {
		return etat;
	}

	public Etat miseAJour() throws DatatypeConfigurationException {
		switch (etat) {
		case Demandee: {
			date.setTime(date.getTime() + 1000 * 60 * 60);
			etat = Etat.EnAttente;
			try {
				InformePriseEnCompte info = new InformePriseEnCompte();
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(date);
				info.setDate(DatatypeFactory.newInstance()
						.newXMLGregorianCalendar(cal));
				info.setIdCommande(idCmd);
				getFeedbackService().informePriseEnCompteOp(info);
				log.debug("livraison {}: {} -> {}", new Object[] { id,
						Etat.Demandee, Etat.EnAttente });
			} catch (WebServiceException e) {
				log.error("erreur lors de l'envoi de la confirmation", e);
			}
			break;
		}
		case EnAttente: {
			etat = Etat.EnCours;
			log.debug("livraison {}: {} -> {}", new Object[] { id,
					Etat.EnAttente, Etat.EnCours });
			break;
		}
		case EnCours: {
			try {
				XMLGregorianCalendar cal = DatatypeFactory.newInstance()
						.newXMLGregorianCalendar(new GregorianCalendar());
				LivraisonEffectuee info = new LivraisonEffectuee();
				info.setDate(cal);
				info.setIdCommande(idCmd);
				getFeedbackService().informeLivraisonOp(info);
			} catch (WebServiceException e) {
				log.error(
						"Erreur lors de 'envoie de la confirmation de livraison",
						e);
			}
			etat = Etat.Livree;
			log.debug("livraison {}: {} -> {}", new Object[] { id,
					Etat.EnCours, Etat.Livree });
			break;
		}
		case Livree: {
			log.warn("Livraison {} déjà livrée", id);
			break;
		}
		}
		return etat;
	}

	private LivraisonFeedback getFeedbackService() {
		StreamSource ss = new StreamSource(new StringReader(feedbackEpr));
		EndpointReference epr = EndpointReference.readFrom(ss);
		return epr.getPort(LivraisonFeedback.class);
	}

}
