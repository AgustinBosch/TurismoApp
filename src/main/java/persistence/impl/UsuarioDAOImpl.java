package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Itinerario;
import model.Sugerible;
import model.Usuario;
import model.exceptions.DatosNegativosException;
import model.exceptions.EscritorExceptions;
import model.nullobjects.NullUsuario;
import persistence.commons.ConnectionProvider;
import persistence.commons.DAOFactory;
import persistence.commons.MissingDataException;
import persistence.dao.AtraccionDAO;
import persistence.dao.PromocionDAO;
import persistence.dao.UsuarioDAO;

public class UsuarioDAOImpl implements UsuarioDAO {

	@Override
	public Usuario findbyID(Integer id) {
		try {
			String sql = "SELECT * FROM usuarios WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			Usuario u = NullUsuario.build();
			if (!rs.isClosed()) {
				u = toUsuario(rs);
			}
			return u;

		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<Usuario> findAll() {
		try {
			String sql = "SELECT * FROM usuarios";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

			while (rs.next()) {
				usuarios.add(toUsuario(rs));
			}
			return usuarios;

		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT count(*) as cantidad FROM usuarios";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			int resultado = rs.getInt("cantidad");
			return resultado;
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int insert(Usuario u) {
		try {
			String sql = "INSERT INTO usuarios (hash, admin, nombre, oro, tiempo_disponible, tipo_preferido) VALUES (?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, u.getPass());
			st.setBoolean(2, u.getAdmin());
			st.setString(3, u.getName());
			st.setDouble(4, u.getOro());
			st.setDouble(5, u.getTiempoDisponible());
			st.setString(6, u.getTipoPref());

			int resultado = st.executeUpdate();
			return resultado;

		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Usuario u) {
		try {
			String sql = "UPDATE usuarios SET nombre = ?, oro = ?, tiempo_disponible = ?, tipo_preferido  = ? WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, u.getName());
			st.setDouble(2, u.getOro());
			st.setDouble(3, u.getTiempoDisponible());
			st.setString(4, u.getTipoPref());
			st.setInt(5, u.getId());
			int resultado = st.executeUpdate();
			return resultado;
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Usuario u) {
		try {
			String sql = "UPDATE atracciones SET borrado = 1 WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, u.getId());
			int rs = st.executeUpdate();
			return rs;
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}

	private Usuario toUsuario(ResultSet rs) throws SQLException {
		Usuario u = NullUsuario.build();
		try {
			String sql = "SELECT tipo, atraccion_id, promo_id FROM itinerarios WHERE usuario_id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, rs.getString("id"));

			ResultSet tablaVisitas = st.executeQuery();
			ArrayList<Sugerible> visitas = new ArrayList<Sugerible>();

			AtraccionDAO ad = DAOFactory.getAtraccionDAO();
			PromocionDAO pd = DAOFactory.getPromocionDAO();

			while (tablaVisitas.next()) {
				if (tablaVisitas.getString("tipo").equals("atraccion"))
					visitas.add(ad.findbyID(tablaVisitas.getInt("atraccion_id")));
				else
					visitas.add(pd.findbyID(tablaVisitas.getInt("promo_id")));
			}

			u = new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("hash"),
					rs.getString("tipo_preferido"), rs.getDouble("oro"), rs.getDouble("tiempo_disponible"),
					new Itinerario(visitas), rs.getBoolean("admin"));
		} catch (DatosNegativosException dne) {
			EscritorExceptions.escribirExceptions("SalidaExceptions/" + "Exceptions.txt", dne);
		}
		return u;
	}

	@Override
	public Usuario findByName(String nombre) {
		try {
			String sql = "SELECT * FROM usuarios WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nombre);
			ResultSet rs = st.executeQuery();
			Usuario u = NullUsuario.build();
			if (!rs.isClosed()) {
				u = toUsuario(rs);
			}
			return u;

		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int aniadirVisita(Integer id, Sugerible s) {
		try {
			String sql;
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement st;
			if (s.esPromo()) {
				sql = "INSERT INTO itinerarios (usuario_id, tipo, promo_id) VALUES (?, ?, ?)";
				st = conn.prepareStatement(sql);
				st.setInt(1, id);
				st.setString(2, "promo");
				st.setInt(3, s.getId());
			} else {
				sql = "INSERT INTO itinerarios (usuario_id, tipo, atraccion_id) VALUES (?, ?, ?)";
				st = conn.prepareStatement(sql);
				st.setInt(1, id);
				st.setString(2, "atraccion");
				st.setInt(3, s.getId());
			}

			int resultado = st.executeUpdate();
			return resultado;

		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public boolean existeUsuario(String name) {
		try {
			String sql = "SELECT count(*) as cantidad FROM usuarios WHERE nombre=?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, name);
			ResultSet rs = st.executeQuery();
			return rs.getBoolean("cantidad");
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}
}
