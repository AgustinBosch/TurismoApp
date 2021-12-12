package services;

import java.util.List;

import model.Atraccion;
import model.Itinerario;
import model.Usuario;
import model.nullobjects.NullUsuario;
import persistence.commons.DAOFactory;
import persistence.dao.UsuarioDAO;
import utils.ParseNumeros;

public class UsuarioService {

	public List<Usuario> buscarUsuarios() {
		return DAOFactory.getUsuarioDAO().findAll();
	}

	public Usuario login(String nombre, String pass) {
		String nombreLower = nombre.toLowerCase();
		Usuario usuario = DAOFactory.getUsuarioDAO().findByName(nombreLower);
		if (usuario.isNull() || !usuario.checkPassword(pass)) {
			usuario = NullUsuario.build();
		}
		return usuario;

	}

	public boolean existeNombre(String nombre) {
		return DAOFactory.getUsuarioDAO().existeUsuario(nombre);
	}

	private Usuario toUsuario(String id, String nombre, String password, String tipo, String oro, String tiempo,
			String admin) {

		int idint = ParseNumeros.parInt(id);
		double oroD = ParseNumeros.parDouble(oro);
		double tiempoD = ParseNumeros.parDouble(tiempo);
		boolean adminB = Boolean.parseBoolean(admin);
		String nombreLower = nombre.toLowerCase();
		Itinerario i = new Itinerario();

		Usuario u = new Usuario(idint, nombreLower, password, tipo, oroD, tiempoD, i, adminB);
		u.setPass(password);

		return u;

	}

	public Usuario registrarUsuario(String nombre, String password, String tipo, String oro, String tiempo,
			String admin) {
		Usuario u = NullUsuario.build();
		
		if(existeNombre(nombre)) {
			nombre = "";
		}
		u = toUsuario("0", nombre, password, tipo, oro, tiempo, admin);

		if (u.isValido()) {
			DAOFactory.getUsuarioDAO().insert(u);
		}
		return u;
	}

	public void borrar(String id) {
		int idint = ParseNumeros.parInt(id);
		Usuario u = new Usuario(idint, "", "", "", 0, 0, null, null);
		DAOFactory.getUsuarioDAO().delete(u);
	}

	public Usuario buscarId(String id) {
		int idint = ParseNumeros.parInt(id);
		return DAOFactory.getUsuarioDAO().findbyID(idint);
	}

	public Usuario actualizar(String id, String nombre, String pass, String genero, String oro, String tiempo, String admin) {
		Usuario u = toUsuario(id, nombre, pass, genero, oro, tiempo, admin);
		if(u.isValido()) 
			DAOFactory.getUsuarioDAO().update(u);
		
		return u;
	}

}
