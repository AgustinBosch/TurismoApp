package model.promocion;

import java.util.ArrayList;

import model.Atraccion;
import model.Sugerible;
import model.exceptions.TipoException;

public abstract class Promo implements Sugerible {

	private ArrayList<Atraccion> miPromo;
	private final double duracionPromedio;
	private final String generoDePromo;
	protected double costo;
	private int id;

	public Promo(int id, ArrayList<Atraccion> miPromo, String generoDePromo) throws TipoException {
		this.miPromo = validarPromo(miPromo, generoDePromo);
		this.duracionPromedio = this.setDuracion();
		this.generoDePromo = generoDePromo;
		this.id = id;
	}
	

	/*
	 * PRE: Recibe una lista de atracciones que incluye la promo y el String de
	 * genero de la misma POST: Retorna la lista de atracciones que incluye la promo
	 * en caso de que el genero de las atracciones coincidan con el genero de la
	 * promocion, caso contrario se lanza StringException
	 */
	private ArrayList<Atraccion> validarPromo(ArrayList<Atraccion> miPromo, String generoDePromo) throws TipoException {
		for (Atraccion atr : miPromo) {
			if (!atr.getGenero().equals(generoDePromo)) {
				throw new TipoException("La atraccion: " + atr.getNombre() + " no coincide con el genero de la promocion");
			}
		}
		return miPromo;
	}

	private double setDuracion() {
		double duracion = 0d;
		for (Atraccion atraccion : this.miPromo) {
			duracion += atraccion.getDuracion();
		}
		return duracion;
	}

	@Override
	public boolean esPromo() {
		return true;
	}

	public ArrayList<Atraccion> getMisAtracciones() {
		return this.miPromo;
	}

	@Override
	public String getGenero() {
		return this.generoDePromo;
	}
	public abstract String getTipoPromo();
	
	public abstract double getExtra();

	@Override
	public double getCosto() {
		return this.costo;
	}

	@Override
	public double getDuracion() {
		return this.duracionPromedio;
	}

	@Override
	public boolean tieneCupo() {
		for (Atraccion atraccion : this.miPromo) {
			if (!atraccion.tieneCupo())
				return false;
		}
		return true;
	}

	@Override
	public void ocuparLugar() {
		for (Atraccion atraccion : this.miPromo)
			atraccion.ocuparLugar();
	}


	public int getId() {
		return this.id;
	}
	
	@Override
	public String getNombre() {
		String nombre = "";
		for (Atraccion atraccion : miPromo) {
			nombre += atraccion.getNombre() + ", ";
		}
		return "Pack " + this.generoDePromo + ": " + nombre;
	}

	@Override
	public String toString() {
		return this.getNombre() + "cuesta: " + this.getCosto() + ", dura: " + this.duracionPromedio + " horas";
	}

	/*
	 * PRE: Recibe un sugerible por parametro POST: Retorna True si this y s tienen
	 * algun elemento en comun
	 */

	@Override
	public boolean tengoSugerible(Sugerible s) {
		boolean resultado = false;
		int i = 0;
		while (!resultado && i < this.miPromo.size())
			resultado = s.tengoSugerible(this.miPromo.get(i++));

		return resultado;
	}

}
