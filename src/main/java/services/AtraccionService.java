package services;

import java.util.List;

import model.Atraccion;
import persistence.commons.DAOFactory;
import persistence.dao.AtraccionDAO;

public class AtraccionService {

	public List<Atraccion> buscarAtracciones() {
		return DAOFactory.getAtraccionDAO().findAll();
	}

}
