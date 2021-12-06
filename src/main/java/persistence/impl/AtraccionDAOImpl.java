package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Atraccion;
import model.exceptions.DatosNegativosException;
import model.exceptions.EscritorExceptions;
import model.nullobjects.NullAtraccion;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;
import persistence.dao.AtraccionDAO;

public class AtraccionDAOImpl implements AtraccionDAO {

	private Atraccion toAtraccion(ResultSet rs) throws SQLException {
		Atraccion a = NullAtraccion.build();
		try {
			a = new Atraccion(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("costo"), rs.getString("tipo"),
					rs.getDouble("tiempo_promedio"), rs.getInt("cupo"));
		} catch (DatosNegativosException dne) {
			EscritorExceptions.escribirExceptions("SalidaExceptions/" + "Exceptions.txt", dne);
		}
		return a;

	}

	@Override
	public Atraccion findbyID(Integer id) {
		try {
			String sql = "SELECT * FROM atracciones WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			return toAtraccion(rs);
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<Atraccion> findAll() {
		try {
			ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
			String sql = "SELECT * FROM atracciones";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				atracciones.add(toAtraccion(rs));
			}
			return atracciones;
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT count(*) as cantidad FROM atracciones";
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
	public int insert(Atraccion a) {
		try {
			String sql = "INSERT INTO atracciones (nombre, costo, tipo, tiempo_promedio, cupo) VALUES( ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, a.getNombre());
			st.setDouble(2, a.getCosto());
			st.setString(3, a.getGenero());
			st.setDouble(4, a.getDuracion());
			st.setInt(5, a.getCupo());
			int rs = st.executeUpdate();
			return rs;
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Atraccion a) {
		try {
			String sql = "UPDATE atracciones SET nombre = ?, costo = ?, tipo = ?, tiempo_promedio  = ?, cupo = ? WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, a.getNombre());
			st.setDouble(2, a.getCosto());
			st.setString(3, a.getGenero());
			st.setDouble(4, a.getDuracion());
			st.setInt(5, a.getCupo());
			st.setInt(6, a.getId());
			int rs = st.executeUpdate();
			return rs;
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}

	}

	@Override
	public int delete(Atraccion a) {
		try {
			String sql = "UPDATE atracciones SET borrado = 1 WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, a.getId());
			int rs = st.executeUpdate();
			return rs;
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Map<String, Atraccion> armarMapaAtraccion() {

		List<Atraccion> atracciones = findAll();
		Map<String, Atraccion> resultado = new HashMap<String, Atraccion>();
		for (Atraccion atraccion : atracciones) {
			resultado.put(atraccion.getNombre(), atraccion);
		}
		return resultado;
	}

}
