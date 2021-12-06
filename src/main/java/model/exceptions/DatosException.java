package model.exceptions;

public class DatosException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DatosException(String msg) {
		super(msg);
	}
	
	public String getMensaje() {
		return this.getMessage();
	}
}
