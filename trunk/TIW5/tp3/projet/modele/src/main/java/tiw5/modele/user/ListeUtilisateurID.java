package tiw5.modele.user;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;




/**
 * Classe ListeUtilisateurID
 * Représente une liste d'UtilisateurID, classe d'authentification d'utilisateurs (identifiants et droit à modification d'albums)
 * Nécessité de créé cette classe pour pouvoir faire du marshalling/unmarshalling de plusieurs
 * UtilisateurID à la fois.
 * @author David CRESCENCE <crescence.david@gmail.com> et Sébastien FAURE <sebastien.faure3@gmail.com>
 * UCBL M2TI 2011-2012 
 */
@XmlRootElement(name = "listeUtilisateurID", namespace = ServicesImplConstants.NS)
public class ListeUtilisateurID {
	
	/**
	 * La liste d'UtilisateurID
	 */
	@XmlElement(name="userID",namespace=ServicesImplConstants.NS)
	private ArrayList<UtilisateurID> users=new ArrayList<UtilisateurID>();
	
	/**
	 * Constructeur vide pour JAXB
	 */
	public ListeUtilisateurID(){
		
	}
	
	/**
	 * Accesseur à la liste d'UtilisateurID
	 * @return la liste
	 */
	public ArrayList<UtilisateurID> getUserIDS(){
		return users;
	}
	
	/**
	 * Modifieur de la liste d'UtilisateurID
	 * @param aLID la liste
	 */
    @XmlTransient
	public void setUserIDs(ArrayList<UtilisateurID> aLID){
		users=aLID;
	}
    
	/**
	 * Ajout d'un UtilisateurID à la liste d'UtilisateurID
	 * @param uID L'UtilisateurID
	 */
	public void addUserID(UtilisateurID uID){
		users.add(uID);
	}
    
	/**
	 * Retrait d'un UtilisateurID de la liste d'UtilisateurID
	 * @param uID L'UtilisateurID à retirer
	 */
	public void removeUserID(UtilisateurID uID){
		users.remove(uID);
	}     

    /**
     * Vérification qu'un UtilisateurID est bien dans la liste des autorisés.
     * @param userID L'utilisateur à teser
     * @return vrai si l'utilisateur est autorisé, faux sinon
     */
	public Boolean isCorrectUser(UtilisateurID userID) {
		Boolean found=false;
		for (int i=0;i<users.size();i++){
			if ((users.get(i).getUser().equals(userID.getUser())) && (users.get(i).getPassword().equals(userID.getPassword()))){
				return true;
			}
		}
		return found;
	}

    /**
     * Vérification qu'un UtilisateurID est bien dans la liste des autorisés
     * et qu'il a le droit d'ajouter/modifier des albums.
     * @param userID L'utilisateur à teser
     * @return vrai si l'utilisateur est a le droit, faux sinon
     */
	public Boolean canUserEditAlbum(UtilisateurID userID) {
		Boolean found=false;
		for (int i=0;i<users.size();i++){
			if ((users.get(i).getUser().equals(userID.getUser())) && (users.get(i).getPassword().equals(userID.getPassword()))){
				// On a trouvé l'utilisateur, maintenant on renvoie son droit a modifier les albums
				return(users.get(i).getCanEditAlbum());
			}
		}
		return found;
	}	
	
}