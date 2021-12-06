package model.exceptions;

public class DatosNegativosException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public DatosNegativosException(String msg) {
		super(msg);
	}
	
	public String getMensaje() {
		return this.getMessage();
	}
}
