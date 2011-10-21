package tiw5.client;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import fr.univ_lyon1.master_info.m2ti.tiw5.services.Album;

@XmlRootElement
public class AlbumDescription {

	/**
	 * L'album à décrire
	 */
	@XmlElement(name="album")
	private Album ab;
	
	/**
	 * Constructeur
	 * @param a
	 */
	public AlbumDescription(Album a){
		ab=a;
	}
	
	/**
	 * Constructeur par défaut
	 */
	public AlbumDescription(){
		
	}
	
	/**
	 * Accesseur sur l'album
	 * @return
	 */
	public Album getAlbum(){
		return ab;
	}
	
}
