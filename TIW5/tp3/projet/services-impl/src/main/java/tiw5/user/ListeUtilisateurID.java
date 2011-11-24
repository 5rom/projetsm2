package tiw5.user;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;



@XmlRootElement(name = "listeUtilisateurID", namespace = "http://master-info.univ-lyon1.fr/M2TI/TIW5/services")
public class ListeUtilisateurID {
	
	@XmlElement(name="userID",namespace="http://master-info.univ-lyon1.fr/M2TI/TIW5/services")
	private ArrayList<UtilisateurID> users;
	
	
	public ListeUtilisateurID(){
		
	}
	
	public ArrayList<UtilisateurID> getUserIDS(){
		return users;
	}
	
    @XmlTransient
	public void setUserIDs(ArrayList<UtilisateurID> aLID){
		users=aLID;
	}

	public Boolean isCorrectUser(UtilisateurID userID) {
		Boolean found=false;
		for (int i=0;i<users.size();i++){
			if ((users.get(i).getUser().equals(userID.getUser())) && (users.get(i).getPassword().equals(userID.getPassword()))){
				return true;
			}
		}
		return found;
	}
	
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
