package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import persistence.commons.DAOFactory;
import persistence.dao.UsuarioDAO;
import utils.Crypt;

public class Usuario {
	
	private int id;
	private String nombre, pass, TipoPref;
	private double oro, tiempoDisponible;
	private Itinerario itinerario;
	private Boolean admin;
	private Map<String, String> errors;

	public Usuario(int id, String nombre, String pass, String TipoPref, double oro, double tiempoDisponible, Itinerario itinerario, Boolean admin) {
		this.id = id;
		this.nombre = nombre;
		this.pass = pass;
		this.TipoPref = TipoPref;
		this.oro = oro;
		this.tiempoDisponible = tiempoDisponible;
		this.itinerario = itinerario;
		this.admin = admin;
	}
	
	public void setPass(String pass) {
		this.pass = Crypt.hash(pass);
	}

	public boolean isValido() {
		validar();
		return errors.isEmpty();
	}
	
	public Map<String, String> getErrors(){
		return this.errors;
	}

	public void validar() {
		errors = new HashMap<String, String>();
		
		if(this.nombre.length() <= 2)
			errors.put("nombre", "Nombre no valido");

		if (this.oro <= 0) 
			errors.put("costo", "Debe ser positivo");
		
		if (this.tiempoDisponible <= 0) 
			errors.put("tiempoDisponible", "Debe ser positivo");
		
	}

	@Override
	public String toString() {
		return "(" + nombre + ", " + oro + ", " + tiempoDisponible + ", " + TipoPref + ")";
	}

	public String getNombre() {
		return this.nombre;
	}

	public double getOro() {
		return this.oro;
	}
	
	public String getPass() {
		return pass;
	}

	public double getTiempoDisponible() {
		return this.tiempoDisponible;
	}

	public String getTipoPref() {
		return this.TipoPref;
	}

	public boolean puedoComprarSugerible(Sugerible s) {
		return (s.getCosto() <= this.oro && s.getDuracion() <= this.tiempoDisponible);
	}
	
	public boolean checkPassword(String password) {
		// this.password en realidad es el hash del password
		return Crypt.match(password, this.pass);
	}
	
	public int getId() {
		return id;
	}
	
	public Boolean getAdmin() {
		return this.admin;
	}

	public boolean isNull() {
		return false;
	}
	public void comprarSugerible(Sugerible s) {
		  UsuarioDAO ud = DAOFactory.getUsuarioDAO();
		  this.oro -= s.getCosto(); 
		  this.tiempoDisponible -= s.getDuracion();
		  ud.update(this);
		  ud.aniadirVisita(this.id, s);
	}

	public Itinerario getItinerario() {
		return itinerario;
	}
	
	public boolean yaCompreSugerible(Sugerible s) {
		int i = 0;
		boolean resultado = false;
		while (!resultado && i < this.itinerario.getVisitas().size()) {
			if (this.itinerario.getVisitas().get(i++).tengoSugerible(s))
				resultado = true;
		}
		return resultado;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(nombre, other.nombre);
	}

	
}
