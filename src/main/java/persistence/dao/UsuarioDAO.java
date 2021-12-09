package persistence.dao;

import model.Sugerible;
import model.Usuario;
import persistence.commons.GenericDAO;

public interface UsuarioDAO extends GenericDAO<Usuario>{
	
	public Usuario findByName(String name);

	public int aniadirVisita(Integer id, Sugerible s);
	
	public boolean existeUsuario(String name);
}
