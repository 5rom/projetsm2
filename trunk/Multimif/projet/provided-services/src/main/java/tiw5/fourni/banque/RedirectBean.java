package tiw5.fourni.banque;

public class RedirectBean {

	public String url;
	public Compte compte;
	public String trId;
	
	public RedirectBean(String url, Compte compte, String trId) {
		this.url = url;
		this.compte = compte;
		this.trId = trId;
	}
	
}
