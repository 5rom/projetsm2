package tiw5.fourni.banque;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Compte {

	@Id
	private long numero;
	
	private double valeur;

	@SuppressWarnings("unused")
	private Compte() {}
	
	public Compte(long numero) {
		this.numero = numero;
		this.valeur = 0.0;
	}
	
	public double getValeur() {
		return valeur;
	}

	public void setValeur(double valeur) {
		this.valeur = valeur;
	}

	public long getNumero() {
		return numero;
	}
	
}
