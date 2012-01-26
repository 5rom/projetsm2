package sic.modele;

/**
 * Classe Produit
 * Represente une produit : avec son identifiant et son nom
 * Créée par Sébastien Faure et David Crescence
 * @author David CRESCENCE <crescence.david@gmail.com> et Sébastien FAURE <sebastien.faure3@gmail.com>
 * UCBL M2TI 2011-2012 
 */
public class Produit {

	/**
	 * Identifiant du produit
	 */
	private long pnum;
	private String pnom;
	
	/**
	 * Constructeur d'un produit
	 * @param num l'identifiant du produit
	 * @param nom le nom du produit
	 */
	public Produit(long num, String nom){
		pnum=num;
		pnom=nom;
	}

	/**
	 * Getter sur l'identifiant du produit
	 * @return l'identifiant du produit
	 */
	public long getPnum() {
		return pnum;
	}

	/**
	 * Setter de l'identifiant du produit
	 * @param pnum l'identifiant du produit
	 */
	public void setPnum(long pnum) {
		this.pnum = pnum;
	}

	/**
	 * Getter sur le nom du produit
	 * @return le nom du produit
	 */
	public String getPnom() {
		return pnom;
	}

	/**
	 * Setter sur le nom du produit
	 * @param pnom le nom du produit
	 */
	public void setPnom(String pnom) {
		this.pnom = pnom;
	}
}
