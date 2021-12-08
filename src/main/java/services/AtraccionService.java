package services;

import java.util.List;

import model.Atraccion;
import persistence.commons.DAOFactory;
import persistence.dao.AtraccionDAO;

public class AtraccionService {

	public List<Atraccion> list() {
		AtraccionDAO ad = DAOFactory.getAtraccionDAO();
		List<Atraccion> atracciones = ad.findAll();
		return atracciones;
	}

}
