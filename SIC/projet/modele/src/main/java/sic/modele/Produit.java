package sic.modele;

public class Produit {

	private long pnum;
	private String pnom;
	
	public Produit(long num, String nom){
		pnum=num;
		pnom=nom;
	}

	public long getPnum() {
		return pnum;
	}

	public void setPnum(long pnum) {
		this.pnum = pnum;
	}

	public String getPnom() {
		return pnom;
	}

	public void setPnom(String pnom) {
		this.pnom = pnom;
	}
}
