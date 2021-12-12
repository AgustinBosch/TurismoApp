package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Atraccion;
import model.nullobjects.NullPromo;
import model.promocion.*;
import persistence.commons.ConnectionProvider;
import persistence.commons.DAOFactory;
import persistence.commons.MissingDataException;
import persistence.dao.AtraccionDAO;
import persistence.dao.PromocionDAO;
import utils.ParseNumeros;

public class PromocionDAOImpl implements PromocionDAO {

	@Override
	public Promo findbyID(Integer id) {
		try {
			String sql = "SELECT promociones.promo_id, promociones.tipo_promo, promociones.descripcion, promociones.tipo_atraccion,promociones.extra, group_concat(atraccion_id) AS 'atracciones' "
					+ "FROM promociones "
					+ "JOIN atracciones_promociones ON promociones.promo_id = atracciones_promociones.promo_id "
					+ "WHERE promociones.promo_id = ? " + "GROUP BY promociones.promo_id";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			return toPromo(rs);

		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<Promo> findAll() {
		try {
			String sql = "SELECT promociones.promo_id, promociones.tipo_promo, promociones.descripcion, promociones.tipo_atraccion,promociones.extra, group_concat(atraccion_id) AS 'atracciones' "
					+ "FROM promociones "
					+ "JOIN atracciones_promociones ON promociones.promo_id = atracciones_promociones.promo_id "
					+ "WHERE borrado = 0 " + "GROUP BY promociones.promo_id";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			ArrayList<Promo> promociones = new ArrayList<Promo>();

			while (rs.next()) {
				promociones.add(toPromo(rs));
			}
			return promociones;

		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}

	public ArrayList<Atraccion> listaAtracciones(String[] ids) {
		ArrayList<Atraccion> listaAtracciones = new ArrayList<Atraccion>();
		AtraccionDAO ad = DAOFactory.getAtraccionDAO();
		for (String id : ids) {
			int idint = ParseNumeros.parInt(id);
			listaAtracciones.add(ad.findbyID(idint));
		}
		return listaAtracciones;
	}

	private Promo toPromo(ResultSet rs) throws SQLException {
		Promo p = NullPromo.build();

		String[] listaids = rs.getString("atracciones").split(",");
		ArrayList<Atraccion> atraccionesDePromo = listaAtracciones(listaids);

		if (rs.getString("tipo_promo").equals("Absoluta")) {
			p = new PromoAbsoluta(rs.getInt("promo_id"), atraccionesDePromo, rs.getString("tipo_atraccion"),
					rs.getString("descripcion"), rs.getDouble("extra"));
		} else if (rs.getString("tipo_promo").equals("Porcentual")) {
			p = new PromoPorcentual(rs.getInt("promo_id"), atraccionesDePromo, rs.getString("tipo_atraccion"),
					rs.getString("descripcion"), rs.getDouble("extra"));
		} else if (rs.getString("tipo_promo").equals("AxB")) {
			p = new PromoAxB(rs.getInt("promo_id"), atraccionesDePromo, rs.getString("tipo_atraccion"),
					rs.getString("descripcion"));
		}

		if (!p.isValido()) {
			p = NullPromo.build();
		}
		return p;
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT count(*) as cantidad FROM promociones";
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
	public int insert(Promo p) {
		try {
			int ultimoId = ultimoId() + 1;
			String sqlpromo = "INSERT INTO promociones (tipo_promo, tipo_atraccion, extra, descripcion) VALUES( ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement st = conn.prepareStatement(sqlpromo);
			st.setString(1, p.getTipoPromo());
			st.setString(2, p.getGenero());
			st.setDouble(3, p.getExtra());
			st.setString(4, p.getDescripcion());
			int resultado = st.executeUpdate();

			String sqlatraccion;
			PreparedStatement stAtraccion;
			for (Atraccion a : p.getMisAtracciones()) {
				sqlatraccion = "INSERT INTO atracciones_promociones (promo_id, atraccion_id) VALUES ( ?, ?)";
				stAtraccion = conn.prepareStatement(sqlatraccion);
				stAtraccion.setInt(1, ultimoId);
				stAtraccion.setInt(2, a.getId());
				resultado += stAtraccion.executeUpdate();
			}

			return resultado;

		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}

	private int ultimoId() {
		try {
			String sql = "SELECT seq FROM sqlite_sequence WHERE name = 'promociones'";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			int ultimoId = rs.getInt("seq");
			return ultimoId;

		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Promo p) {
		return 0;
	}

	private int borrarAtracciones(Promo p) {
		try {
			String sql = "DELETE FROM atracciones_promociones WHERE promo_id = ?";
			Connection conn = ConnectionProvider.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, p.getId());
			int rs = st.executeUpdate();
			
			return rs;
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}

	}

	@Override
	public int delete(Promo p) {
		try {
			String sql = "UPDATE promociones SET borrado = 1 WHERE promo_id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, p.getId());
			int rs = st.executeUpdate();
			return rs;
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}

}
