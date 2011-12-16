package tiw5.fourni.entrepot;

import java.util.Date;

public class DispoCd extends QuantiteCd {

	private static final long serialVersionUID = 2L;

	public Date dateDispo;
	
	public DispoCd() {}
	
	public DispoCd(int quantite, long cdId, Date dateDispo) {
		super(quantite, cdId);
		this.dateDispo = dateDispo;
	}
	
}
