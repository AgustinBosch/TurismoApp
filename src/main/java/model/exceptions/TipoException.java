package model.exceptions;

public class TipoException extends Exception {

	private static final long serialVersionUID = 1L;

	public TipoException(String msg) {
		super(msg);
	}
	
	public String getMensaje() {
		return this.getMessage();
	}
}
