package tiw5.fourni.entrepot;

public class NonDisponibleException extends Exception {
	private static final long serialVersionUID = 1018758502746113139L;
	public QuantiteCd nonDisponible;
	
	public NonDisponibleException(QuantiteCd nonDisponible) {
		super("le cd "+nonDisponible.idCd+"n'est pas disponible");
		this.nonDisponible = nonDisponible;
	}

}
