package model;

import java.util.ArrayList;

public class Itinerario {

	private ArrayList<Sugerible> visitas;

	public Itinerario(ArrayList<Sugerible> visitas) {
		this.visitas = visitas;
	}
	
	public Itinerario() {
		ArrayList<Sugerible> array = new ArrayList<Sugerible>();
		this.visitas = array;
	}

	@Override
	public String toString() {
		if (this.visitas.isEmpty())
			return "Usted no compró nada"; //Se modificó para imprimir en pagina de itinerario 9/12/2021 - 17:15
		return /*"Itinerario " +*/ this.visitas.toString() ;//+ "\nCosto total: " + this.getCostoTotal() + " de oro, Tiempo total: "
//				+ this.getTiempoTotal() + " horas.";
	}

	public ArrayList<Sugerible> getVisitas() {
		return visitas;
	}

	/*
	 * POST: Retorna el costo total de la suma de cada elemento de la lista
	 */

	public double getCostoTotal() {
		double total = 0;
		for (Sugerible s : visitas)
			total += s.getCosto();
		return total;
	}

	/*
	 * POST: Retorna la duracion promedio total de la suma de cada elemento de la
	 * lista
	 */

	public double getTiempoTotal() {
		double total = 0;
		for (Sugerible s : visitas)
			total += s.getDuracion();
		return total;
	}

	public void aniadirVisita(Sugerible sugerencia) {
		this.visitas.add(sugerencia);
	}

}
