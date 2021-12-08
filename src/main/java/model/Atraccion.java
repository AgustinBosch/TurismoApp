package model;

import java.util.Objects;

import model.exceptions.DatosNegativosException;
import persistence.commons.DAOFactory;
import persistence.dao.AtraccionDAO;

public class Atraccion implements Sugerible {

	private final String nombre;
	private final double costo;
	private final String genero;
	private final double tiempoPromedio;
	private int cupo;
	private int id;
	private String descripcion;

	public Atraccion(int id, String nombre, double costo, String genero, double tiempoPromedio, int cupo,
			String descripcion) throws DatosNegativosException {
		this.nombre = nombre;
		this.genero = genero;
		this.costo = validarCosto(costo);
		this.cupo = validarCupo(cupo);
		this.tiempoPromedio = validarTiempoPromedio(tiempoPromedio);
		this.id = id;
		this.descripcion = descripcion;
	}

	/*
	 * PRE : Recibe un tiempo promedio de atracción POST : Retorna el valor de
	 * tiempo promedio en caso de que sea valido, caso contrario lanza la exception
	 * DatosNegativosException
	 */
	private Double validarTiempoPromedio(double tiempoPromedio) throws DatosNegativosException {
		if (tiempoPromedio < 0) {
			throw new DatosNegativosException("El tiempo promedio de: " + this.nombre + " posee un valor negativo");
		}
		return tiempoPromedio;
	}

	private int validarCupo(int cupo) throws DatosNegativosException {
		if (cupo < 0) {
			throw new DatosNegativosException("El cupo de la atraccion: " + this.nombre + " posee un valor negativo");
		} else {
			return cupo;
		}
	}

	/*
	 * PRE: Recibe el valor de un costo POST: Retorna el valor del costo en caso de
	 * que sea válido, caso contrario lanza la exception DatosNegativosException
	 */

	private Double validarCosto(Double costo) throws DatosNegativosException {
		if (costo < 0) {
			throw new DatosNegativosException("El costo de la atraccion: " + this.nombre + " posee un valor negativo");
		} else {
			return costo;
		}
	}

	public void ocuparLugar() {
		/*
		 * this.cupo--; AtraccionDAO ad = DAOFactory.getAtraccionDAO();
		 * ad.updateCupo(this);
		 */
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

}
