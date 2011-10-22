package tiw5.client;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import fr.univ_lyon1.master_info.m2ti.tiw5.services.Album;

/**
 * Classe AlbumDescription.java
 * Classe d'encapsulation d'un album pour faciliter
 * la (dé)sérialisation JAXB des albums car <AlbumDescription> devient
 * la racine du document xml et l'album l'élément qu'elle contient
 * @author David CRESCENCE <crescence.david@gmail.com> et Sébastien FAURE <sebastien.faure3@gmail.com>
 * UCBL M2TI 2011-2012
 */
@XmlRootElement
public class AlbumDescription {

	/**
	 * L'album à décrire : element xml album
	 */
	@XmlElement(name="album")
	private Album ab;
	
	/**
	 * Constructeur
	 * @param a L'album
	 */
	public AlbumDescription(Album a){
		ab=a;
	}
	
	/**
	 * Constructeur par défaut : vide
	 */
	public AlbumDescription(){
		
	}
	
	/**
	 * Accesseur sur l'album
	 * @return l'album contenu
	 */
	public Album getAlbum(){
		return ab;
	}
	
}
