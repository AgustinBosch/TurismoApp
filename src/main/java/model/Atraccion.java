package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import persistence.commons.DAOFactory;

public class Atraccion implements Sugerible {

	private final String nombre;
	private final double costo;
	private final String genero;
	private final double tiempoPromedio;
	private int cupo;
	private int id;
	private String descripcion;
	private Map<String, String> errors;

	public Atraccion(int id, String nombre, double costo, String genero, double tiempoPromedio, int cupo,
			String descripcion) {
		this.nombre = nombre;
		this.genero = genero;
		this.costo = costo;
		this.cupo = cupo;
		this.tiempoPromedio = tiempoPromedio;
		this.id = id;
		this.descripcion = descripcion;
	}

	public boolean isNull() {
		return false;
	}

	public boolean isValido() {
		validar();
		return errors.isEmpty();
	}

	public void validar() {
		errors = new HashMap<String, String>();

		if (this.nombre.length() <= 2) 
			errors.put("nombre", "Debe ser mayor o igual a 3");
		
		if (this.costo <= 0) 
			errors.put("costo", "Debe ser positivo");
		
		if (this.tiempoPromedio <= 0) 
			errors.put("tiempo", "Debe ser positivo");
		
		if (this.cupo <= 0) 
			errors.put("cupo", "Debe ser positivo");
		
		if (this.descripcion.length() <= 2) 
			errors.put("descripcion", "Debe ser mayor o igual a 3");
		
	}

	public void ocuparLugar() {
		this.cupo--;
		DAOFactory.getAtraccionDAO().update(this);
	}

	@Override
	public String getGenero() {
		return this.genero;
	}

	@Override
	public double getCosto() {
		return this.costo;
	}

	@Override
	public double getDuracion() {
		return this.tiempoPromedio;
	}

	@Override
	public boolean tieneCupo() {
		return this.cupo > 0;
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public String toString() {
		return "Atraccion: " + this.nombre + ", String: " + this.genero + ", cuesta: " + this.costo + ", dura: "
				+ this.tiempoPromedio + " horas.";
	}

	@Override
	public boolean esPromo() {
		return false;
	}

	/*
	 * PRE: Recibe un sugerible por parametro POST: Retorna True si this y s son la
	 * misma atraccion, sino es una promo y se encargará la misma
	 */
	@Override
	public boolean tengoSugerible(Sugerible s) {
		if (s.esPromo())
			return s.tengoSugerible(this);
		else
			return this.equals(s);
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
		Atraccion other = (Atraccion) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public int getId() {
		return this.id;
	}

	public int getCupo() {
		return this.cupo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

}
