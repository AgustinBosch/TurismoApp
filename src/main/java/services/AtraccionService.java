package services;

import java.util.List;

import model.Atraccion;
import model.nullobjects.NullAtraccion;
import persistence.commons.DAOFactory;
import utils.ParseNumeros;

public class AtraccionService {

	public List<Atraccion> buscarAtracciones() {
		return DAOFactory.getAtraccionDAO().findAll();
	}

	private Atraccion toAtraccion(String id, String nombre, String oro, String genero, String tiempo, String cupo,
			String descripcion) {

		Atraccion a = NullAtraccion.build();

		int idint = ParseNumeros.parInt(id);
		double oroD = ParseNumeros.parDouble(oro);
		double tiempoD = ParseNumeros.parDouble(tiempo);
		int cupoI = ParseNumeros.parInt(cupo);

		a = new Atraccion(idint, nombre, oroD, genero, tiempoD, cupoI, descripcion);

		return a;

	}

	public Atraccion crearAtraccion(String nombre, String oro, String genero, String tiempo, String cupo,
			String descripcion) {

		Atraccion a = toAtraccion("0", nombre, oro, genero, tiempo, cupo, descripcion);

		if (a.isValido()) {
			DAOFactory.getAtraccionDAO().insert(a);
		}

		return a;
	}

	public void borrar(Integer id) {
		Atraccion a = new Atraccion(id, null, 0, null, 0, 0, null);
		DAOFactory.getAtraccionDAO().delete(a);
	}

	public Atraccion buscarId(String id) {
		int idint = id.matches("\\d+") ? Integer.parseInt(id) : 0;
		return DAOFactory.getAtraccionDAO().findbyID(idint);
	}

	public Atraccion actualizar(String id, String nombre, String oro, String genero, String tiempo, String cupo,
			String descripcion) {

		Atraccion a = toAtraccion(id, nombre, oro, genero, tiempo, cupo, descripcion);
		if (a.isValido())
			DAOFactory.getAtraccionDAO().update(a);

		return a;
	}

}
