package entregable_2;

import java.sql.*;
import java.util.List;
import java.util.Random;
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
			System.out.println("Se ha guardado exitosamente la moneda en la base de datos.");
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

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Moneda m = new Moneda();
				m.setNombre(rs.getString(2));
				m.setCodigo(rs.getString(3));
				m.setPrecioEnDolar(rs.getDouble(4));
				m.setStock(rs.getDouble(6));
				monedas.add(m);
			}
			return monedas;
		} catch (SQLException e) {
			System.out.println("Error de SQL al listar monedas: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public Moneda getMoneda(Connection con, String nomenclatura) throws SQLException {
	    String sql = "SELECT * FROM MONEDA WHERE NOMENCLATURA = ?";
	    Moneda moneda = null;

	    try (PreparedStatement pst = con.prepareStatement(sql)) {
	        pst.setString(1, nomenclatura);
	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	            moneda = new Moneda(
	                    rs.getString("NOMENCLATURA"),
	                    rs.getDouble("VALOR_DOLAR"),
	                    rs.getString("NOMBRE"),
	                    rs.getDouble("STOCK"),
	                    rs.getDouble("VOLATILIDAD")
	            );
	        }
	    }
	    return moneda;
	}
	
	public boolean stockRandomizer(Connection con) {
		List<Moneda> monedas = listarMonedas(con);
		if (monedas==null || monedas.isEmpty()) {
			System.out.println("No se encontró ninguna moneda");
			return false;
		}
		Random rand = new Random();
		String sql = "UPDATE MONEDA SET STOCK = ? WHERE NOMENCLATURA = ?";
		try (PreparedStatement pst = con.prepareStatement(sql)){
			for (Moneda moneda: monedas) {
				double stock = rand.nextDouble() * 10000;
				pst.setDouble(1, stock);
				pst.setString(2, moneda.getCodigo());
			}
		System.out.println("Stock generado");
		return true;
		} catch (SQLException e) {
			System.out.println("Error de SQL: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
}