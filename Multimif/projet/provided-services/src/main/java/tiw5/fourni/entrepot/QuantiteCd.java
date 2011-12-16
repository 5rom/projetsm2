package tiw5.fourni.entrepot;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"quantite","idCd"})
public class QuantiteCd implements Serializable {

	private static final long serialVersionUID = 1L;

	public int quantite;
	
	public long idCd;
	
	public QuantiteCd() {};
	
	public QuantiteCd(int quantite, long idCd) {
		this.quantite = quantite;
		this.idCd = idCd;
	}
	
}
