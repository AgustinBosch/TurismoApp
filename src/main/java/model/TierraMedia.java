package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import model.comparator.OrdenarOferta;
import model.promocion.Promo;

public class TierraMedia {

	private ArrayList<Usuario> misUsuarios;
	private ArrayList<Sugerible> misSugeribles = new ArrayList<Sugerible>();

	public TierraMedia(ArrayList<Atraccion> misAtracciones, ArrayList<Promo> misPromos,
			ArrayList<Usuario> misUsuarios) {
		this.misSugeribles = setSugeribles(misAtracciones, misPromos);
		this.misUsuarios = misUsuarios;
	}

	private ArrayList<Sugerible> setSugeribles(ArrayList<Atraccion> misAtracciones, ArrayList<Promo> misPromos) {
		ArrayList<Sugerible> arrayDeSugeribles = new ArrayList<Sugerible>();
		arrayDeSugeribles.addAll(misAtracciones);
		arrayDeSugeribles.addAll(misPromos);
		return arrayDeSugeribles;
	}

	public ArrayList<Usuario> getMisUsuarios() {
		return misUsuarios;
	}

	private void venderATodos() {
		for (Usuario p : this.misUsuarios) {
			System.out.println("Hola " + p.getNombre());
			this.venderItinerario(p);
		}
	}

	/*
	 * PRE: Recibe un perfil por parametro POST: Se organiza el itenarario del
	 * perfil, segun las promociones o atracciones adquiridas
	 */

	private void venderItinerario(Usuario usuario) {

		Scanner miscan = null;

		Collections.sort(this.misSugeribles, new OrdenarOferta(usuario.getTipoPref()));

		for (Sugerible sugerencia : this.misSugeribles) {

			boolean yaLoTengo = tengoSugEnLista(sugerencia, usuario.getItinerario().getVisitas());

			if (usuario.puedoComprarSugerible(sugerencia) && sugerencia.tieneCupo() && !yaLoTengo) {

				System.out.println("Quiere comprar(y/n): " + sugerencia);

				miscan = new Scanner(System.in).useDelimiter("\n");
				boolean salida = true;
				while (salida) {
					String respuesta = miscan.nextLine();
					if (respuesta.equalsIgnoreCase("y")) {
//						boolean compraExitosa = usuario.comprarSugerible(sugerencia);
//						if (compraExitosa)
//							sugerencia.ocuparLugar();
//						salida = false;
					} else if (respuesta.equalsIgnoreCase("n"))
						salida = false;
					else
						System.out.println("Introduzca Y o N.");
				}
			}
		}
		System.out.println("Gracias " + usuario.getNombre() + " por visitar La Tierra Media\n");
	}

	/*
	 * PRE: Recibe un sugerible y la lista de sugeribles vendidos POST: Retorna True
	 * si el sugerible está contenido en la lista de vendidos
	 */

	private boolean tengoSugEnLista(Sugerible s, ArrayList<Sugerible> lista) {
		int i = 0;
		boolean resultado = false;

		while (!resultado && i < lista.size()) {
			if (lista.get(i++).tengoSugerible(s))
				resultado = true;
		}
		return resultado;
	}

	public void ejecutar() {
		this.venderATodos();
	}

}
