package sic.modele;

public class Composition {
	private long pmajeur;
	private long pmineur;
	
	public Composition(long majeur, long mineur){
		pmajeur=majeur;
		pmineur=mineur;
	}

	public long getPmajeur() {
		return pmajeur;
	}

	public void setPmajeur(long pmajeur) {
		this.pmajeur = pmajeur;
	}

	public long getPmineur() {
		return pmineur;
	}

	public void setPmineur(long pmineur) {
		this.pmineur = pmineur;
	}
	
	
}
