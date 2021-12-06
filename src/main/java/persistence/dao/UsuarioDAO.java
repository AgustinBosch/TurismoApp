package persistence.dao;

import model.Usuario;
import persistence.commons.GenericDAO;

public interface UsuarioDAO extends GenericDAO<Usuario>{
	public Usuario findByName(String name);
}
