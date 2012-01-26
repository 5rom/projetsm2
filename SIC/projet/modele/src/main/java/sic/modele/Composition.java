package sic.modele;

/**
 * Classe Composition
 * Represente une composition de produit : le produit majeur "est composé" du produit mineur
 * Créée par Sébastien Faure et David Crescence
 * @author David CRESCENCE <crescence.david@gmail.com> et Sébastien FAURE <sebastien.faure3@gmail.com>
 * UCBL M2TI 2011-2012 
 */
public class Composition {
	
	/**
	 * Produit Majeur de la composition
	 */
	private long pmajeur;

	/**
	 * Produit Mineur de la composition
	 */	
	private long pmineur;
	
	/**
	 * Constructeur d'une composition
	 * @param majeur le produit majeur
	 * @param mineur le produit mineur
	 */
	public Composition(long majeur, long mineur){
		pmajeur=majeur;
		pmineur=mineur;
	}

	/**
	 * Getter sur le produit majeur
	 * @return l'identifiant du produit majeur
	 */
	public long getPmajeur() {
		return pmajeur;
	}

	/**
	 * Setter sur le produit majeur
	 * @param pmajeur l'identifiant du produit majeur
	 */	
	public void setPmajeur(long pmajeur) {
		this.pmajeur = pmajeur;
	}

	/**
	 * Getter sur le produit mineur
	 * @return l'identifiant du produit mineur
	 */	
	public long getPmineur() {
		return pmineur;
	}

	/**
	 * Setter sur le produit mineur
	 * @param pmineur l'identifiant du produit mineur
	 */
	public void setPmineur(long pmineur) {
		this.pmineur = pmineur;
	}
	
	
}
