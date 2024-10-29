package entregable_2;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class MonedaDAOjdbc implements MonedaDAO {

	@Override
	public boolean guardarMoneda(Moneda moneda) {
		String sql = "INSERT INTO MONEDA (TIPO, NOMBRE, NOMENCLATURA, VALOR_DOLAR, STOCK) VALUES (?,?,?,?,?)";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setString(1, String.valueOf('F'));
			pst.setString(2, moneda.getNombre());
			pst.setString(3, moneda.getCodigo());
			pst.setDouble(4, moneda.getPrecioEnDolar());
			pst.setDouble(5, moneda.getStock());
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<Moneda> listarMonedas(Connection con) {
		List<Moneda> monedas = new ArrayList<>();
		String sql = "SELECT * FROM MONEDA";

		try (PreparedStatement pst = con.prepareStatement(sql)) {
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Moneda m = new Moneda(rs.getString("NOMENCLATURA"), rs.getDouble("VALOR_DOLAR"), rs.getString("NOMBRE"),
						rs.getDouble("STOCK"), rs.getDouble("VOLATILIDAD"));
				monedas.add(m);
			}
			return monedas;
		} catch (SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}




}
