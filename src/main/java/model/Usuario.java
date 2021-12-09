package model;

import java.util.ArrayList;
import java.util.Objects;

import model.exceptions.DatosNegativosException;
import persistence.commons.DAOFactory;
import persistence.dao.UsuarioDAO;
import utils.Crypt;

public class Usuario {
	
	private int id;
	private String name, pass, TipoPref;
	private double oro, tiempoDisponible;
	private Itinerario itinerario;
	private Boolean admin;

	public Usuario(int id, String name, String pass, String TipoPref, double oro, double tiempoDisponible, Itinerario itinerario, Boolean admin)
			throws DatosNegativosException {
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.TipoPref = TipoPref;
		this.oro = validarOro(oro);
		this.tiempoDisponible = validarTiempoDisponible(tiempoDisponible);
		this.itinerario = itinerario;
		this.admin = admin;
	}
	
	public void setPass(String pass) {
		this.pass = Crypt.hash(pass);
	}

	/*
	 * PRE : Recibe un tiempo disponible del usuario POST : Retorna el valor de
	 * tiempo disponible del usuario en caso de que sea valido caso contrario lanza
	 * la exception DatosNegativosException
	 */
	private double validarTiempoDisponible(double tiempoDisponible) throws DatosNegativosException {
		if (tiempoDisponible < 0) {
			throw new DatosNegativosException("El tiempo disponible de: " + this.name + " es negativo");
		}
		return tiempoDisponible;
	}

	/*
	 * PRE : Recibe el valor del oro que posee el usuario POST : Retorna el valor
	 * del oro en caso de que sea valido, caso contrario lanza la exception
	 * DatosNegativosException
	 */
	private double validarOro(double oro) throws DatosNegativosException {
		if (oro < 0) {
			throw new DatosNegativosException("El oro de: " + this.name + " es negativo");
		}
		return oro;
	}

	@Override
	public String toString() {
		return "(" + name + ", " + oro + ", " + tiempoDisponible + ", " + TipoPref + ")";
	}

	public String getName() {
		return this.name;
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
		return Objects.hash(name);
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
		return Objects.equals(name, other.name);
	}

	
}
