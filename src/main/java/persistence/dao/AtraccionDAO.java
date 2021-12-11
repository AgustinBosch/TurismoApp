package persistence.dao;

import java.util.List;
import java.util.Map;

import model.Atraccion;
import persistence.commons.GenericDAO;

public interface AtraccionDAO extends GenericDAO<Atraccion> {

	public Map<Integer, Atraccion> armarMapaAtraccion();
	
	public List<Atraccion> findAllBorrados();

}
